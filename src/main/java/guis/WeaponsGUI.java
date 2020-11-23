package guis;

import crucial.Brawler;
import main.BrawlBox;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import util.ItemBuilder;
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

        contents.set(1, 1, ClickableItem.of(new ItemBuilder(Material.WOODEN_SWORD).name("&fWooden Sword").flags(ItemFlag.HIDE_ATTRIBUTES).addLore(Arrays.asList("", "&7Cost: &6FREE", "", "&f&lCOMMON SWORD")).build(), e -> {
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

        contents.set(1, 3, ClickableItem.of(new ItemBuilder(Material.STONE_SWORD).name("&aStone Sword").flags(ItemFlag.HIDE_ATTRIBUTES).addLore(Arrays.asList("", "&7Cost: &6100$", "", "&a&lUNCOMMON SWORD")).build(), e -> {
            if (player.getInventory().getItem(0).getType() == Material.WOODEN_SWORD) {
                if (b.canBuy(100)) {
                    b.buy(100);
                    player.sendMessage(ValueHolder.tacc("&aYou purchased &6Stone Sword&a."));
                    player.getInventory().setItem(0, ValueHolder.stone_sword);
                }else{
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



        contents.set(1, 5, ClickableItem.of(new ItemBuilder(Material.GOLDEN_SWORD).name("&9Golden Sword").flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).enchant(Enchantment.DAMAGE_ALL, 1).addLore(Arrays.asList("", "&7Cost: &6300$", "", "&9&lRARE SWORD")).build(), e -> {
            if (player.getInventory().getItem(0).getType() == Material.WOODEN_SWORD) {
                if (b.canBuy(300)) {
                    b.buy(300);
                    player.sendMessage(ValueHolder.tacc("&aYou purchased &6Golden Sword&a."));
                    player.getInventory().setItem(0, ValueHolder.golden_sword);
                }else{
                    player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
                }
            } else if (player.getInventory().getItem(0).getType() == Material.STONE_SWORD) {
                if (b.canBuy(200)) {
                    b.buy(200);
                    player.sendMessage(ValueHolder.tacc("&aYou purchased &6Golden Sword&a."));
                    player.getInventory().setItem(0, ValueHolder.golden_sword);
                }else{
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

        contents.set(1, 7, ClickableItem.of(new ItemBuilder(Material.IRON_SWORD).name("&5Iron Sword").flags(ItemFlag.HIDE_ATTRIBUTES).addLore(Arrays.asList("", "&7Cost: &6500$", "", "&5&lEPIC SWORD")).build(), e -> {
            if (player.getInventory().getItem(0).getType() == Material.WOODEN_SWORD) {
                if (b.canBuy(500)) {
                    b.buy(500);
                    player.sendMessage(ValueHolder.tacc("&aYou purchased &6Iron Sword&a."));
                    player.getInventory().setItem(0, ValueHolder.iron_sword);
                }else{
                    player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
                }
            } else if (player.getInventory().getItem(0).getType() == Material.STONE_SWORD) {
                if (b.canBuy(400)) {
                    b.buy(400);
                    player.sendMessage(ValueHolder.tacc("&aYou purchased &6Iron Sword&a."));
                    player.getInventory().setItem(0, ValueHolder.iron_sword);
                }else{
                    player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
                }
            } else if (player.getInventory().getItem(0).getType() == Material.GOLDEN_SWORD) {
                if (b.canBuy(200)) {
                    b.buy(200);
                    player.sendMessage(ValueHolder.tacc("&aYou purchased &6Iron Sword&a."));
                    player.getInventory().setItem(0, ValueHolder.iron_sword);
                }else{
                    player.sendMessage(ValueHolder.tacc("&cYou don't have enough coins."));
                }
            } else if (player.getInventory().getItem(0).getType() == Material.IRON_SWORD) {
                player.sendMessage(ValueHolder.tacc("&cYou already own this item."));
            }
        }));

        contents.set(3, 0, ClickableItem.of(new ItemBuilder(Material.ARROW).name("&cBack").build(), e -> {
            ShopGUI.INVENTORY.open(player);
        }));
    }
}
