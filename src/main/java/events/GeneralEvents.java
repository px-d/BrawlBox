package events;

import crucial.Brawler;
import enums.State;
import enums.Teams;
import guis.ShopGUI;
import guis.TeamGUI;
import main.BrawlBox;
import org.bukkit.*;
import org.bukkit.block.Block;
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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import util.ActionBarAPI;
import util.ValueHolder;

public class GeneralEvents implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        if (e.getBlockAgainst().getType() != Material.SEA_LANTERN && e.getBlock().getType().toString().contains("CONCRETE")) {
            e.setCancelled(true);
            ActionBarAPI.abToPlayer("&cYou can only place this block in the middle.", e.getPlayer());
        } else if (e.getBlockAgainst().getType() == Material.SEA_LANTERN && !e.getBlock().getType().toString().contains("CONCRETE")) {
            e.setCancelled(true);
            ActionBarAPI.abToPlayer("&cYou can only place Team Blocks here!", e.getPlayer());
        }

        if (BrawlBox.gm.state == State.PREP) {
            e.setCancelled(true);
        }
        if (e.getBlockPlaced().getLocation().getBlockY() == 8) {
            e.setCancelled(true);
            ActionBarAPI.abToPlayer("&cYou can't build here.", e.getPlayer());
        } else {
            BrawlBox.gm.placedBlocks.add(e.getBlock().getLocation());
        }


    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getBlock().getType().toString().contains("CONCRETE")) {
            e.setDropItems(false);
            Block b = e.getBlock();
            Brawler miner = BrawlBox.gm.players.get(e.getPlayer().getUniqueId());

            if (b.getType() == Material.BLUE_CONCRETE && miner.getTeam() == Teams.BLUE) {
                if (!e.getPlayer().getInventory().containsAtLeast(new ItemStack(Material.BLUE_CONCRETE), 9)) {
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.BLUE_CONCRETE));
                }
            }
            if (b.getType() == Material.RED_CONCRETE && miner.getTeam() == Teams.RED) {
                if (!e.getPlayer().getInventory().containsAtLeast(new ItemStack(Material.RED_CONCRETE), 9)) {
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.RED_CONCRETE));
                }
            }


            Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, x -> {
                if (e.getBlock().getLocation().getBlock().getType() == Material.AIR) {
                    e.getBlock().getLocation().getBlock().setType(Material.WHITE_CONCRETE);
                }
            }, 80);

            return;
        }
        if (!BrawlBox.gm.placedBlocks.contains(e.getBlock().getLocation())) {
            e.setCancelled(true);
            if (BrawlBox.gm.state == State.GAME) {
                ActionBarAPI.abToPlayer("&cYou're not allowed to mine this!", e.getPlayer());
            }

            e.getBlock().getWorld().spawnParticle(Particle.SPELL, e.getBlock().getLocation().add(0.5, 0.2, 0.5), 10);
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);

        } else {
            BrawlBox.gm.placedBlocks.remove(e.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (BrawlBox.gm.state != State.GAME) {
            if (e.getEntity() instanceof Player) {
                e.setDamage(0);
            }
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
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock() == null) {
                return;
            }
            if (e.getClickedBlock().getType() == Material.END_PORTAL_FRAME) {
                ShopGUI.INVENTORY.open(e.getPlayer());
            } else if (e.getClickedBlock().getType() == Material.ANVIL) {
                e.setCancelled(true);
            } else if (e.getClickedBlock().getType() == Material.BLAST_FURNACE) {
                e.setCancelled(true);
            } else if (e.getClickedBlock().getType() == Material.ENCHANTING_TABLE) {
                e.setCancelled(true);
            } else if (e.getClickedBlock().getType() == Material.FURNACE) {
                e.setCancelled(true);
            } else if (e.getClickedBlock().getType() == Material.CRAFTING_TABLE) {
                e.setCancelled(true);
            } else if (e.getClickedBlock().getType() == Material.ENDER_CHEST) {
                e.setCancelled(true);
            } else if (e.getClickedBlock().getType() == Material.BARREL) {
                e.setCancelled(true);
            } else if (e.getClickedBlock().getType() == Material.LECTERN) {
                e.setCancelled(true);
            } else if (e.getClickedBlock().getType() == Material.LOOM) {
                e.setCancelled(true);
            }
            if (e.getItem() != null) {
                if (e.getClickedBlock().getType().toString().contains("LOG") && e.getItem().getType().toString().contains("AXE")) {
                    e.setCancelled(true);
                }
            }
        }

    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {
        if (!e.isCancelled()) {
            if (BrawlBox.gm.state == State.LOBBY && e.getWhoClicked().getGameMode() != GameMode.CREATIVE)
                e.setCancelled(true);

            if (BrawlBox.gm.state == State.PREP) {
                e.setCancelled(true);
            }
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType().toString().contains("LEGGINGS") || e.getCurrentItem().getType().toString().contains("BOOTS") || e.getCurrentItem().getType().toString().contains("HELMET")) {
                    e.setCancelled(true);
                }
            }

        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!(e.getItemDrop().getItemStack().getType() == Material.SMOOTH_SANDSTONE || e.getItemDrop().getItemStack().getType() == Material.END_STONE || e.getItemDrop().getItemStack().getType() == Material.WHITE_WOOL)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.getPlayer().getLocation().getBlock().getType() == Material.WATER) {
            Brawler b = BrawlBox.gm.players.get(e.getPlayer().getUniqueId());
            b.kill();
        }
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e) {
        e.setCancelled(true);
    }


}
