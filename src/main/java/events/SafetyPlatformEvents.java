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
import util.ValueHolder;

public class SafetyPlatformEvents implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getPlayer().getInventory().getItemInMainHand() != null) {
                if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD && BrawlBox.gm.state == State.GAME) {
                    Location center = e.getPlayer().getLocation().add(0, -1, 0);
                    Location cobweb = center.clone().add(0, 1, 0);

                    if (center.getBlock().getType() == Material.AIR) {
                        center.getBlock().setType(Material.RED_STAINED_GLASS);
                    }
                    if (cobweb.getBlock().getType() == Material.AIR) {
                        cobweb.getBlock().setType(Material.COBWEB);
                    }
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (center.clone().add(i, 0, j).getBlock().getType() == Material.AIR) {
                                center.clone().add(i, 0, j).getBlock().setType(Material.ORANGE_STAINED_GLASS);
                            }
                        }
                    }

                    //REMOVE PLATFORM LATER
                    Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, x -> {
                        if (cobweb.getBlock().getType() == Material.COBWEB) {
                            cobweb.getBlock().setType(Material.AIR);
                        }
                    }, 20);
                    Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, x -> {
                        if (center.getBlock().getType() == Material.RED_STAINED_GLASS) {
                            center.getBlock().setType(Material.AIR);
                        }
                        for (int i = -1; i <= 1; i++) {
                            for (int j = -1; j <= 1; j++) {
                                if (center.clone().add(i, 0, j).getBlock().getType() == Material.ORANGE_STAINED_GLASS) {
                                    center.clone().add(i, 0, j).getBlock().setType(Material.AIR);
                                }
                            }
                        }
                    }, 200);


                    e.getPlayer().getInventory().setItemInMainHand(ValueHolder.used_platform);


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
}
