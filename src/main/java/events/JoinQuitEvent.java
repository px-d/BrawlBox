package events;

import main.BrawlBox;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import util.ValueHolder;

public class JoinQuitEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        BrawlBox.gm.registerPlayer(e.getPlayer());
        e.setJoinMessage(ValueHolder.prefix + ValueHolder.tacc("&e" + e.getPlayer().getName() + " &7joined."));
        e.getPlayer().teleport(ValueHolder.lobby);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        BrawlBox.gm.unregisterPlayer(e.getPlayer());
        e.setQuitMessage(ValueHolder.prefix + ValueHolder.tacc("&e" + e.getPlayer().getName() + " &7left."));
    }

}
