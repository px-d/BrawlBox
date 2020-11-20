package events;

import enums.State;
import main.BrawlBox;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class AxeEvents implements Listener {

    ArrayList<ArmorStand> axes;

    public AxeEvents() {
        this.axes = new ArrayList<>();
    }

    @EventHandler
    public void noManipulate(final PlayerArmorStandManipulateEvent event) {
        if (this.axes.contains(event.getRightClicked())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void axeThrow(final PlayerInteractEvent event) {

        if (BrawlBox.gm.state != State.GAME) {
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR && event.getItem() != null && (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.WOODEN_AXE || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.STONE_AXE || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.GOLDEN_AXE || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_AXE || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_AXE) && (event.getItem().getType() == Material.WOODEN_AXE || event.getItem().getType() == Material.STONE_AXE || event.getItem().getType() == Material.GOLDEN_AXE || event.getItem().getType() == Material.IRON_AXE || event.getItem().getType() == Material.DIAMOND_AXE)) {
            event.setCancelled(true);
            final Player p = event.getPlayer();
            final Vector v = p.getLocation().add(p.getLocation().getDirection().multiply(10)).toVector().subtract(p.getLocation().toVector()).normalize();
            p.getInventory().removeItem(p.getInventory().getItem(p.getInventory().getHeldItemSlot()));
            this.axe((Entity) p, v, event.getItem());
        }
    }

    @EventHandler
    public void axeThrow2(final PlayerInteractEntityEvent event) {

        if (BrawlBox.gm.state != State.GAME) {
            return;
        }

        if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.WOODEN_AXE || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.STONE_AXE || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.GOLDEN_AXE || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_AXE || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_AXE) {
            final Player p = event.getPlayer();
            final Vector v = p.getLocation().add(p.getLocation().getDirection().multiply(10)).toVector().subtract(p.getLocation().toVector()).normalize();
            this.axe((Entity) p, v, event.getPlayer().getInventory().getItemInMainHand());
            p.getInventory().removeItem(p.getInventory().getItem(p.getInventory().getHeldItemSlot()));
        }
    }

    public void axe(final Entity e, final Vector v, final ItemStack m) {
        final Location to = new Location(e.getWorld(), e.getLocation().getX(), e.getLocation().getY() + 1.0, e.getLocation().getZ());
        final ArmorStand a = (ArmorStand) e.getWorld().spawnEntity(to, EntityType.ARMOR_STAND);
        a.setVisible(false);
        a.setSmall(true);
        a.getEquipment().setItemInMainHand(m);
        this.axes.add(a);
        this.axe2(e, a, v.multiply(1.2), 0, m);
    }

    public void axe2(final Entity ef, final ArmorStand a, final Vector v, final int recurse, final ItemStack m) {
        if (recurse < 300) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BrawlBox.INSTANCE, () -> {
                for (final Entity e : a.getNearbyEntities(1.0, 1.0, 1.0)) {
                    if (e != ef && e != a && e instanceof Damageable) {
                        if (m.getType() == Material.WOODEN_AXE) {
                            ((Damageable) e).damage(2.0);
                        }
                        if (m.getType() == Material.STONE_AXE) {
                            ((Damageable) e).damage(5.0);
                        }
                        if (m.getType() == Material.GOLDEN_AXE) {
                            ((Damageable) e).damage(6.0);
                        }
                        if (m.getType() == Material.IRON_AXE) {
                            ((Damageable) e).damage(8.0);
                        }
                        if (m.getType() == Material.DIAMOND_AXE) {
                            ((Damageable) e).damage(10.0);
                        }
                        if (m.hasItemMeta() && m.getItemMeta().hasEnchant(Enchantment.FIRE_ASPECT)) {
                            e.setFireTicks(20);
                        }
                        axes.remove(a);
                        a.remove();
                        break;
                    }
                }
                final double x = a.getRightArmPose().getX();
                a.setRightArmPose(new EulerAngle(x + 0.6, 0.0, 0.0));
                final Vector vec = new Vector(v.getX(), v.getY() - 0.03, v.getZ());
                a.setVelocity(vec);
                if (!a.isDead() && a.isOnGround()) {
                    a.getLocation().getWorld().dropItem(a.getLocation(), new ItemStack(m));
                    axes.remove(a);
                    a.remove();
                } else if (!a.isDead()) {
                    axe2(ef, a, vec, recurse + 1, m);
                }
            }, 2L);
        } else {
            a.getLocation().getWorld().dropItem(a.getLocation(), new ItemStack(m));
            this.axes.remove(a);
            a.remove();
        }
    }

}
