package util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Array;
import java.util.Arrays;

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

    public static ItemStack chestPlus = new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).name("&fChestplate &c+").enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addLore(Arrays.asList("", "&7A little bit more advanced", "&7than your average", "&7leather chestplate", "", "&f&lCOMMON CHESTPLATE")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack ironChest = new ItemBuilder(Material.IRON_CHESTPLATE).name("&aIron Chestplate").addLore(Arrays.asList("", "&7Advanced forging technology", "&7allowed the creation of this", "&7stronger chestplate.", "", "&a&lUNCOMMON CHESTPLATE")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack chestPlusPlus = new ItemBuilder(Material.GOLDEN_CHESTPLATE).name("&aChestplate &c++").addLore(Arrays.asList("", "&7Advanced forging technology", "&7combined with complicated", "&7black magic", "&7allowed the creation of this", "&7stronger chestplate.", "", "&a&lUNCOMMON CHESTPLATE")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack diamondChest = new ItemBuilder(Material.DIAMOND_CHESTPLATE).name("&5Diamond Chestplate").addLore(Arrays.asList("", "&7Price: &6800$", "","&7Tanky, Strong, Mighy","&7what else could you need?","", "&5&lEPIC CHESTPLATE")).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 4).flags(ItemFlag.HIDE_ATTRIBUTES).build();

    public static ItemStack shield = new ItemBuilder(Material.SHIELD).name("&aShield").enchant(Enchantment.DURABILITY, 1).addLore(Arrays.asList("","&7Curve your enemies in style","","&a&lUNCOMMON TOOL")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack kbStick = new ItemBuilder(Material.STICK).name("&aKnockback Stick").enchant(Enchantment.KNOCKBACK, 1).addLore(Arrays.asList("","&7Try to be as annoying as possible.","","&a&lCOMMON WEAPON")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack jarnbjorn = new ItemBuilder(Material.IRON_AXE).name("&dJarnbjorn").addLore(Arrays.asList("","&7Throw this axe to deal","&7massive damage.","","&d&k&lK &r&d&lMYTHIC WEAPON&d&k&lK")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack reaperScythe = new ItemBuilder(Material.DIAMOND_HOE).name("&6Reaper's Scythe").addLore(Arrays.asList("","&7You'll become your enemies","&7worst nightmare...","","&6&lLEGENDARY WEAPON")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack safetyPlatform = new ItemBuilder(Material.BLAZE_ROD).name("&5Safety Platform").addLore(Arrays.asList("","&7Haha N00B.. You really","&7need a sAfeTy PlAtFoRM?!?!","","&5&lEPIC TOOL")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack creeperBow = new ItemBuilder(Material.BOW).name("&aHotkeyyy Bow").addLore(Arrays.asList("&7Explosive I","","&7&oAww.. Man...","","&7Shoots explosive projectiles","&7that damage your enemies","&7and launches them in the air.","","&a&lUNCOMMON BOW")).build();

}
