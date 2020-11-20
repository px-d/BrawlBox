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
import util.inventory.content.Pagination;
import util.inventory.content.SlotIterator;

import java.util.Arrays;

public class ShopGUI implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder().id("shop").provider(new ShopGUI())
            .size(4, 9).title("" + ValueHolder.tacc("&9Shop"))
            .manager(BrawlBox.INSTANCE.inventoryManager).closeable(true).build();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fill(ClickableItem.empty(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ").build()));

        contents.set(1, 1, ClickableItem.of(new ItemBuilder(Material.IRON_SWORD).name("&aWeapons").addLore(Arrays.asList("", "&7Browse all Weapons")).flags(ItemFlag.HIDE_ATTRIBUTES).build(), e -> {
            WeaponsGUI.INVENTORY.open(player);
        }));
        contents.set(1, 3, ClickableItem.of(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).name("&aArmor").addLore(Arrays.asList("", "&7Browse all Chestplates")).flags(ItemFlag.HIDE_ATTRIBUTES).build(), e -> {
            ArmorGUI.INVENTORY.open(player);
        }));

        contents.set(1, 5, ClickableItem.of(new ItemBuilder(Material.SANDSTONE).name("&aBlocks").addLore(Arrays.asList("", "&7Browse all Blocks")).flags(ItemFlag.HIDE_ATTRIBUTES).build(), e -> {
            BlocksGUI.INVENTORY.open(player);
        }));

        contents.set(1, 7, ClickableItem.of(new ItemBuilder(Material.BLAZE_ROD).name("&aSpecial").addLore(Arrays.asList("", "&7Browse all Special Items")).flags(ItemFlag.HIDE_ATTRIBUTES).build(), e -> {
            SpecialGUI.INVENTORY.open(player);
        }));

    }
}
