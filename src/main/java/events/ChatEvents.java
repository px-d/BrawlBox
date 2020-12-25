package events;

import crucial.Brawler;
import enums.State;
import enums.Teams;
import main.BrawlBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import util.ValueHolder;

public class ChatEvents implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        if (BrawlBox.gm.state == State.LOBBY) {
            e.setCancelled(true);
            msgToAll(ValueHolder.tacc("&7[&aALL&7] " + e.getPlayer().getName() + ": " + e.getMessage().replaceAll("@a", "")));
        }
        if (BrawlBox.gm.state == State.GAME || BrawlBox.gm.state == State.PREP) {
            e.setCancelled(true);


            if (e.getMessage().equalsIgnoreCase("@a")) {
                return;
            }

            if (e.getMessage().startsWith("@a")) {
                msgToAll(ValueHolder.tacc("&7[&aALL&7] " + e.getPlayer().getName() + ": " + e.getMessage().replaceAll("@a", "")));
            } else {
                Brawler b = BrawlBox.gm.players.get(e.getPlayer().getUniqueId());
                if (b.getTeam() == Teams.NONE) {
                    msgToAll(ValueHolder.tacc("&7[&aALL&7] " + e.getPlayer().getName() + ": " + e.getMessage()));
                } else {
                    for (Brawler br : BrawlBox.gm.players.values()) {
                        if (br.getTeam() == b.getTeam()) {
                            if (br.getTeam() == Teams.RED) {
                                br.getPlayer().sendMessage(ValueHolder.tacc("&7[&cTEAM&7] ") + b.getPlayer().getName() + ": " + e.getMessage());
                            }
                            if (br.getTeam() == Teams.BLUE) {
                                br.getPlayer().sendMessage(ValueHolder.tacc("&7[&9TEAM&7] ") + b.getPlayer().getName() + ": " + e.getMessage());
                            }

                        }
                    }
                }
            }
        }
    }

    private void msgToAll(String msg) {
        for (Brawler b : BrawlBox.gm.players.values()) {
            b.getPlayer().sendMessage(msg);
        }
    }

}
