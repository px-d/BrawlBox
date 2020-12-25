package exec;

import crucial.Brawler;
import enums.Lang;
import enums.State;
import main.BrawlBox;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import util.LangDE;
import util.LangEN;
import util.ValueHolder;

public class LangExec implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }


        Brawler brawler = BrawlBox.gm.players.get(((Player) sender).getUniqueId());
        if (BrawlBox.gm.state == State.LOBBY) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("en")) {
                    brawler.lang = Lang.EN;
                    sender.sendMessage(ValueHolder.tacc("&cYou changed the Language to &aEnglish&c."));
                    brawler.getPlayer().getInventory().setItem(6, LangEN.getWelcomeBook());
                }
                if (args[0].equalsIgnoreCase("de")) {
                    brawler.lang = Lang.DE;
                    sender.sendMessage(ValueHolder.tacc("&cDu hast die Sprache zu &aDeutsch&c ge\u00e4ndert."));
                    brawler.getPlayer().getInventory().setItem(6, LangDE.getWelcomeBook());
                }
            } else {
                switch (brawler.lang) {
                    case EN:
                        sender.sendMessage(ValueHolder.tacc("&cYour current Language is: &aEnglish"));
                        return true;
                    case DE:
                        sender.sendMessage(ValueHolder.tacc("&cDeine aktuelle Sprache ist: &aDeutsch"));
                }
            }
        }else{
            if(brawler.lang == Lang.EN){
                brawler.getPlayer().sendMessage(ValueHolder.tacc("&cThis Command is currently disabled."));
            }else{
                brawler.getPlayer().sendMessage(ValueHolder.tacc("&cDieser Befehl ist momentan deaktiviert."));
            }
        }


        return true;
    }
}
