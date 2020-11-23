package events;

import enums.State;
import main.BrawlBox;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import util.ValueHolder;

public class JoinQuitEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (BrawlBox.gm.state != State.LOBBY) {
            if (e.getPlayer().getUniqueId().equals("070220f3-40f1-4d51-a95d-c9f5ab21cc2e") || e.getPlayer().getUniqueId().toString().equalsIgnoreCase("d1aad114-129d-48a6-b889-187e86d5cb2c")) {
                e.getPlayer().teleport(ValueHolder.center);
                e.getPlayer().setGameMode(GameMode.SPECTATOR);
                return;
            }
        }
        BrawlBox.gm.registerPlayer(e.getPlayer());
        e.setJoinMessage(ValueHolder.prefix + ValueHolder.tacc("&e" + e.getPlayer().getName() + " &7joined."));
        e.getPlayer().teleport(ValueHolder.lobby);
        e.getPlayer().setWalkSpeed(0.2f);
        e.getPlayer().setFlySpeed(0.1f);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        BrawlBox.gm.unregisterPlayer(e.getPlayer());
        e.setQuitMessage(ValueHolder.prefix + ValueHolder.tacc("&e" + e.getPlayer().getName() + " &7left."));
    }

}
