package util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.inventory.meta.SkullMeta;

public class ValueHolder {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', "&7[&bBrawl&fBox&7]&f ");
    public static String redPrefix = "§c[RED]§r ";
    public static String bluePrefix = "§9[BLUE]§r ";

    public static Location lobby = new Location(Bukkit.getWorld("lobby"), 0.5, 10.0, 0.5, 90, 0);

    public static Location red_prep = new Location(Bukkit.getWorld("game"), 33.5, 23.0, 0.5, 90, 4);
    public static Location red_spawn = new Location(Bukkit.getWorld("game"), 26.5, 20.0, 0.5, 90, 4);

    public static Location blue_prep = new Location(Bukkit.getWorld("game"), -32.5, 23.0, 0.5, -90, 4);
    public static Location blue_spawn = new Location(Bukkit.getWorld("game"), -25.5, 20.0, 0.5, -90, 4);


    public static String tacc(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }



}
