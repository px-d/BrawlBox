package guis;

import crucial.Brawler;
import main.BrawlBox;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import util.ItemBuilder;
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

        contents.set(1, 2, ClickableItem.of(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).name("&fChestplate&c+").addLore(Arrays.asList("", "&7Price: &6100$", "", "&f&lCOMMON CHESTPLATE")).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).flags(ItemFlag.HIDE_ATTRIBUTES).build(), e -> {
            if (b.canBuy(100)) {
                player.getInventory().addItem(ValueHolder.chestPlus);
            }
        }));

        contents.set(1, 3, ClickableItem.of(new ItemBuilder(Material.IRON_CHESTPLATE).name("&aIron Chestplate").addLore(Arrays.asList("", "&7Price: &6300$", "", "&a&lUNCOMMON CHESTPLATE")).flags(ItemFlag.HIDE_ATTRIBUTES).build(), e -> {
            if (b.canBuy(300)) {
                player.getInventory().addItem(ValueHolder.ironChest);
            }
        }));


        contents.set(1, 5, ClickableItem.of(new ItemBuilder(Material.GOLDEN_CHESTPLATE).name("&9Chestplate&c++").addLore(Arrays.asList("", "&7Price: &6400$", "", "&9&lRARE CHESTPLATE")).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).flags(ItemFlag.HIDE_ATTRIBUTES).build(), e -> {
            if (b.canBuy(400)) {
                player.getInventory().addItem(ValueHolder.chestPlusPlus);
            }
        }));

        contents.set(1, 6, ClickableItem.of(new ItemBuilder(Material.DIAMOND_CHESTPLATE).name("&5Diamond Chestplate").addLore(Arrays.asList("", "&7Price: &6800$", "", "&5&lEPIC CHESTPLATE")).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 4).flags(ItemFlag.HIDE_ATTRIBUTES).build(), e -> {
            if (b.canBuy(800)) {
                player.getInventory().addItem(ValueHolder.diamondChest);
            }
        }));


        contents.set(3, 0, ClickableItem.of(new ItemBuilder(Material.ARROW).name("&cBack").build(), e -> {
            ShopGUI.INVENTORY.open(player);
        }));
    }
}
