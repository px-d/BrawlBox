package exec;

import enums.State;
import main.BrawlBox;
import main.GameManager;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import util.ValueHolder;

public class SetRoundExec implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] strings) {

        if (sender.isOp() && strings.length == 1) {
            if (NumberUtils.isNumber(strings[0])) {
                if(BrawlBox.gm.state == State.LOBBY){
                    GameManager.end_rounds = Integer.parseInt(strings[0]);
                    sender.sendMessage(ValueHolder.tacc("&cMax Rounds has been set to &a" + strings[0]));
                }else{
                    sender.sendMessage(ValueHolder.tacc("&cYou can only change the number of rounds during lobby-phase."));
                }

            }
        }

        return true;
    }
}
