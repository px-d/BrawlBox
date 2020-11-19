package events;

import enums.State;
import guis.TeamGUI;
import main.BrawlBox;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import util.ValueHolder;

public class GeneralEvents implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (BrawlBox.gm.state == State.PREP) {
            e.setCancelled(true);
        } else {
            BrawlBox.gm.placedBlocks.add(e.getBlock().getLocation());
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getBlock().getType().toString().contains("CONCRETE")) {
            return;
        }
        if (!BrawlBox.gm.placedBlocks.contains(e.getBlock().getLocation())) {
            e.setCancelled(true);
            if (BrawlBox.gm.state == State.GAME) {
                e.getPlayer().sendMessage(ValueHolder.prefix + ValueHolder.tacc("&cYou're not allowed to mine this!"));
            }

            e.getBlock().getWorld().spawnParticle(Particle.SPELL, e.getBlock().getLocation().add(0.5, 0.2, 0.5), 10);
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);

        } else {
            BrawlBox.gm.placedBlocks.remove(e.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (BrawlBox.gm.state != State.GAME)
            if (e.getEntity() instanceof Player) {
                e.setDamage(0);
            }
    }


    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (BrawlBox.gm.state == State.LOBBY) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getItem() != null) {
                    if (e.getItem().getType() == Material.CHEST) {
                        e.setCancelled(true);
                        TeamGUI.INVENTORY.open(e.getPlayer());
                    }
                }
            }
        }
    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {
        if (!e.isCancelled()) {
            if (BrawlBox.gm.state == State.LOBBY && e.getWhoClicked().getGameMode() != GameMode.CREATIVE)
                e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
            e.setCancelled(true);

    }


}
