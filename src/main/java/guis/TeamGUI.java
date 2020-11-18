package guis;

import enums.Teams;
import main.BrawlBox;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import util.ItemBuilder;
import util.ValueHolder;
import util.inventory.ClickableItem;
import util.inventory.SmartInventory;
import util.inventory.content.InventoryContents;
import util.inventory.content.InventoryProvider;

public class TeamGUI implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder().id("teamSelect").provider(new TeamGUI())
            .size(3, 9).title("" + ValueHolder.tacc("&9Kits:"))
            .manager(BrawlBox.INSTANCE.inventoryManager).closeable(true).build();

    @Override
    public void init(Player player, InventoryContents contents) {
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        int state = contents.property("state", 0);
        contents.setProperty("state", state + 1);

        if(state % 5 != 0){
            return;
        }

        contents.set(1, 2,ClickableItem.of(new ItemBuilder(Material.RED_WOOL).name(ValueHolder.tacc("&cTeam Red")).lore(BrawlBox.gm.getRedPlayers()).build(), e -> {
            if(BrawlBox.gm.getRedPlayers().size() < 3){

                if(BrawlBox.gm.getRedPlayers().size() < BrawlBox.gm.getBluePlayers().size()){
                    BrawlBox.gm.players.get(player.getUniqueId()).setTeam(Teams.RED);
                    player.sendMessage(ValueHolder.prefix + ValueHolder.tacc("&aYou joined &cTeam Red&a."));
                }else{
                    player.sendMessage(ValueHolder.prefix + ValueHolder.tacc("&cPlease join &9Blue Team &cfor fairness sake."));
                }


            }else{
                player.sendMessage(ValueHolder.prefix + ValueHolder.tacc("&cThis Team is full!"));
            }
            player.closeInventory();
        }));

        contents.set(1, 6, ClickableItem.of(new ItemBuilder(Material.BLUE_WOOL).name(ValueHolder.tacc("&9Team Blue")).lore(BrawlBox.gm.getBluePlayers()).build(), e -> {
            if(BrawlBox.gm.getBluePlayers().size() < 3){
                BrawlBox.gm.players.get(player.getUniqueId()).setTeam(Teams.BLUE);
                player.sendMessage(ValueHolder.prefix + ValueHolder.tacc("&aYou joined &9Team Blue&a."));
            }else{
                player.sendMessage(ValueHolder.prefix + ValueHolder.tacc("&cThis Team is full!"));
            }
            player.closeInventory();
        }));
    }
}
