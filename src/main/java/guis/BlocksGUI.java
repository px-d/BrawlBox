package guis;

import crucial.Brawler;
import enums.Lang;
import main.BrawlBox;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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

public class BlocksGUI implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder().id("shop").provider(new BlocksGUI())
            .size(4, 9).title("" + ValueHolder.tacc("&9Shop > Blocks"))
            .manager(BrawlBox.INSTANCE.inventoryManager).closeable(true).build();


    @Override
    public void init(Player player, InventoryContents contents) {
        Brawler b = BrawlBox.gm.players.get(player.getUniqueId());

        contents.fill(ClickableItem.empty(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ").build()));


        if (b.lang == Lang.EN) {
            contents.set(1, 3, ClickableItem.of(LangEN.sandstone, e -> {
                if (b.canBuy(200)) {
                    player.getInventory().addItem(new ItemStack(Material.SMOOTH_SANDSTONE, 8));
                    b.buy(200);
                }
            }));

            contents.set(1, 4, ClickableItem.of(LangEN.endstone, e -> {
                if (b.canBuy(150)) {
                    player.getInventory().addItem(new ItemStack(Material.END_STONE, 6));
                    b.buy(150);
                }
            }));


            contents.set(1, 5, ClickableItem.of(LangEN.wool, e -> {
                if (b.canBuy(200)) {
                    player.getInventory().addItem(new ItemStack(Material.WHITE_WOOL, 4));
                    b.buy(200);
                }
            }));


            contents.set(3, 0, ClickableItem.of(new ItemBuilder(Material.ARROW).name("&cZur\u00fcck").build(), e -> {
                ShopGUI.INVENTORY.open(player);
            }));
        } else {
            contents.set(1, 3, ClickableItem.of(LangDE.sandstone, e -> {
                if (b.canBuy(200)) {
                    player.getInventory().addItem(new ItemStack(Material.SMOOTH_SANDSTONE, 8));
                    b.buy(200);
                }
            }));

            contents.set(1, 4, ClickableItem.of(LangDE.endstone, e -> {
                if (b.canBuy(150)) {
                    player.getInventory().addItem(new ItemStack(Material.END_STONE, 6));
                    b.buy(150);
                }
            }));


            contents.set(1, 5, ClickableItem.of(LangDE.wool, e -> {
                if (b.canBuy(200)) {
                    player.getInventory().addItem(new ItemStack(Material.WHITE_WOOL, 4));
                    b.buy(200);
                }
            }));


            contents.set(3, 0, ClickableItem.of(new ItemBuilder(Material.ARROW).name("&cZur\u00fcck").build(), e -> {
                ShopGUI.INVENTORY.open(player);
            }));
        }


    }
}
