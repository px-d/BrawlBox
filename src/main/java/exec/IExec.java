package exec;

import main.BrawlBox;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import util.ValueHolder;

public class IExec implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (sender instanceof Player) {
            if (args.length == 1) {

                Material m;

                try {
                    m = Material.valueOf(args[0]);
                    ((Player) sender).getInventory().addItem(new ItemStack(m));
                } catch (Exception e) {
                    sender.sendMessage("INVALID NAME");
                }


            } else if (args.length == 2) {
                if (StringUtils.isNumeric(args[1])) {
                    Material m;
                    try {
                        m = Material.valueOf(args[0]);
                        ((Player) sender).getInventory().addItem(new ItemStack(m, Math.min(Integer.parseInt(args[1]), 64)));
                    } catch (Exception e) {
                        sender.sendMessage("INVALID NAME");
                    }

                }
            }
        }
        return true;
    }
}
