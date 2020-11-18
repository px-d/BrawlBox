package main;

import crucial.Brawler;
import enums.State;
import enums.Teams;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import util.ItemBuilder;
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
    private int lobbyTime = 20;
    private int preptime = 30;
    private int endtime = 30;

    public GameManager() {
        run();
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
     * Update all scoreboards depending on the state of the game
     */
    public void updateBoards() {
        int missing_players = min_players - Bukkit.getOnlinePlayers().size();
        for (FastBoard b : boards.values()) {
            Brawler brawler = players.get(b.getPlayer().getUniqueId());
            if (state == State.LOBBY) {
                if (missing_players > 0) {
                    b.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ValueHolder.tacc("&cWaiting for " + missing_players + " more...")));
                } else {
                    b.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ValueHolder.tacc("&eGame starting in &a" + lobbyTime + "s")));
                }
                b.updateLines(Arrays.asList(
                        "",
                        ValueHolder.tacc("Players:&a " + players.size() + "/" + Bukkit.getMaxPlayers()),
                        "",
                        "Team: " + brawler.getStringTeam(),
                        "",
                        "Starting in: " + lobbyTime));
            }

            if (state == State.PREP) {
                b.updateLines(Arrays.asList(
                        "Team: " + brawler.getStringTeam(),
                        "Prep: " + preptime,
                        "",
                        "Coins: " + ValueHolder.tacc("&6" + brawler.getCoins() + "$"),
                        "",
                        "Starting in: " + lobbyTime));
            }
        }
    }

    /**
     * Default RUN METHOD
     */
    private void run() {
        Bukkit.getScheduler().runTaskTimer(BrawlBox.INSTANCE, e -> {
            if (pause == false) {
                updateBoards();
                if (state == State.LOBBY) {
                    if (lobbyTime == 0) {
                        state = State.PREP;
                        teleportPlayers();
                    }
                    if (players.size() >= min_players) {
                        lobbyTime--;
                    } else {
                        lobbyTime = 20;
                    }
                }

                if (state == State.PREP) {

                }

                if (state == State.GAME) {

                }

                if (state == State.END) {

                }
            }
        }, 0, 20);
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
    private void teleportPlayers() {
        for (Brawler b : players.values()) {
            if (b.getTeam() == Teams.NONE) {
                if (getBluePlayers().size() == 3 && getRedPlayers().size() == 3) {
                    pause = true;
                    Bukkit.broadcastMessage(ValueHolder.tacc("&cGame has been paused due to an error."));
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
            if (b.getTeam() == Teams.RED) {
                b.getPlayer().teleport(ValueHolder.red_prep);
            } else if (b.getTeam() == Teams.BLUE) {
                b.getPlayer().teleport(ValueHolder.blue_prep);
            } else {
                b.getPlayer().sendMessage(ValueHolder.tacc("&cSomething went wrong. Please contact an Admin."));
                b.getPlayer().kickPlayer("Something went wrong. Please contact an admin.");
            }
        }
    }

}
