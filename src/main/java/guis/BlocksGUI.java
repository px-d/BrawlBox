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

public class BlocksGUI implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder().id("shop").provider(new BlocksGUI())
            .size(4, 9).title("" + ValueHolder.tacc("&9Shop > Blocks"))
            .manager(BrawlBox.INSTANCE.inventoryManager).closeable(true).build();


    @Override
    public void init(Player player, InventoryContents contents) {
        Brawler b = BrawlBox.gm.players.get(player.getUniqueId());

        contents.fill(ClickableItem.empty(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ").build()));

        contents.set(1, 3, ClickableItem.of(new ItemBuilder(Material.SMOOTH_SANDSTONE).amount(12).name("&fSandstone").addLore(Arrays.asList("", "&7Price: &6100$", "", "&f&lCOMMON BUILDING BLOCK")).build(), e -> {
            if (b.canBuy(100)) {
                player.getInventory().addItem(new ItemStack(Material.SMOOTH_SANDSTONE, 12));
                b.buy(100);
            }
        }));

        contents.set(1, 4, ClickableItem.of(new ItemBuilder(Material.END_STONE).amount(6).name("&aEndstone").addLore(Arrays.asList("", "&7Price: &6100$", "", "&a&lUNCOMMON BUILDING BLOCK")).build(), e -> {
            if (b.canBuy(100)) {
                player.getInventory().addItem(new ItemStack(Material.END_STONE, 6));
                b.buy(100);
            }
        }));


        contents.set(1, 5, ClickableItem.of(new ItemBuilder(Material.WHITE_WOOL).amount(4).name("&9Wool").addLore(Arrays.asList("", "&7Price: &6200$", "", "&9&lRARE BUILDING BLOCK")).build(), e -> {
            if (b.canBuy(200)) {
                player.getInventory().addItem(new ItemStack(Material.WHITE_WOOL, 4));
                b.buy(200);
            }
        }));


        contents.set(3, 0, ClickableItem.of(new ItemBuilder(Material.ARROW).name("&cBack").build(), e -> {
            ShopGUI.INVENTORY.open(player);
        }));
    }
}
