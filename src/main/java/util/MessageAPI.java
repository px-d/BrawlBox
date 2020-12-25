package util;

import crucial.Brawler;
import enums.Lang;
import enums.Teams;
import main.BrawlBox;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MessageAPI {

    public static void noCoins(Brawler brawler) {
        if (brawler.lang == Lang.EN) {
            brawler.getPlayer().sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
        } else {
            brawler.getPlayer().sendMessage(ValueHolder.tacc("&cDu hast nicht gen\u00FCgend Coins."));
        }
    }

    public static void purchased(String msg, Brawler player) {
        if (player.lang == Lang.EN) {
            player.getPlayer().sendMessage(ValueHolder.tacc("&aYou purchased &6" + msg + "&a."));
        } else {
            player.getPlayer().sendMessage(ValueHolder.tacc("&aDu hast &6" + msg + "&a gekauft."));
        }
    }

    public static void alreadyHas(Brawler player) {
        if (player.lang == Lang.EN) {
            player.getPlayer().sendMessage(ValueHolder.tacc("&cYou already bought this."));
        } else {
            player.getPlayer().sendMessage(ValueHolder.tacc("&cDu hast das schon gekauft."));
        }
    }


    public static void counterReduced() {
        for (Brawler b : BrawlBox.gm.players.values()) {
            if (b.getPlayer().isOp() || b.getPlayer().hasPermission("bb.debug")) {
                b.getPlayer().sendMessage(ValueHolder.tacc("&cCountdown-Timer has been reduced to &a5s&c."));
            }
        }
    }

    public static void reward(Teams winner) {
        for (Brawler b : BrawlBox.gm.players.values()) {
            if (b.getTeam() == winner) {
                b.addCoins(400);

                if (b.lang == Lang.EN) {
                    ActionBarAPI.abToPlayer("&6+400$ &afor winning.", b.getPlayer());
                } else {
                    ActionBarAPI.abToPlayer("&6+400$ &af\u00fcr Rundensieg.", b.getPlayer());
                }

            } else {
                if (b.lang == Lang.EN) {
                    ActionBarAPI.abToPlayer("&6+100$ &afor losing.", b.getPlayer());
                } else {
                    ActionBarAPI.abToPlayer("&6+100$ &af\u00fcr Niederlage.", b.getPlayer());
                }
                b.addCoins(100);
            }
        }
    }

    public static void wonRound(Teams team) {
        for (Brawler brawler : BrawlBox.gm.players.values()) {
            if (team == Teams.RED) {
                if (brawler.lang == Lang.EN) {
                    brawler.getPlayer().sendMessage(ValueHolder.tacc(ValueHolder.prefix + "&9Team Blue &awins the round."));
                } else {
                    brawler.getPlayer().sendMessage(ValueHolder.tacc(ValueHolder.prefix + "&9Team Blau &agewinnt die Runde."));
                }
            } else {
                if (brawler.lang == Lang.EN) {
                    brawler.getPlayer().sendMessage(ValueHolder.tacc(ValueHolder.prefix + "&cTeam Red &awins the round."));
                } else {
                    brawler.getPlayer().sendMessage(ValueHolder.tacc(ValueHolder.prefix + "&cTeam Rot &agewinnt die Runde."));
                }
            }
        }
    }


    public static void abWaiting(int missing) {
        for (Brawler brawler : BrawlBox.gm.players.values()) {
            if (brawler.lang == Lang.EN) {
                ActionBarAPI.abToPlayer("&cWaiting for " + missing + " more...", brawler.getPlayer());
            } else {
                ActionBarAPI.abToPlayer("&cWir warten noch auf " + missing + " Spieler", brawler.getPlayer());
            }
        }
    }

    public static void ty() {
        for (Brawler brawler : BrawlBox.gm.players.values()) {
            brawler.getPlayer().sendMessage("");
            if (brawler.lang == Lang.EN) {
                brawler.getPlayer().sendMessage(ValueHolder.tacc("&a&lThank you for playing. Please report any bugs you encounter on the our discord."));
            } else {
                brawler.getPlayer().sendMessage(ValueHolder.tacc("&a&lDanke f\u00FCr's spielen. Bitte reporte alle Bugs die dir auffallen im Discord."));
            }
            brawler.getPlayer().sendMessage("");
        }
    }

    public static void teamWon(Teams team) {
        for (Brawler brawler : BrawlBox.gm.players.values()) {
            if (team == Teams.RED) {
                if (brawler.lang == Lang.EN) {
                    ActionBarAPI.abToPlayer("&CRed Team &awon.", brawler.getPlayer());
                } else {
                    ActionBarAPI.abToPlayer("&CRotes Team &agewinnt.", brawler.getPlayer());
                }
            } else {
                if (brawler.lang == Lang.EN) {
                    ActionBarAPI.abToPlayer("&9Blue Team &awon.", brawler.getPlayer());
                } else {
                    ActionBarAPI.abToPlayer("&9Blaues Team &agewinnt.", brawler.getPlayer());
                }
            }
        }
    }

    public static void bcWinner(Teams team) {
        for (Brawler brawler : BrawlBox.gm.players.values()) {
            if (team == Teams.RED) {
                if (brawler.lang == Lang.EN) {
                    brawler.getPlayer().sendMessage(ValueHolder.prefix + ValueHolder.tacc("&cRed Team &ahas won the game. &6&lGGs!"));
                } else {
                    brawler.getPlayer().sendMessage(ValueHolder.prefix + ValueHolder.tacc("&cTeam Rot &ahat das Spiel gewonnen. &6&lGGs!"));
                }
            } else {
                if (brawler.lang == Lang.EN) {
                    brawler.getPlayer().sendMessage(ValueHolder.prefix + ValueHolder.tacc("&9Blue Team &ahas won the game. &6&lGGs!"));
                } else {
                    brawler.getPlayer().sendMessage(ValueHolder.prefix + ValueHolder.tacc("&9Team Blue &ahat das Spiel gewonnen. &6&lGGs!"));
                }
            }
        }
    }


    public static void prepOver() {
        for (Brawler brawler : BrawlBox.gm.players.values()) {
            if (brawler.lang == Lang.EN) {
                brawler.getPlayer().sendMessage(ValueHolder.tacc("&aPreperation is over. It's getting real serious. &lGO! GO! GO!"));
            } else {
                brawler.getPlayer().sendMessage(ValueHolder.tacc("&aDie Vorbereitung ist vorbei! Jetzt wird's ernst!. &lLOS! LOS! LOS!"));
            }
        }
    }

    public static void abGameStart(int waiting) {
        for (Brawler brawler : BrawlBox.gm.players.values()) {
            if (brawler.lang == Lang.EN) {
                ActionBarAPI.abToPlayer("&aGame starting in &e" + waiting + "s", brawler.getPlayer());
            } else {
                ActionBarAPI.abToPlayer("&aSpiel startet in &e" + waiting + "s", brawler.getPlayer());
            }
        }
    }

    public static void gameStart() {
        for (Brawler brawler : BrawlBox.gm.players.values()) {
            if (brawler.lang == Lang.EN) {
                brawler.getPlayer().sendMessage(ValueHolder.tacc("&aThe game has started. Buy equipment during the &cPrep-Phase &aat the&e Shop.&c Good Luck, Have Fun!"));
            } else {
                brawler.getPlayer().sendMessage(ValueHolder.tacc("&aDas Spiel hat begonnen. Kaufe R\u00FCstung w\u00E4hrend der &cPrep-Phase &aim &eShop.&c Viel Erfolg & habt Spa\u00DF!"));
            }
        }
    }

    public static void prepEndsIn(int time) {
        for (Brawler brawler : BrawlBox.gm.players.values()) {
            if (brawler.lang == Lang.EN) {
                brawler.getPlayer().sendMessage(ValueHolder.prefix + ValueHolder.tacc("&aThe Preperation-Phase is ending in &e" + time + "s&a."));
            } else {
                brawler.getPlayer().sendMessage(ValueHolder.prefix + ValueHolder.tacc("&aDie Vorbereitung endet in &e" + time + "s&a."));
            }
        }
    }


    public static void gameStartingIn(int time) {
        for (Brawler brawler : BrawlBox.gm.players.values()) {
            if (brawler.lang == Lang.EN) {
                brawler.getPlayer().sendMessage(ValueHolder.prefix + ValueHolder.tacc("&aGame is starting in &e" + time + "s"));
            } else {
                brawler.getPlayer().sendMessage(ValueHolder.prefix + ValueHolder.tacc("&aSpiel startet in &e" + time + "s"));
            }
        }
    }

    public static void sendTitleToAll(String title, String subtitle) {
        title = ValueHolder.tacc(title);
        subtitle = ValueHolder.tacc(subtitle);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(title, subtitle, 0, 21, 0);
        }
    }

    public static void sendTitleToAll(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        title = ValueHolder.tacc(title);
        subtitle = ValueHolder.tacc(subtitle);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
    }

    public static void sendTitleToPlayer(Player p, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        title = ValueHolder.tacc(title);
        subtitle = ValueHolder.tacc(subtitle);
        p.sendTitle(title, subtitle, fadeIn, stay, fadeOut);

    }

    public static void msgToAll(String msg) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(ValueHolder.tacc(msg));
        }
    }

    public static void roundEndSound() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 0.5f, 1f);
        }
    }

    public static void bing() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
        }
    }

    public static void bong() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 0.5f, 1f);
        }
    }

}
