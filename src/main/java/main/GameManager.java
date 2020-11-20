package main;

import crucial.Brawler;
import enums.State;
import enums.Teams;
import org.bukkit.*;
import org.bukkit.entity.Player;
import util.*;
import util.scoreboard.FastBoard;

import java.util.*;


public class GameManager {

    public HashMap<UUID, Brawler> players = new HashMap<>();
    public HashMap<UUID, FastBoard> boards = new HashMap<>();

    public ArrayList<Location> placedBlocks = new ArrayList<>();

    public State state = State.LOBBY;

    public int score_red = 0;
    public int score_blue = 0;

    public int min_players = 2;

    //PAUSE
    public boolean pause = false;

    //TIMES
    private int lobbyTime = 60;
    private int preptime = 20;
    private int endtime = 30;

    private String[] core = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private int[] distr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

    public GameManager() {
        run();
        updateBoards();
    }

    /**
     * @param p The Player you want to register
     */
    public void registerPlayer(Player p) {
        p.getInventory().clear();
        heal(p);
        Brawler brawler = new Brawler(p);
        FastBoard board = new FastBoard(p);

        board.updateTitle(ValueHolder.tacc("&bBrawl&fBox"));

        players.put(p.getUniqueId(), brawler);
        boards.put(p.getUniqueId(), board);

        p.getInventory().setItem(4, new ItemBuilder(Material.CHEST).name(ValueHolder.tacc("&aTeamselector &7(Rightclick)")).build());
    }

    /**
     * @param p The Player you want to un-register
     */
    public void unregisterPlayer(Player p) {
        players.remove(p.getUniqueId());
        FastBoard board = boards.remove(p.getUniqueId());
        if (board != null) {
            board.delete();
        }
    }

    /**
     * Default RUN METHOD
     */
    private void run() {
        Bukkit.getScheduler().runTaskTimer(BrawlBox.INSTANCE, e -> {
            if (!pause) {
                if (state == State.GAME || state == State.PREP) {
                    if (getRedPlayers().size() == 0) {
                        Bukkit.broadcastMessage("NO RED PLAYERS LEFT");
                        score_blue = 5;
                        state = State.END;
                    }

                    if (getBluePlayers().size() == 0) {
                        Bukkit.broadcastMessage("NO BLUE PLAYERS LEFT");
                        score_red = 5;
                        state = State.END;
                    }
                }


                if (state == State.LOBBY) {
                    if (lobbyTime == 1) {
                        state = State.PREP;
                        tpToPrep();
                        ActionBarAPI.sendABToAll("");
                        MessageAPI.msgToAll(ValueHolder.prefix + "&aThe game has started. Buy equipment during the &cPrep-Phase &aon the&e End Portal Frame&a behind you.&c Good Luck, Have Fun!");
                        MessageAPI.bong();
                    } else if (players.size() >= min_players) {
                        lobbyTime--;

                        if (lobbyTime <= 5 && lobbyTime > 0) {
                            MessageAPI.msgToAll(ValueHolder.prefix + "&aGame is starting in &e" + lobbyTime + "s");
                            MessageAPI.bing();
                        }

                    } else {
                        lobbyTime = 10;
                    }
                }

                if (state == State.PREP) {
                    if (preptime == 1) {
                        tpToArena();
                        state = State.GAME;
                        preptime = 20;
                        MessageAPI.msgToAll(ValueHolder.prefix + "&aPreperation is over. Prepare for battle. &lGO! GO! GO!");
                        MessageAPI.bong();
                    } else {
                        preptime--;
                        if (preptime % 10 == 0 || preptime <= 5) {
                            MessageAPI.msgToAll(ValueHolder.prefix + "&aThe Preperation-Phase is ending in &e" + preptime + "s&a.");
                            MessageAPI.bing();
                        }
                    }
                }


                if (state == State.END) {
                    if (score_blue == 5) {
                        ActionBarAPI.sendABToAll("&9BLUE TEAM &aWON.");
                    } else if (score_red == 5) {
                        ActionBarAPI.sendABToAll("&CRED TEAM &aWON.");
                    }
                    if (endtime == 30) {
                        Bukkit.broadcastMessage(ValueHolder.tacc("&cServer is restarting in &e" + endtime + "s"));
                        resetBoard();

                        endGame();

                        if (score_blue == 5) {
                            Bukkit.broadcastMessage(ValueHolder.prefix = ValueHolder.tacc("&9Blue Team &ahas won the game. &6&lGGs!"));
                        } else if (score_red == 5) {
                            Bukkit.broadcastMessage(ValueHolder.prefix = ValueHolder.tacc("&cRed Team &ahas won the game. &6&lGGs!"));
                        }
                    }

                    if (endtime <= 5 || endtime == 10 || endtime == 20) {
                        Bukkit.broadcastMessage(ValueHolder.tacc("&cServer is restarting in &e" + endtime + "s"));
                        MessageAPI.bing();
                    }

                    if (endtime == 0) {
                        Bukkit.getServer().spigot().restart();
                    }
                    endtime--;
                }
            }
        }, 0, 20);
    }

    private void endGame() {

        for (Brawler b : players.values()) {
            b.getPlayer().getInventory().clear();
            b.getPlayer().getInventory().setArmorContents(null);
            b.getPlayer().teleport(ValueHolder.lobby);

            b.getPlayer().sendMessage("");
            b.getPlayer().sendMessage(ValueHolder.tacc("&a&lThank you for playing. Please report any bugs you encounter on my discord."));
            b.getPlayer().sendMessage("");

        }

    }

    /**
     * Update all scoreboards depending on the state of the game
     */
    public void updateBoards() {

        Bukkit.getScheduler().runTaskTimer(BrawlBox.INSTANCE, e -> {
            int missing_players = min_players - Bukkit.getOnlinePlayers().size();

            //CHECK THE CORE
            if (state == State.GAME || state == State.PREP) {
                checkCore();
                checkPlayers();
                //CHECK SCORE
                if (score_red == 5 || score_blue == 5) {
                    state = State.END;
                }
            }
            //UPDATE BOARDS
            for (FastBoard b : boards.values()) {
                Brawler brawler = players.get(b.getPlayer().getUniqueId());
                if (state == State.LOBBY) {
                    if (missing_players > 0) {
                        ActionBarAPI.sendABToAll("&cWaiting for " + missing_players + " more...");
                    } else {
                        ActionBarAPI.sendABToAll("&aGame starting in &e" + lobbyTime + "s");

                    }
                    b.updateLines(Arrays.asList(
                            "",
                            ValueHolder.tacc("Players: &a" + players.size() + "/" + Bukkit.getMaxPlayers()),
                            "",
                            "Team: " + brawler.getStringTeam(),
                            "",
                            ValueHolder.tacc("Starting in: &a" + lobbyTime)));
                }

                if (state == State.PREP) {

                    b.updateLines(Arrays.asList(
                            "",
                            "Team: " + brawler.getStringTeam(),
                            "Coins: " + ValueHolder.tacc("&6" + brawler.getCoins() + "$"),
                            "",
                            "Score:",
                            ValueHolder.tacc("  &cRed&f: " + score_red),
                            ValueHolder.tacc("  &9Blue&f: " + score_blue),
                            "",
                            ValueHolder.tacc("Prep: &a" + preptime + "s")
                    ));
                }

                if (state == State.GAME) {
                    b.updateLines(Arrays.asList(
                            "",
                            ValueHolder.tacc("Kills: &a" + brawler.getKills()),
                            ValueHolder.tacc("Coins: &6" + brawler.getCoins() + "$"),
                            "",
                            "Score:",
                            ValueHolder.tacc("  &cRed&f: " + score_red),
                            ValueHolder.tacc("  &9Blue&f: " + score_blue),
                            "",
                            "  " + core[0] + " " + core[1] + " " + core[2],
                            "  " + core[3] + " " + core[4] + " " + core[5],
                            "  " + core[6] + " " + core[7] + " " + core[8]

                    ));
                }

                if (state == State.END) {
                    if (score_blue == 5) {
                        b.updateLines(Arrays.asList(
                                "",
                                ValueHolder.tacc("&9Blue Team &awon."),
                                ""
                        ));
                    } else if (score_red == 5) {
                        b.updateLines(Arrays.asList(
                                "",
                                ValueHolder.tacc("&cRed Team &awon."),
                                ""
                        ));
                    }
                }
            }
        }, 0, 1);
    }


    private void checkPlayers() {
        int red_alive = 0;
        int blue_alive = 0;

        for (Brawler b : players.values()) {
            if (b.getTeam() == Teams.RED && b.isAlive) {
                red_alive++;
            }
            if (b.getTeam() == Teams.BLUE && b.isAlive) {
                blue_alive++;
            }
        }

        if (blue_alive == 0 && red_alive == 0) {
            Bukkit.broadcastMessage(ValueHolder.tacc("&cERROR CODE 004"));
            state = State.END;
            return;
        }

        if (red_alive == 0 && getRedPlayers().size() > 0 && state == State.GAME) {
            //BLUE WINS
            preptime = 21;
            state = State.PREP;
            Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, e -> {
                tpToPrep();
                resetBoard();
                score_blue++;
            }, 20);
            MessageAPI.msgToAll(ValueHolder.prefix + "&9Team Blue &awins the round.");
            MessageAPI.roundEndSound();
            win(Teams.BLUE);
        }

        if (blue_alive == 0 && getBluePlayers().size() > 0 && state == State.GAME) {
            //RED WINS
            preptime = 21;
            state = State.PREP;
            Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, e -> {
                tpToPrep();
                resetBoard();
                score_red++;
            }, 20);
            MessageAPI.msgToAll(ValueHolder.prefix + "&cTeam Red &awins the round.");
            MessageAPI.roundEndSound();
            win(Teams.RED);
        }

    }

    /**
     * Checks the 3x3 field in the middle
     */
    private void checkCore() {
        World w = Bukkit.getWorld("game");

        //TOP ROW
        assert w != null;
        if (w.getBlockAt(-1, 19, -1).getType() == Material.BLUE_CONCRETE) {
            core[0] = ValueHolder.tacc("&9\u25A0");
            distr[0] = -1;
        } else if (w.getBlockAt(-1, 19, -1).getType() == Material.RED_CONCRETE) {
            core[0] = ValueHolder.tacc("&c\u25A0");
            distr[0] = 1;
        } else {
            core[0] = ValueHolder.tacc("&f\u25A0");
            distr[0] = 0;
        }
        if (w.getBlockAt(0, 19, -1).getType() == Material.BLUE_CONCRETE) {
            core[1] = ValueHolder.tacc("&9\u25A0");
            distr[1] = -1;
        } else if (w.getBlockAt(0, 19, -1).getType() == Material.RED_CONCRETE) {
            core[1] = ValueHolder.tacc("&c\u25A0");
            distr[1] = 1;
        } else {
            core[1] = ValueHolder.tacc("&f\u25A0");
            distr[1] = 0;
        }
        if (w.getBlockAt(1, 19, -1).getType() == Material.BLUE_CONCRETE) {
            core[2] = ValueHolder.tacc("&9\u25A0");
            distr[2] = -1;
        } else if (w.getBlockAt(1, 19, -1).getType() == Material.RED_CONCRETE) {
            core[2] = ValueHolder.tacc("&c\u25A0");
            distr[2] = 1;
        } else {
            core[2] = ValueHolder.tacc("&f\u25A0");
            distr[2] = 0;
        }

        //MID ROW
        if (w.getBlockAt(-1, 19, 0).getType() == Material.BLUE_CONCRETE) {
            core[3] = ValueHolder.tacc("&9\u25A0");
            distr[3] = -1;
        } else if (w.getBlockAt(-1, 19, 0).getType() == Material.RED_CONCRETE) {
            core[3] = ValueHolder.tacc("&c\u25A0");
            distr[3] = 1;
        } else {
            core[3] = ValueHolder.tacc("&f\u25A0");
            distr[3] = 0;
        }
        if (w.getBlockAt(0, 19, 0).getType() == Material.BLUE_CONCRETE) {
            core[4] = ValueHolder.tacc("&9\u25A0");
            distr[4] = -1;
        } else if (w.getBlockAt(0, 19, 0).getType() == Material.RED_CONCRETE) {
            core[4] = ValueHolder.tacc("&c\u25A0");
            distr[4] = 1;
        } else {
            core[4] = ValueHolder.tacc("&f\u25A0");
            distr[4] = 0;
        }
        if (w.getBlockAt(1, 19, 0).getType() == Material.BLUE_CONCRETE) {
            core[5] = ValueHolder.tacc("&9\u25A0");
            distr[5] = -1;
        } else if (w.getBlockAt(1, 19, 0).getType() == Material.RED_CONCRETE) {
            core[5] = ValueHolder.tacc("&c\u25A0");
            distr[5] = 1;
        } else {
            core[5] = ValueHolder.tacc("&f\u25A0");
            distr[5] = 0;
        }

        //BOT ROW
        if (w.getBlockAt(-1, 19, 1).getType() == Material.BLUE_CONCRETE) {
            core[6] = ValueHolder.tacc("&9\u25A0");
            distr[6] = -1;
        } else if (w.getBlockAt(-1, 19, 1).getType() == Material.RED_CONCRETE) {
            core[6] = ValueHolder.tacc("&c\u25A0");
            distr[6] = 1;
        } else {
            core[6] = ValueHolder.tacc("&f\u25A0");
            distr[6] = 0;
        }
        if (w.getBlockAt(0, 19, 1).getType() == Material.BLUE_CONCRETE) {
            core[7] = ValueHolder.tacc("&9\u25A0");
            distr[7] = -1;
        } else if (w.getBlockAt(0, 19, 1).getType() == Material.RED_CONCRETE) {
            core[7] = ValueHolder.tacc("&c\u25A0");
            distr[7] = 1;
        } else {
            core[7] = ValueHolder.tacc("&f\u25A0");
            distr[7] = 0;
        }
        if (w.getBlockAt(1, 19, 1).getType() == Material.BLUE_CONCRETE) {
            core[8] = ValueHolder.tacc("&9\u25A0");
            distr[8] = -1;
        } else if (w.getBlockAt(1, 19, 1).getType() == Material.RED_CONCRETE) {
            core[8] = ValueHolder.tacc("&c\u25A0");
            distr[8] = 1;
        } else {
            core[8] = ValueHolder.tacc("&f\u25A0");
            distr[8] = 0;
        }

        if (distr[0] == 1 && distr[1] == 1 && distr[2] == 1 && distr[3] == 1 && distr[4] == 1 && distr[5] == 1 && distr[6] == 1 && distr[7] == 1 && distr[8] == 1) {
            //RED WINS
            tpToPrep();
            resetBoard();
            state = State.PREP;
            score_red++;

            MessageAPI.msgToAll(ValueHolder.prefix + "&cTeam Red &awins the round.");
            MessageAPI.roundEndSound();
            win(Teams.RED);
        }
        if (distr[0] == -1 && distr[1] == -1 && distr[2] == -1 && distr[3] == -1 && distr[4] == -1 && distr[5] == -1 && distr[6] == -1 && distr[7] == -1 && distr[8] == -1) {
            //BLUE WINS
            tpToPrep();
            resetBoard();
            state = State.PREP;
            score_blue++;

            MessageAPI.msgToAll(ValueHolder.prefix + "&9Team Blue &awins the round.");
            MessageAPI.roundEndSound();
            win(Teams.BLUE);
        }

    }

    /**
     * @param p The Player you want to reset all default attributes
     */
    public void heal(Player p) {
        p.setHealth(20.0);
        p.setFoodLevel(20);
        p.setLevel(0);
        p.setExp(0);
    }

    /**
     * @return A list of all Players of the RED TEAM
     */
    public List<String> getRedPlayers() {
        List<String> redPl = new ArrayList<>();

        for (Brawler b : players.values()) {
            if (b.getTeam() == Teams.RED) {
                redPl.add(ValueHolder.tacc(" &8\u27a2 &7" + b.getPlayer().getName()));
            }
        }

        return redPl;
    }

    /**
     * @return A list of all Players of the BLUE TEAM
     */
    public List<String> getBluePlayers() {
        List<String> bluePl = new ArrayList<>();

        for (Brawler b : players.values()) {
            if (b.getTeam() == Teams.BLUE) {
                bluePl.add(ValueHolder.tacc(" &8\u27a2 &7" + b.getPlayer().getName()));
            }
        }

        return bluePl;
    }

    /**
     * Teleport all Players to their respective team
     */
    private void tpToPrep() {
        for (Brawler b : players.values()) {
            b.isAlive = true;
            if (b.getTeam() == Teams.NONE) {
                if (getBluePlayers().size() == 3 && getRedPlayers().size() == 3) {
                    pause = true;
                    Bukkit.broadcastMessage(ValueHolder.tacc("&cGame has been paused due to an error. (ERROR CODE 000)"));
                    return;
                }

                if (getBluePlayers().size() <= getRedPlayers().size()) {
                    b.setTeam(Teams.BLUE);

                } else {
                    b.setTeam(Teams.RED);
                }
            }
        }

        for (Brawler b : players.values()) {
            b.getPlayer().getInventory().clear();
            if (b.getTeam() == Teams.RED) {
                b.getPlayer().teleport(ValueHolder.red_prep);
                b.getPlayer().setDisplayName(ValueHolder.tacc("&c" + b.getPlayer().getName() + "&f"));
                b.getPlayer().setPlayerListName(ValueHolder.tacc("&c" + b.getPlayer().getName()));
                LoadoutUtil.redLoadout(b.getPlayer());

            } else if (b.getTeam() == Teams.BLUE) {
                b.getPlayer().teleport(ValueHolder.blue_prep);
                b.getPlayer().setDisplayName(ValueHolder.tacc("&9" + b.getPlayer().getName() + "&f"));
                b.getPlayer().setPlayerListName(ValueHolder.tacc("&9" + b.getPlayer().getName()));
                LoadoutUtil.blueLoadout(b.getPlayer());
            } else {
                b.getPlayer().sendMessage(ValueHolder.tacc("&cSomething went wrong. Please contact an Admin. (ERROR CODE 001)"));
                b.getPlayer().kickPlayer("Something went wrong. Please contact an admin. (ERROR CODE 001)");
            }
        }
    }

    /**
     * Teleport all players to the battle arena
     */
    private void tpToArena() {
        for (Brawler b : players.values()) {
            if (b.getTeam() == Teams.RED) {
                b.getPlayer().teleport(ValueHolder.red_spawn);

            } else if (b.getTeam() == Teams.BLUE) {
                b.getPlayer().teleport(ValueHolder.blue_spawn);
            } else {
                Bukkit.broadcastMessage(ValueHolder.tacc("ERROR CODE 002"));
            }
        }
    }

    /**
     * Reset the 3x3 area in the middle & all player placed blocks
     */
    public void resetBoard() {
        for (Location l : placedBlocks) {
            l.getBlock().setType(Material.AIR);
        }
        World w = Bukkit.getWorld("game");
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                assert w != null;
                w.getBlockAt(i, 19, j).setType(Material.WHITE_CONCRETE);
            }
        }
    }

    /**
     * @param team The team you want to reward. Other team gets nothing.
     */
    private void win(Teams team) {
        for (Brawler b : players.values()) {
            if (b.getTeam() == team) {
                b.addCoins(400);
                b.getPlayer().sendMessage(ValueHolder.prefix + ValueHolder.tacc("&aYou got rewarded for winning &7(+400$)&a."));
                b.getPlayer().playSound(b.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
            }
        }
    }

    /**
     * Start the Game earlier
     */
    public void start() {
        this.lobbyTime = 5;
    }
}
