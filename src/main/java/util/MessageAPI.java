package util;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MessageAPI {

    public static void sendTitleToAll(String title, String subtitle) {
        title = ValueHolder.tacc(title);
        subtitle = ValueHolder.tacc(subtitle);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(title, subtitle, 0, 21, 0);
        }
    }

    public static void sendTitleToAll(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        title = ValueHolder.tacc(title);
        subtitle = ValueHolder.tacc(subtitle);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
    }

    public static void msgToAll(String msg) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(ValueHolder.tacc(msg));
        }
    }

    public static void roundEndSound() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 0.5f, 1f);
        }
    }

    public static void bing() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
        }
    }

    public static void bong() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 0.5f, 1f);
        }
    }

}
