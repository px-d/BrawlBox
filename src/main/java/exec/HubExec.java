package exec;

import enums.State;
import main.BrawlBox;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import util.MessageAPI;
import util.ValueHolder;

public class HubExec implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player) {
            if (BrawlBox.gm.state == State.LOBBY) {
                ((Player) commandSender).teleport(ValueHolder.lobby);
                commandSender.sendMessage(ValueHolder.tacc("&cTeleported you to the &cHub"));
            }
        }


        return true;
    }
}
