package events;

import enums.State;
import main.BrawlBox;
import org.bukkit.Bukkit;
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
            if (e.getPlayer().isOp() || e.getPlayer().hasPermission("bb.spectate")) {
                e.getPlayer().teleport(ValueHolder.center);
                e.getPlayer().setGameMode(GameMode.SPECTATOR);
                e.setJoinMessage("");
                return;
            }
        } else {
            BrawlBox.gm.registerPlayer(e.getPlayer());
            e.setJoinMessage(ValueHolder.tacc("&a+ &e" + e.getPlayer().getName()));
            e.getPlayer().teleport(ValueHolder.lobby);
            e.getPlayer().setWalkSpeed(0.2f);
            e.getPlayer().setFlySpeed(0.1f);
            Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, () -> {
                e.getPlayer().sendMessage("");
                e.getPlayer().sendMessage(ValueHolder.tacc("&a\u00C4nder deine Sprache mit &c/lang [en | de]"));
                e.getPlayer().sendMessage(ValueHolder.tacc("&aChange your Language with &c/lang [en | de]"));
                e.getPlayer().sendMessage("");
            }, 5);

        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        BrawlBox.gm.unregisterPlayer(e.getPlayer());
        e.setQuitMessage(ValueHolder.tacc("&c- &e" + e.getPlayer().getName()));
    }
}
