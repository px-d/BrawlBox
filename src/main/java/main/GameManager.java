package main;

import crucial.Brawler;
import enums.State;
import enums.Teams;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import util.ActionBarAPI;
import util.ItemBuilder;
import util.TitleAPI;
import util.ValueHolder;
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
    private int lobbyTime = 10;
    private int preptime = 10;
    private int endtime = 30;

    private String[] core = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};

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
                if (state == State.LOBBY) {
                    if (lobbyTime == 1) {
                        state = State.PREP;
                        tpToPrep();
                        ActionBarAPI.sendABToAll("");
                    }
                    if (players.size() >= min_players) {
                        lobbyTime--;

                        if (lobbyTime <= 5 && lobbyTime > 0) {
                            TitleAPI.sendTitleToAll("", "&eStarting in: &a" + lobbyTime + "s");
                        }

                    } else {
                        lobbyTime = 10;
                    }
                }

                if (state == State.PREP) {
                    if (preptime > 0) {
                        preptime--;
                    } else if (preptime == 0) {
                        tpToArena();
                        state = State.GAME;
                        preptime = 10;
                    }
                }

                if (state == State.GAME) {
                    checkPlayers();
                    checkCore();
                }

                if (state == State.END) {
                    System.out.println("GAME IS OVER");
                }
            }
        }, 0, 20);
    }

    /**
     * Update all scoreboards depending on the state of the game
     */
    public void updateBoards() {
        Bukkit.getScheduler().runTaskTimer(BrawlBox.INSTANCE, e -> {
            int missing_players = min_players - Bukkit.getOnlinePlayers().size();
            for (FastBoard b : boards.values()) {
                Brawler brawler = players.get(b.getPlayer().getUniqueId());
                if (state == State.LOBBY) {
                    if (missing_players > 0) {
                        ActionBarAPI.sendABToAll("&cWaiting for " + missing_players + " more...");
                    } else {
                        ActionBarAPI.sendABToAll("&eGame starting in &a" + lobbyTime + "s");
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
                            "",
                            ValueHolder.tacc("Prep: &a" + preptime + "s"),
                            "",
                            "Coins: " + ValueHolder.tacc("&6" + brawler.getCoins() + "$")
                    ));
                }

                if (state == State.GAME) {
                    b.updateLines(Arrays.asList(
                            "",
                            "Team: " + brawler.getStringTeam(),
                            "",
                            ValueHolder.tacc("Kills: &a" + brawler.getKills()),
                            ValueHolder.tacc("Coins: &6" + brawler.getCoins() + "$"),
                            "",
                            core[0] + " " + core[1] + " " + core[2],
                            core[3] + " " + core[4] + " " + core[5],
                            core[6] + " " + core[7] + " " + core[8]

                    ));
                }
            }
        }, 0, 1);
    }


    private void checkPlayers() {

    }

    /**
     * Checks the 3x3 field in the middle
     */
    private void checkCore() {
        World w = Bukkit.getWorld("game");

        //TOP ROW
        assert w != null;
        if (w.getBlockAt(-1, 19, -1).getType() == Material.BLUE_CONCRETE) {
            core[0] = ValueHolder.tacc("&9\u2b1b");
        } else if (w.getBlockAt(-1, 19, -1).getType() == Material.RED_CONCRETE) {
            core[0] = ValueHolder.tacc("&c\u2b1b");
        } else {
            core[0] = ValueHolder.tacc("&f\u2b1b");
        }
        if (w.getBlockAt(0, 19, -1).getType() == Material.BLUE_CONCRETE) {
            core[1] = ValueHolder.tacc("&9\u2b1b");
        } else if (w.getBlockAt(0, 19, -1).getType() == Material.RED_CONCRETE) {
            core[1] = ValueHolder.tacc("&c\u2b1b");
        } else {
            core[1] = ValueHolder.tacc("&f\u2b1b");
        }
        if (w.getBlockAt(1, 19, -1).getType() == Material.BLUE_CONCRETE) {
            core[2] = ValueHolder.tacc("&9\u2b1b");
        } else if (w.getBlockAt(1, 19, -1).getType() == Material.RED_CONCRETE) {
            core[2] = ValueHolder.tacc("&c\u2b1b");
        } else {
            core[2] = ValueHolder.tacc("&f\u2b1b");
        }

        //MID ROW
        if (w.getBlockAt(-1, 19, 0).getType() == Material.BLUE_CONCRETE) {
            core[3] = ValueHolder.tacc("&9\u2b1b");
        } else if (w.getBlockAt(-1, 19, 0).getType() == Material.RED_CONCRETE) {
            core[3] = ValueHolder.tacc("&c\u2b1b");
        } else {
            core[3] = ValueHolder.tacc("&f\u2b1b");
        }
        if (w.getBlockAt(0, 19, 0).getType() == Material.BLUE_CONCRETE) {
            core[4] = ValueHolder.tacc("&9\u2b1b");
        } else if (w.getBlockAt(0, 19, 0).getType() == Material.RED_CONCRETE) {
            core[4] = ValueHolder.tacc("&c\u2b1b");
        } else {
            core[4] = ValueHolder.tacc("&f\u2b1b");
        }
        if (w.getBlockAt(1, 19, 0).getType() == Material.BLUE_CONCRETE) {
            core[5] = ValueHolder.tacc("&9\u2b1b");
        } else if (w.getBlockAt(1, 19, 0).getType() == Material.RED_CONCRETE) {
            core[5] = ValueHolder.tacc("&c\u2b1b");
        } else {
            core[5] = ValueHolder.tacc("&f\u2b1b");
        }

        //BOT ROW
        if (w.getBlockAt(-1, 19, 1).getType() == Material.BLUE_CONCRETE) {
            core[6] = ValueHolder.tacc("&9\u2b1b");
        } else if (w.getBlockAt(-1, 19, 1).getType() == Material.RED_CONCRETE) {
            core[6] = ValueHolder.tacc("&c\u2b1b");
        } else {
            core[6] = ValueHolder.tacc("&f\u2b1b");
        }
        if (w.getBlockAt(0, 19, 1).getType() == Material.BLUE_CONCRETE) {
            core[7] = ValueHolder.tacc("&9\u2b1b");
        } else if (w.getBlockAt(0, 19, 1).getType() == Material.RED_CONCRETE) {
            core[7] = ValueHolder.tacc("&c\u2b1b");
        } else {
            core[7] = ValueHolder.tacc("&f\u2b1b");
        }
        if (w.getBlockAt(1, 19, 1).getType() == Material.BLUE_CONCRETE) {
            core[8] = ValueHolder.tacc("&9\u2b1b");
        } else if (w.getBlockAt(1, 19, 1).getType() == Material.RED_CONCRETE) {
            core[8] = ValueHolder.tacc("&c\u2b1b");
        } else {
            core[8] = ValueHolder.tacc("&f\u2b1b");
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
            } else if (b.getTeam() == Teams.BLUE) {
                b.getPlayer().teleport(ValueHolder.blue_prep);
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
                Bukkit.broadcastMessage("ERROR CODE 002");
            }
        }
    }

}
