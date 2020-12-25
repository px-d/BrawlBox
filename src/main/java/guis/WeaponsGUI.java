package guis;

import crucial.Brawler;
import enums.Lang;
import main.BrawlBox;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import util.ItemBuilder;
import util.LangDE;
import util.LangEN;
import util.ValueHolder;
import util.inventory.ClickableItem;
import util.inventory.SmartInventory;
import util.inventory.content.InventoryContents;
import util.inventory.content.InventoryProvider;

import java.util.Arrays;

public class WeaponsGUI implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder().id("shop").provider(new WeaponsGUI())
            .size(4, 9).title("" + ValueHolder.tacc("&9Shop > Weapons"))
            .manager(BrawlBox.INSTANCE.inventoryManager).closeable(true).build();


    @Override
    public void init(Player player, InventoryContents contents) {
        Brawler b = BrawlBox.gm.players.get(player.getUniqueId());
        contents.fill(ClickableItem.empty(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ").build()));

        ItemStack wood_sword = b.lang == Lang.EN ? LangEN.wooden_sword : LangDE.wooden_sword;
        ItemStack stone_sword = b.lang == Lang.EN ? LangEN.stone_sword : LangDE.stone_sword;
        ItemStack gold_sword = b.lang == Lang.EN ? LangEN.golden_sword : LangDE.golden_sword;
        ItemStack iron_sword = b.lang == Lang.EN ? LangEN.iron_sword : LangDE.iron_sword;

        contents.set(1, 1, ClickableItem.of(wood_sword, e -> {
            if (player.getInventory().getItem(0).getType() == Material.WOODEN_SWORD) {
                player.sendMessage(ValueHolder.tacc("&cYou already own this item."));
            } else if (player.getInventory().getItem(0).getType() == Material.STONE_SWORD) {
                b.addCoins(100);
                player.sendMessage(ValueHolder.tacc("&aYou got a refund of &6100 Coins&a."));
                player.getInventory().setItem(0, ValueHolder.wooden_sword);
            } else if (player.getInventory().getItem(0).getType() == Material.GOLDEN_SWORD) {
                b.addCoins(300);
                player.sendMessage(ValueHolder.tacc("&aYou got a refund of &6300 Coins&a."));
                player.getInventory().setItem(0, ValueHolder.wooden_sword);
            } else if (player.getInventory().getItem(0).getType() == Material.IRON_SWORD) {
                b.addCoins(500);
                player.sendMessage(ValueHolder.tacc("&aYou got a refund of &6500 Coins&a."));
                player.getInventory().setItem(0, ValueHolder.wooden_sword);
            }
        }));

        contents.set(1, 3, ClickableItem.of(stone_sword, e -> {
            if (player.getInventory().getItem(0).getType() == Material.WOODEN_SWORD) {
                if (b.canBuy(100)) {
                    b.buy(100);
                    player.sendMessage(ValueHolder.tacc("&aYou purchased &6Stone Sword&a."));
                    player.getInventory().setItem(0, ValueHolder.stone_sword);
                } else {
                    player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
                }
            } else if (player.getInventory().getItem(0).getType() == Material.STONE_SWORD) {
                player.sendMessage(ValueHolder.tacc("&cYou already own this item."));
            } else if (player.getInventory().getItem(0).getType() == Material.GOLDEN_SWORD) {

                b.addCoins(200);
                player.sendMessage(ValueHolder.tacc("&aYou got a refund of &6200 Coins&a."));
                player.getInventory().setItem(0, ValueHolder.stone_sword);


            } else if (player.getInventory().getItem(0).getType() == Material.IRON_SWORD) {
                b.addCoins(400);
                player.sendMessage(ValueHolder.tacc("&aYou got a refund of &6400 Coins&a."));
                player.getInventory().setItem(0, ValueHolder.stone_sword);
            }
        }));


        contents.set(1, 5, ClickableItem.of(gold_sword, e -> {
            if (player.getInventory().getItem(0).getType() == Material.WOODEN_SWORD) {
                if (b.canBuy(300)) {
                    b.buy(300);
                    player.sendMessage(ValueHolder.tacc("&aYou purchased &6Golden Sword&a."));
                    player.getInventory().setItem(0, ValueHolder.golden_sword);
                } else {
                    player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
                }
            } else if (player.getInventory().getItem(0).getType() == Material.STONE_SWORD) {
                if (b.canBuy(200)) {
                    b.buy(200);
                    player.sendMessage(ValueHolder.tacc("&aYou purchased &6Golden Sword&a."));
                    player.getInventory().setItem(0, ValueHolder.golden_sword);
                } else {
                    player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
                }
            } else if (player.getInventory().getItem(0).getType() == Material.GOLDEN_SWORD) {
                player.sendMessage(ValueHolder.tacc("&cYou already own this item."));
            } else if (player.getInventory().getItem(0).getType() == Material.IRON_SWORD) {
                b.addCoins(200);
                player.sendMessage(ValueHolder.tacc("&aYou got a refund of &6200 Coins&a."));
                player.getInventory().setItem(0, ValueHolder.golden_sword);
            }
        }));

        contents.set(1, 7, ClickableItem.of(iron_sword, e -> {
            if (player.getInventory().getItem(0).getType() == Material.WOODEN_SWORD) {
                if (b.canBuy(500)) {
                    b.buy(500);
                    player.sendMessage(ValueHolder.tacc("&aYou purchased &6Iron Sword&a."));
                    player.getInventory().setItem(0, ValueHolder.iron_sword);
                } else {
                    player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
                }
            } else if (player.getInventory().getItem(0).getType() == Material.STONE_SWORD) {
                if (b.canBuy(400)) {
                    b.buy(400);
                    player.sendMessage(ValueHolder.tacc("&aYou purchased &6Iron Sword&a."));
                    player.getInventory().setItem(0, ValueHolder.iron_sword);
                } else {
                    player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
                }
            } else if (player.getInventory().getItem(0).getType() == Material.GOLDEN_SWORD) {
                if (b.canBuy(200)) {
                    b.buy(200);
                    player.sendMessage(ValueHolder.tacc("&aYou purchased &6Iron Sword&a."));
                    player.getInventory().setItem(0, ValueHolder.iron_sword);
                } else {
                    player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
                }
            } else if (player.getInventory().getItem(0).getType() == Material.IRON_SWORD) {
                player.sendMessage(ValueHolder.tacc("&cYou already own this item."));
            }
        }));


        if(b.lang == Lang.EN){
            contents.set(3, 0, ClickableItem.of(new ItemBuilder(Material.ARROW).name("&cBack").build(), e -> {
                ShopGUI.INVENTORY.open(player);
            }));
        }else{
            contents.set(3, 0, ClickableItem.of(new ItemBuilder(Material.ARROW).name("&cZur\u00fcck").build(), e -> {
                ShopGUI.INVENTORY.open(player);
            }));
        }

    }
}
