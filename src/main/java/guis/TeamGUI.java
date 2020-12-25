package guis;

import crucial.Brawler;
import enums.Lang;
import enums.Teams;
import main.BrawlBox;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import util.ItemBuilder;
import util.ValueHolder;
import util.inventory.ClickableItem;
import util.inventory.SmartInventory;
import util.inventory.content.InventoryContents;
import util.inventory.content.InventoryProvider;

public class TeamGUI implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder().id("teamSelect").provider(new TeamGUI())
            .size(3, 9).title("" + ValueHolder.tacc("&9Teams:"))
            .manager(BrawlBox.INSTANCE.inventoryManager).closeable(true).build();

    @Override
    public void init(Player player, InventoryContents contents) {
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        int state = contents.property("state", 0);

        int red_players = BrawlBox.gm.getRedPlayers().size();
        int blue_players = BrawlBox.gm.getBluePlayers().size();
        Brawler brawler = BrawlBox.gm.players.get(player.getUniqueId());
        contents.setProperty("state", state + 1);

        if (state % 5 != 0) {
            return;
        }

        ItemStack red = brawler.lang == Lang.EN ? new ItemBuilder(Material.RED_WOOL).name("&cTeam Red").lore(BrawlBox.gm.getRedPlayers()).build() : new ItemBuilder(Material.RED_WOOL).name("&cTeam Rot").lore(BrawlBox.gm.getRedPlayers()).build();
        ItemStack blue = brawler.lang == Lang.EN ? new ItemBuilder(Material.BLUE_WOOL).name("&9Team Blue").lore(BrawlBox.gm.getBluePlayers()).build() : new ItemBuilder(Material.BLUE_WOOL).name("&9Team Blau").lore(BrawlBox.gm.getBluePlayers()).build();

        //JOIN RED TEAM IF FAIR ENOUGH
        contents.set(1, 2, ClickableItem.of(red, e -> {

            if (blue_players == 3 && red_players == 3) {
                if (brawler.lang == Lang.EN) {
                    player.kickPlayer("Something went wrong while joining a team.");
                } else {
                    player.kickPlayer("Es ist ein Fehler aufgetreten bei dem Versuch einem Team beizutreten.");
                }
                return;
            }

            if (brawler.getTeam() == Teams.RED) {
                if (brawler.lang == Lang.EN) {
                    player.sendMessage(ValueHolder.tacc("&cYou are already in that team."));
                } else {
                    player.sendMessage(ValueHolder.tacc("&cDu bist schon in diesem Team."));
                }

                return;
            }

            if (red_players == blue_players) {
                if (brawler.getTeam() == Teams.BLUE) {
                    if (brawler.lang == Lang.EN) {
                        player.sendMessage(ValueHolder.tacc("&cYou can't join that team."));
                    } else {
                        player.sendMessage(ValueHolder.tacc("&cDu kannst diesem Team nicht beitreten."));
                    }
                } else {
                    brawler.setTeam(Teams.RED);

                    if (brawler.lang == Lang.EN) {
                        player.sendMessage(ValueHolder.tacc("&aYou joined &cTeam Red&a."));
                    } else {
                        player.sendMessage(ValueHolder.tacc("&aDu bist &cTeam Rot &abeigetreten."));
                    }

                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 1f);
                }

                return;
            }

            if (red_players > blue_players) {
                if (brawler.lang == Lang.EN) {
                    player.sendMessage(ValueHolder.tacc("&cYou can't join that team."));
                } else {
                    player.sendMessage(ValueHolder.tacc("&cDu kannst diesem Team nicht beitreten."));
                }
                return;
            }
            brawler.setTeam(Teams.RED);
            if (brawler.lang == Lang.EN) {
                player.sendMessage(ValueHolder.tacc("&aYou joined &cTeam Red&a."));
            } else {
                player.sendMessage(ValueHolder.tacc("&aDu bist &cTeam Rot &abeigetreten."));
            }
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 1f);
            return;
        }));

        //JOIN BLUE TEAM IF FAIR ENOUGH
        contents.set(1, 6, ClickableItem.of(blue, e -> {
            if (red_players == 3 && blue_players == 3) {
                if (brawler.lang == Lang.EN) {
                    player.kickPlayer("Something went wrong while joining a team.");
                } else {
                    player.kickPlayer("Es ist ein Fehler aufgetreten bei dem Versuch einem Team beizutreten.");
                }
                return;
            }

            if (brawler.getTeam() == Teams.BLUE) {
                if (brawler.lang == Lang.EN) {
                    player.sendMessage(ValueHolder.tacc("&cYou are already in that team."));
                } else {
                    player.sendMessage(ValueHolder.tacc("&cDu bist schon in diesem Team."));
                }
                return;
            }

            if (blue_players == red_players) {
                if (brawler.getTeam() == Teams.RED) {
                    if (brawler.lang == Lang.EN) {
                        player.sendMessage(ValueHolder.tacc("&cYou can't join that team."));
                    } else {
                        player.sendMessage(ValueHolder.tacc("&cDu kannst diesem Team nicht beitreten."));
                    }
                } else {
                    brawler.setTeam(Teams.BLUE);
                    if (brawler.lang == Lang.EN) {
                        player.sendMessage(ValueHolder.tacc("&aYou joined &9Team Blue&a."));
                    } else {
                        player.sendMessage(ValueHolder.tacc("&aDu bist &9Team Blau &abeigetreten."));
                    }

                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 1f);
                }
                return;

            }

            if (blue_players > red_players) {
                if (brawler.lang == Lang.EN) {
                    player.sendMessage(ValueHolder.tacc("&cYou can't join that team."));
                } else {
                    player.sendMessage(ValueHolder.tacc("&cDu kannst diesem Team nicht beitreten."));
                }
                return;
            }
            brawler.setTeam(Teams.BLUE);
            if (brawler.lang == Lang.EN) {
                player.sendMessage(ValueHolder.tacc("&aYou joined &9Team Blue&a."));
            } else {
                player.sendMessage(ValueHolder.tacc("&aDu bist &9Team Blau &abeigetreten."));
            }
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 1f);
            return;
        }));
    }
}
