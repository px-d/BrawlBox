package events;

import crucial.Brawler;
import enums.State;
import main.BrawlBox;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;

public class ExplosiveBowEvent implements Listener {

    @EventHandler
    public void onShoot(ProjectileHitEvent e) {
        if (e.getHitEntity() == null) {
            if (e.getEntity().getShooter() instanceof Player) {
                Player shooter = (Player) e.getEntity().getShooter();
                Brawler brawler = BrawlBox.gm.players.get(shooter.getUniqueId());
                if (e.getEntity().getType() == EntityType.ARROW) {
                    if (e.getEntity().getCustomName().equals("explosiveArrow")) {
                        double radius = 2.5;
                        Location loc = e.getHitBlock().getLocation();
                        shooter.playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f);
                        loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loc.add(0, 1.2, 0), 5);
                        for (Entity ent : e.getEntity().getNearbyEntities(radius, radius, radius)) {
                            if (ent instanceof Player) {
                                if (ent.getUniqueId() != shooter.getUniqueId()) {
                                    ((Player) ent).playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f);
                                    Brawler victim = BrawlBox.gm.players.get(ent.getUniqueId());

                                    if (BrawlBox.gm.state == State.LOBBY) {
                                        if (((Player) ent).getHealth() - 4.0 > 0) {
                                            ((Player) ent).damage(4);
                                        } else {
                                            victim.kill();
                                        }
                                        System.out.println("PROJECTILE");
                                        ent.setVelocity(new Vector(0, 1, 0));
                                    } else {
                                        if (victim.getTeam() != brawler.getTeam()) {
                                            if (((Player) ent).getHealth() - 4.0 > 0) {
                                                ((Player) ent).damage(3);
                                            } else {
                                                victim.kill();
                                            }
                                            System.out.println("PROJECTILE");
                                            ent.setVelocity(new Vector(new Random().nextDouble(), 1, new Random().nextDouble()));
                                        }
                                    }
                                }
                            }
                        }
                        e.getEntity().remove();
                    }
                }
            }
        }
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent e) {
        if (e.getBow().getItemMeta().getDisplayName().contains("Explosive Bow")) {
            e.getProjectile().setCustomName("explosiveArrow");
            if (e.getEntity() instanceof Player) {
                Player shooter = (Player) e.getEntity();
                Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, x -> {
                    if (!shooter.getInventory().containsAtLeast(new ItemStack(Material.ARROW), 1)) {
                        shooter.getInventory().remove(Material.BOW);
                    }
                }, 1);
            }
        }
    }
}
