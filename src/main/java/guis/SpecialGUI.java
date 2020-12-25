package guis;

import crucial.Brawler;
import enums.Lang;
import main.BrawlBox;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import util.*;
import util.inventory.ClickableItem;
import util.inventory.SmartInventory;
import util.inventory.content.InventoryContents;
import util.inventory.content.InventoryProvider;

public class SpecialGUI implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder().id("shop").provider(new SpecialGUI())
            .size(4, 9).title("" + ValueHolder.tacc("&9Shop > Special"))
            .manager(BrawlBox.INSTANCE.inventoryManager).closeable(true).build();


    @Override
    public void init(Player player, InventoryContents contents) {
        Brawler b = BrawlBox.gm.players.get(player.getUniqueId());

        contents.fill(ClickableItem.empty(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ").build()));

        ItemStack kbstick = b.lang == Lang.EN ? LangEN.kbStick : LangDE.kbStick;
        ItemStack explosiveBow = b.lang == Lang.EN ? LangEN.creeperBow : LangDE.creeperBow;
        ItemStack reaperScythe = b.lang == Lang.EN ? LangEN.reaperScythe : LangDE.reaperScythe;
        ItemStack safetyPlatform = b.lang == Lang.EN ? LangEN.safetyPlatform : LangDE.safetyPlatform;
        ItemStack jarnbjorn = b.lang == Lang.EN ? LangEN.frag : LangDE.frag;


//        contents.set(1, 2, ClickableItem.of(new ItemBuilder(Material.SHIELD).name("&aShield").lore(Arrays.asList("", "&7Price: &6300$", "", "&7Curve your enemies in style", "", "&a&lUNCOMMON TOOL")).build(), e -> {
//            if (b.canBuy(300)) {
//                if (player.getInventory().contains(Material.SHIELD)) {
//                    player.sendMessage(ValueHolder.tacc("&cYou already bought this."));
//                } else {
//                    player.sendMessage(ValueHolder.tacc("&aYou purchased a &6Shield&a."));
//                    b.buy(300);
//                    player.getInventory().addItem(ValueHolder.shield);
//                }
//            } else {
//                player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
//            }
//        }));
        contents.set(1, 2, ClickableItem.of(jarnbjorn, e -> {
            if (b.canBuy(300)) {
                if (player.getInventory().contains(Material.IRON_AXE)) {
                    MessageAPI.alreadyHas(b);
                } else {
                    MessageAPI.purchased("Frag Grenade", b);
                    b.buy(300);
                    player.getInventory().addItem(ValueHolder.frag);
                }
            } else {
                MessageAPI.noCoins(b);
            }
        }));

        contents.set(1, 3, ClickableItem.of(kbstick, e -> {
            if (b.canBuy(100)) {
                if (player.getInventory().contains(Material.STICK)) {
                    MessageAPI.alreadyHas(b);
                } else {
                    MessageAPI.purchased("Knockback Stick", b);
                    b.buy(100);
                    player.getInventory().addItem(ValueHolder.kbStick);
                }
            } else {
                MessageAPI.noCoins(b);
            }

        }));

        contents.set(1, 5, ClickableItem.of(reaperScythe, e -> {

//            if (b.canBuy(400)) {
//                if (player.getInventory().contains(Material.DIAMOND_HOE)) {
//                    player.sendMessage(ValueHolder.tacc("&cYou already bought this."));
//                } else {
//                    player.sendMessage(ValueHolder.tacc("&aYou purchased a &6Reaper Scythe&a."));
//                    b.buy(400);
//                    player.getInventory().addItem(ValueHolder.reaperScythe);
//                }
//            } else {
//                player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
//            }

        }));
        contents.set(1, 6, ClickableItem.of(safetyPlatform, e -> {
            if (b.canBuy(200)) {
                if (player.getInventory().contains(Material.BLAZE_ROD)) {
                    MessageAPI.alreadyHas(b);
                } else {
                    MessageAPI.purchased("Safety Platform", b);
                    b.buy(200);
                    player.getInventory().addItem(ValueHolder.safetyPlatform);
                }
            } else {
                MessageAPI.noCoins(b);
            }
        }));

        contents.set(1, 4, ClickableItem.of(explosiveBow, e -> {
            if (b.canBuy(500)) {
                if (player.getInventory().contains(Material.BOW)) {
                    MessageAPI.alreadyHas(b);
                }
                MessageAPI.purchased("Explosive Bow", b);
                player.getInventory().addItem(ValueHolder.creeperBow);
                player.getInventory().setItem(20, new ItemStack(Material.ARROW, 3));
                b.buy(500);
            } else {
                MessageAPI.noCoins(b);
            }
        }));

        contents.set(3, 0, ClickableItem.of(new ItemBuilder(Material.ARROW).name("&cBack").build(), e -> {
            ShopGUI.INVENTORY.open(player);
        }));
    }
}
