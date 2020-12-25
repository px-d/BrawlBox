package events;

import crucial.Brawler;
import enums.State;
import guis.ShopGUI;
import guis.TeamGUI;
import main.BrawlBox;
import main.GameManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import util.ActionBarAPI;
import util.ValueHolder;

public class GeneralEvents implements Listener {


    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        if (e.getBlockAgainst().getType() != Material.SEA_LANTERN && e.getBlock().getType().toString().contains("CONCRETE")) {
            e.setCancelled(true);
            ActionBarAPI.abToPlayer("&cYou can only place this block in the middle.", e.getPlayer());
        }
        if (e.getBlockAgainst().getType() == Material.SEA_LANTERN && !e.getBlock().getType().toString().contains("CONCRETE")) {
            e.setCancelled(true);
            ActionBarAPI.abToPlayer("&cYou can only place Team Blocks here!", e.getPlayer());
        }

        if (BrawlBox.gm.state == State.PREP || BrawlBox.gm.state == State.LOBBY) {
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

            Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, x -> {
                if (!e.getBlock().getLocation().getBlock().getType().toString().contains("CONCRETE")) {
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

        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (e.getEntity() instanceof Player) {
                if (((Player) e.getEntity()).getHealth() - e.getDamage() <= 0) {
                    e.setDamage(0);
                    BrawlBox.gm.players.get(e.getEntity().getUniqueId()).kill();
                }
            }
        }

        if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            e.setCancelled(true);
        }

    }


    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        if (BrawlBox.gm.state != State.LOBBY) {
            if (e.getPlayer().isOp() || e.getPlayer().hasPermission("bb.spectate")) {
                e.getPlayer().setGameMode(GameMode.SPECTATOR);
                e.getPlayer().teleport(ValueHolder.center);
                e.getPlayer().sendMessage(ValueHolder.tacc("&cYou joined the game as spectator because it was already running."));
            } else {
                e.disallow(PlayerLoginEvent.Result.KICK_FULL, "Game is already running.");
            }
        }
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractAtEntityEvent e) {

        if (BrawlBox.gm.state == State.PREP) {
            if (e.getRightClicked().getType() == EntityType.VILLAGER) {
                e.setCancelled(true);
                ShopGUI.INVENTORY.open(e.getPlayer());
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.2f, 0.2f);
            }
        }


    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock().getType().toString().contains("TRAPDOOR") || e.getClickedBlock().getType().toString().contains("FENCE_GATE")) {
                e.setCancelled(true);
            }
        }
        if (BrawlBox.gm.state == State.LOBBY) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getItem() != null) {
                    if (e.getItem().getType() == Material.CHEST) {
                        e.setCancelled(true);
                        TeamGUI.INVENTORY.open(e.getPlayer());
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_CHEST_LOCKED, 0.2f, 1f);
                    }
                }
            }
        }
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock() == null) {
                return;
            }
            if (e.getClickedBlock().getType() == Material.ANVIL) {
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
                BrawlBox.gm.openTeamInv(e.getPlayer());
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
        if (e.getSlot() == 40) {
            e.setCancelled(true);
        }
        if (BrawlBox.gm.state == State.PREP) {
            if (e.getClick() == ClickType.NUMBER_KEY) {
                e.setCancelled(true);
            }
        }

        if (e.getCurrentItem() != null) {

            if (e.getCurrentItem().getType().toString().toLowerCase().contains("helm") || e.getCurrentItem().getType().toString().toLowerCase().contains("boots") || e.getCurrentItem().getType().toString().toLowerCase().contains("leggings")) {
                e.setCancelled(true);
            }

            if (BrawlBox.gm.state == State.LOBBY) {
                e.setCancelled(true);
            }

            if (BrawlBox.gm.state == State.PREP) {

                if (e.getInventory() == GameManager.blue_inv || e.getInventory() == GameManager.red_inv) {
                    if (e.getCurrentItem().getType().toString().toLowerCase().contains("concrete") || e.getCurrentItem().getType().toString().toLowerCase().contains("pickaxe")) {
                        e.setCancelled(true);
                    }
                }

                if (e.getCurrentItem().getType().toString().toLowerCase().contains("sword")) {
                    e.setCancelled(true);
                    return;
                }
            }

            if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
                if (e.getClick() == ClickType.RIGHT) {
                    BrawlBox.gm.openTeamInv((Player) e.getWhoClicked());
                }
                e.setCancelled(true);
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
            if (BrawlBox.gm.players.containsKey(e.getPlayer().getUniqueId())) {
                Brawler b = BrawlBox.gm.players.get(e.getPlayer().getUniqueId());
                b.kill();
            }
        }
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e) {
        e.setCancelled(true);
    }


}
