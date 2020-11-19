package util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TitleAPI {

    public static void sendTitleToAll(String title, String subtitle) {
        title = ValueHolder.tacc(title);
        subtitle = ValueHolder.tacc(subtitle);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(title, subtitle, 0, 21, 0);
        }
    }

}
