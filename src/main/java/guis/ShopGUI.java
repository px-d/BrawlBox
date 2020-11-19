package guis;

import main.BrawlBox;
import org.bukkit.entity.Player;
import util.ValueHolder;
import util.inventory.SmartInventory;
import util.inventory.content.InventoryContents;
import util.inventory.content.InventoryProvider;

public class ShopGUI implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder().id("shop").provider(new TeamGUI())
            .size(3, 9).title("" + ValueHolder.tacc("&9Shop:"))
            .manager(BrawlBox.INSTANCE.inventoryManager).closeable(true).build();

    @Override
    public void init(Player player, InventoryContents contents) {



    }
}
