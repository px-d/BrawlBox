package events;

import crucial.Brawler;
import enums.State;
import enums.Teams;
import main.BrawlBox;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import util.ActionBarAPI;
import util.ValueHolder;

public class CombatEvents implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {

        if(e.getEntity() instanceof Player && e.getDamager() instanceof Arrow){
            e.getDamager().remove();
            e.setCancelled(true);
        }

        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Brawler hitter = BrawlBox.gm.players.get(e.getDamager().getUniqueId());
            Brawler victim = BrawlBox.gm.players.get(e.getEntity().getUniqueId());

            if (hitter.getTeam() == victim.getTeam() && BrawlBox.gm.state != State.LOBBY) {
                e.setCancelled(true);
                ActionBarAPI.abToPlayer(ValueHolder.tacc("&cDon't hurt your teammates :("),hitter.getPlayer());
            }

            if (((Player) e.getDamager()).getInventory().getItemInMainHand().getType().toString().contains("AXE")) {
                e.setCancelled(true);
            }

            if (e.getDamage() >= ((Player) e.getEntity()).getHealth()) {
                ((Player) e.getEntity()).getInventory().clear();
                ((Player) e.getEntity()).getInventory().setArmorContents(null);
                e.setCancelled(true);
                victim.kill();

                hitter.addKill();
                //REWARDS
                ActionBarAPI.abToPlayer("&aYou killed &e" + victim.getPlayer().getName() + " &7(+200$)",hitter.getPlayer());
//                hitter.getPlayer().sendMessage(ValueHolder.prefix + ValueHolder.tacc("&aYou killed &e" + victim.getPlayer().getName() + " &7(+200$)"));
                hitter.addCoins(200);
                if (victim.getTeam() == Teams.BLUE) {
                    victim.getPlayer().teleport(ValueHolder.blue_prep);
                }
                if (victim.getTeam() == Teams.RED) {
                    victim.getPlayer().teleport(ValueHolder.red_prep);
                }
                BrawlBox.gm.heal(victim.getPlayer());
            }
        }
    }


    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.getEntity().getInventory().clear();
        if (e.getEntity().getKiller() != null) {
            Brawler killer = BrawlBox.gm.players.get(e.getEntity().getKiller().getUniqueId());
            killer.addKill();


            Brawler victim = BrawlBox.gm.players.get(e.getEntity().getUniqueId());
            victim.kill();

            //REWARDS
            killer.getPlayer().sendMessage(ValueHolder.prefix + ValueHolder.tacc("&aYou killed &e" + victim.getPlayer().getName() + " &7(+200$)"));
            killer.addCoins(200);
            killer.getPlayer().playSound(killer.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f,1f);

            e.getEntity().spigot().respawn();

            if (victim.getTeam() == Teams.BLUE) {
                victim.getPlayer().teleport(ValueHolder.blue_prep);
            }
            if (victim.getTeam() == Teams.RED) {
                victim.getPlayer().teleport(ValueHolder.red_prep);
            }
        }
    }


}
