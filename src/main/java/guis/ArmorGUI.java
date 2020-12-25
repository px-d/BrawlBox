package guis;

import crucial.Brawler;
import enums.Lang;
import main.BrawlBox;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import util.ItemBuilder;
import util.LangDE;
import util.LangEN;
import util.ValueHolder;
import util.inventory.ClickableItem;
import util.inventory.SmartInventory;
import util.inventory.content.InventoryContents;
import util.inventory.content.InventoryProvider;

import java.util.Arrays;

public class ArmorGUI implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder().id("shop").provider(new ArmorGUI())
            .size(4, 9).title("" + ValueHolder.tacc("&9Shop > Armor"))
            .manager(BrawlBox.INSTANCE.inventoryManager).closeable(true).build();


    @Override
    public void init(Player player, InventoryContents contents) {
        Brawler b = BrawlBox.gm.players.get(player.getUniqueId());

        contents.fill(ClickableItem.empty(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ").build()));

        if (b.lang == Lang.EN) {
            contents.set(1, 2, ClickableItem.of(new ItemBuilder(LangEN.chestPlus).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).build(), e -> {
                if (b.canBuy(100)) {
                    player.getInventory().addItem(ValueHolder.chestPlus);
                    b.buy(100);
                }
            }));
            contents.set(1, 3, ClickableItem.of(new ItemBuilder(LangEN.ironChest).flags(ItemFlag.HIDE_ATTRIBUTES).build(), e -> {
                if (b.canBuy(300)) {
                    player.getInventory().addItem(ValueHolder.ironChest);
                    b.buy(300);
                }
            }));
            contents.set(1, 5, ClickableItem.of(new ItemBuilder(LangEN.chestPlusPlus).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).build(), e -> {
                if (b.canBuy(400)) {
                    player.getInventory().addItem(ValueHolder.chestPlusPlus);
                    b.buy(400);
                }
            }));
            contents.set(1, 6, ClickableItem.of(new ItemBuilder(LangEN.diamondChest).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 4).flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).build(), e -> {
                if (b.canBuy(800)) {
                    player.getInventory().addItem(ValueHolder.diamondChest);
                    b.buy(800);
                }
            }));
            contents.set(3, 0, ClickableItem.of(new ItemBuilder(Material.ARROW).name("&cBack").build(), e -> ShopGUI.INVENTORY.open(player)));
        }
        if (b.lang == Lang.DE) {
            contents.set(1, 2, ClickableItem.of(new ItemBuilder(LangDE.chestPlus).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).build(), e -> {
                if (b.canBuy(100)) {
                    player.getInventory().addItem(ValueHolder.chestPlus);
                    b.buy(100);
                }
            }));
            contents.set(1, 3, ClickableItem.of(new ItemBuilder(LangDE.ironChest).flags(ItemFlag.HIDE_ATTRIBUTES).build(), e -> {
                if (b.canBuy(300)) {
                    player.getInventory().addItem(ValueHolder.ironChest);
                    b.buy(300);
                }
            }));
            contents.set(1, 5, ClickableItem.of(new ItemBuilder(LangDE.chestPlusPlus).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).build(), e -> {
                if (b.canBuy(400)) {
                    player.getInventory().addItem(ValueHolder.chestPlusPlus);
                    b.buy(400);
                }
            }));
            contents.set(1, 6, ClickableItem.of(new ItemBuilder(LangDE.diamondChest).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 4).flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).build(), e -> {
                if (b.canBuy(800)) {
                    player.getInventory().addItem(ValueHolder.diamondChest);
                    b.buy(800);
                }
            }));
            contents.set(3, 0, ClickableItem.of(new ItemBuilder(Material.ARROW).name("&cZur\u00fcck").build(), e -> ShopGUI.INVENTORY.open(player)));
        }

    }
}
