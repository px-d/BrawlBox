package events;

import crucial.Brawler;
import enums.Teams;
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

            if(((Player) e.getDamager()).getInventory().getItemInMainHand().getType().toString().contains("AXE")){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.getEntity().getInventory().clear();
        if (e.getEntity().getKiller() != null) {
            Brawler killer = BrawlBox.gm.players.get(e.getEntity().getKiller().getUniqueId());
            killer.addKill();
            killer.addCoins(200);

            Brawler victim = BrawlBox.gm.players.get(e.getEntity().getUniqueId());
            victim.kill();

            e.getEntity().spigot().respawn();

            if(victim.getTeam() == Teams.BLUE){
                victim.getPlayer().teleport(ValueHolder.blue_prep);
            }
            if(victim.getTeam() == Teams.RED){
                victim.getPlayer().teleport(ValueHolder.red_prep);
            }
        }
    }


}
