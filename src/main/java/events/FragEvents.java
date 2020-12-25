package events;

import crucial.Brawler;
import main.BrawlBox;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;

public class FragEvents implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) {
                if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.SLIME_BALL) {
                    ItemStack itemStack = e.getPlayer().getInventory().getItemInMainHand();
                    Location ploc = e.getPlayer().getEyeLocation();
                    e.getPlayer().getInventory().setItemInMainHand(null);

                    Item throwItem = e.getPlayer().getWorld().dropItem(ploc, itemStack);

                    throwItem.setVelocity(ploc.getDirection().multiply(1.3));
                    throwItem.setGlowing(true);
                    throwItem.setOwner(e.getPlayer().getUniqueId());
                    Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, () -> {
                        throwItem.remove();
                        throwItem.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, throwItem.getLocation(), 20);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.playSound(throwItem.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f);
                        }

                        List<Entity> inRange = throwItem.getNearbyEntities(2, 2, 2);

                        Brawler thrower = BrawlBox.gm.players.get(e.getPlayer().getUniqueId());

                        inRange.forEach(entity -> {
                            if (entity instanceof Player) {
                                Brawler brawler = BrawlBox.gm.players.get(entity.getUniqueId());
                                if (thrower.getTeam() != brawler.getTeam()) {
                                    if (((Player) entity).getHealth() - 6.0 > 0) {
                                        ((Player) entity).damage(6);
                                        entity.setVelocity(new Vector(new Random().nextDouble(), 0.2, new Random().nextDouble()));
                                    } else {
                                        brawler.kill();
                                    }
                                }
                            }
                        });

                    }, 24);
                }
            }
        }
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getItem().getOwner() != null) {
                e.setCancelled(true);
            }
        }
    }

}
