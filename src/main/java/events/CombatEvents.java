package events;

import crucial.Brawler;
import main.BrawlBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import util.ValueHolder;

public class CombatEvents implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Brawler hitter = BrawlBox.gm.players.get(e.getDamager().getUniqueId());
            Brawler victim = BrawlBox.gm.players.get(e.getEntity().getUniqueId());

            if (hitter.getTeam() == victim.getTeam()) {
                e.setCancelled(true);
                hitter.getPlayer().sendMessage(ValueHolder.tacc("&cDon't hurt your allies :("));
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            Brawler killer = BrawlBox.gm.players.get(e.getEntity().getKiller().getUniqueId());
            killer.addKill();
            killer.addCoins(200);
        }
    }


}
