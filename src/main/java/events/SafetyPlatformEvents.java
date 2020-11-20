package events;

import enums.State;
import main.BrawlBox;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SafetyPlatformEvents implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
//            e.getPlayer().getInventory().getItemInMainHand();
            if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD && BrawlBox.gm.state == State.GAME) {
                Location center = e.getPlayer().getLocation().add(0, -1, 0);
                BrawlBox.gm.placedBlocks.add(center);

                if (center.getBlock().getType() == Material.AIR) {
                    center.getBlock().setType(Material.ORANGE_STAINED_GLASS);
                }
                if (center.clone().add(0, 1, 0).getBlock().getType() == Material.AIR) {
                    center.clone().add(0, 1, 0).getBlock().setType(Material.COBWEB);
                }
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (center.clone().add(i, 0, j).getBlock().getType() == Material.AIR) {
                            center.clone().add(i, 0, j).getBlock().setType(Material.ORANGE_STAINED_GLASS);
                            BrawlBox.gm.placedBlocks.add(center.clone().add(i, 0, j));
                        }
                    }
                }
                Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, x -> {
                    if (center.clone().add(0, 1, 0).getBlock().getType() == Material.COBWEB) {
                        center.clone().add(0, 1, 0).getBlock().setType(Material.AIR);
                    }
                }, 20);
                Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, x -> {

                    center.clone().add(0, 1, 0).getBlock().setType(Material.AIR);
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (center.clone().add(i, 0, j).getBlock().getType() == Material.ORANGE_STAINED_GLASS) {
                                center.clone().add(i, 0, j).getBlock().setType(Material.AIR);
                                BrawlBox.gm.placedBlocks.remove(center.clone().add(i, 0, j));
                            }
                        }
                    }
                }, 200);
                e.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, 1f, 0.2f);
                Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, x -> {
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, 1f, 0.6f);
                }, 2);
                Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, x -> {
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, 1f, 1f);
                }, 4);
            }
        }

    }

}
