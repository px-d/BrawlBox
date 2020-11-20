package exec;

import main.BrawlBox;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import util.ValueHolder;

public class FreezeExec implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (sender.isOp()) {
            BrawlBox.gm.pause = !BrawlBox.gm.pause;

            String msg = BrawlBox.gm.pause ? "&cGame has been &lfrozen." : "&cGame has been &lunfrozen.";

            sender.sendMessage(ValueHolder.tacc(msg));
        }
        return true;
    }
}
