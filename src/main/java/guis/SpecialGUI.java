package guis;

import crucial.Brawler;
import main.BrawlBox;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import util.ItemBuilder;
import util.ValueHolder;
import util.inventory.ClickableItem;
import util.inventory.SmartInventory;
import util.inventory.content.InventoryContents;
import util.inventory.content.InventoryProvider;

import java.util.Arrays;

public class SpecialGUI implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder().id("shop").provider(new SpecialGUI())
            .size(4, 9).title("" + ValueHolder.tacc("&9Shop > Special"))
            .manager(BrawlBox.INSTANCE.inventoryManager).closeable(true).build();


    @Override
    public void init(Player player, InventoryContents contents) {
        Brawler b = BrawlBox.gm.players.get(player.getUniqueId());

        contents.fill(ClickableItem.empty(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ").build()));

        contents.set(1, 2, ClickableItem.of(new ItemBuilder(Material.SHIELD).name("&aShield").lore(Arrays.asList("", "&7Price: &6300$", "", "&7Curve your enemies in style", "", "&a&lUNCOMMON TOOL")).build(), e -> {
            if (b.canBuy(300)) {
                if (player.getInventory().contains(Material.SHIELD)) {
                    player.sendMessage(ValueHolder.tacc("&cYou already bought this."));
                } else {
                    player.sendMessage(ValueHolder.tacc("&aYou purchased a &6Shield&a."));
                    b.buy(300);
                    player.getInventory().addItem(ValueHolder.shield);
                }
            } else {
                player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
            }
        }));

        contents.set(1, 3, ClickableItem.of(new ItemBuilder(Material.STICK).name("&aKnockback Stick").amount(1).lore(Arrays.asList("", "&7Price: &6100$", "", "&a&lUNCOMMON WEAPON")).build(), e -> {
            if (b.canBuy(100)) {
                if (player.getInventory().contains(Material.STICK)) {
                    player.sendMessage(ValueHolder.tacc("&cYou already bought this."));
                } else {
                    player.sendMessage(ValueHolder.tacc("&aYou purchased a &6Knockback Stick&a."));
                    b.buy(100);
                    player.getInventory().addItem(ValueHolder.kbStick);
                }
            } else {
                player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
            }

        }));


        contents.set(1, 4, ClickableItem.of(new ItemBuilder(Material.IRON_AXE).name("&dJarnbjorn").amount(1).lore(Arrays.asList("", "&7Price: &6300$", "", "&7Throw this axe to deal", "&7massive damage.", "", "&d&k&lK &r&d&l&lMYTHIC WEAPON &d&k&lK")).build(), e -> {
            if (b.canBuy(300)) {
                if (player.getInventory().contains(Material.IRON_AXE)) {
                    player.sendMessage(ValueHolder.tacc("&cYou already bought this."));
                } else {
                    player.sendMessage(ValueHolder.tacc("&aYou purchased a &6Jarnbjorn&a."));
                    b.buy(300);
                    player.getInventory().addItem(ValueHolder.jarnbjorn);
                }
            } else {
                player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
            }
        }));

        contents.set(1, 5, ClickableItem.of(new ItemBuilder(Material.DIAMOND_HOE).name("&6Reaper's Scythe").amount(1).lore(Arrays.asList("", "&7Price: &6400$", "", "&7You'll become your enemies", "&7worst nightmare...", "", "&6&lLEGENDARY WEAPON")).build(), e -> {

            if (b.canBuy(400)) {
                if (player.getInventory().contains(Material.DIAMOND_HOE)) {
                    player.sendMessage(ValueHolder.tacc("&cYou already bought this."));
                } else {
                    player.sendMessage(ValueHolder.tacc("&aYou purchased a &6Reaper Scythe&a."));
                    b.buy(400);
                    player.getInventory().addItem(ValueHolder.reaperScythe);
                }
            } else {
                player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
            }

        }));
        contents.set(1, 6, ClickableItem.of(new ItemBuilder(Material.BLAZE_ROD).name("&5Safety Platform").amount(1).lore(Arrays.asList("", "&7Price: &6200$", "", "&7Haha N00B.. You really", "&7need a sAfeTy PlAtFoRM?!?!", "", "&5&lEPIC TOOL")).build(), e -> {
            if (b.canBuy(200)) {
                if (player.getInventory().contains(Material.BLAZE_ROD)) {
                    player.sendMessage(ValueHolder.tacc("&cYou already bought this."));
                } else {
                    player.sendMessage(ValueHolder.tacc("&aYou purchased a &6Safety Platform&a."));
                    b.buy(200);
                    player.getInventory().addItem(ValueHolder.safetyPlatform);
                }
            } else {
                player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
            }
        }));

        contents.set(2, 4, ClickableItem.of(new ItemBuilder(Material.BOW).name("&aHotkeyyy Bow").lore(Arrays.asList("&7Explosive I", "", "&7Price: &6500$", "", "&7&oAww.. Man...", "", "&7Shoots explosive projectiles", "&7that damage your enemies", "&7and launches them in the air.", "", "&a&lUNCOMMON BOW")).build(), e -> {
            if (b.canBuy(500)) {
                player.getInventory().addItem(ValueHolder.creeperBow);
                player.getInventory().setItem(20,new ItemStack(Material.ARROW, 3));
                b.buy(500);
            }else {
                player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
            }
        }));

        contents.set(3, 0, ClickableItem.of(new ItemBuilder(Material.ARROW).name("&cBack").build(), e -> {
            ShopGUI.INVENTORY.open(player);
        }));
    }
}
