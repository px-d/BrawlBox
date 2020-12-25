package util;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Arrays;

public class ValueHolder {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', "&7[&bBrawl&fBox&7]&f ");
    public static String redPrefix = "§c[RED]§r ";
    public static String bluePrefix = "§9[BLUE]§r ";

    public static World world = Bukkit.createWorld(new WorldCreator("game"));
    public static World spawn = Bukkit.createWorld(new WorldCreator("lobby"));

    public static Location lobby = new Location(spawn, -629.5, 10.0, 634.5, 90, 0);

    public static Location center = new Location(world, 0.5, 20.0, 0.5);


    public static Location red_prep = new Location(world, 37.5, 23.0, 0.5, 90, 4);
    public static Location red_spawn = new Location(world, 26.5, 20.0, 0.5, 90, 4);
    public static Location red_shop = new Location(world, 33.5, 23.0, 0.5, -90, 0);

    public static Location blue_prep = new Location(world, -36.5, 23.0, 0.5, -90, 4);
    public static Location blue_spawn = new Location(world, -25.5, 20.0, 0.5, -90, 4);
    public static Location blue_shop = new Location(world, -32.5, 23.0, 0.5, 90, 0);
    
    public static String tacc(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static ItemStack chestPlus = new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).name("&fChestplate &c+").enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addLore(Arrays.asList("", "&7A little bit more advanced", "&7than your average", "&7leather chestplate", "", "&f&lCOMMON CHESTPLATE")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack ironChest = new ItemBuilder(Material.IRON_CHESTPLATE).name("&aIron Chestplate").addLore(Arrays.asList("", "&7Advanced forging technology", "&7allowed the creation of this", "&7stronger chestplate.", "", "&a&lUNCOMMON CHESTPLATE")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack chestPlusPlus = new ItemBuilder(Material.GOLDEN_CHESTPLATE).name("&aChestplate &c++").addLore(Arrays.asList("", "&7Advanced forging technology", "&7combined with complicated", "&7black magic", "&7allowed the creation of this", "&7stronger chestplate.", "", "&a&lUNCOMMON CHESTPLATE")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack diamondChest = new ItemBuilder(Material.DIAMOND_CHESTPLATE).name("&5Diamond Chestplate").addLore(Arrays.asList("", "&7Price: &6800$", "", "&7Tanky, Strong, Mighy", "&7what else could you need?", "", "&5&lEPIC CHESTPLATE")).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 4).flags(ItemFlag.HIDE_ATTRIBUTES).build();

    public static ItemStack shield = new ItemBuilder(Material.SHIELD).name("&aShield").enchant(Enchantment.DURABILITY, 1).addLore(Arrays.asList("", "&7Curve your enemies in style", "", "&a&lUNCOMMON TOOL")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack kbStick = new ItemBuilder(Material.STICK).name("&aKnockback Stick").enchant(Enchantment.KNOCKBACK, 1).addLore(Arrays.asList("", "&7Try to be as annoying as possible.", "", "&a&lCOMMON WEAPON")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack frag = new ItemBuilder(Material.SLIME_BALL).name("&dFrag").setCooldown(0.1D).addLore(Arrays.asList("", "&7Throw this axe and let it explode", "&7to deal massive damage.", "", "&d&k&lK &r&d&lMYTHIC WEAPON&d&k&lK")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack reaperScythe = new ItemBuilder(Material.DIAMOND_HOE).name("&6Reaper's Scythe").addLore(Arrays.asList("", "&7You'll become your enemies", "&7worst nightmare...", "", "&6&lLEGENDARY WEAPON")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack safetyPlatform = new ItemBuilder(Material.BLAZE_ROD).name("&5Safety Platform").addLore(Arrays.asList("", "&7Haha N00B.. You really", "&7need a sAfeTy PlAtFoRM?!?!", "", "&5&lEPIC TOOL")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack creeperBow = new ItemBuilder(Material.BOW).name("&aExplosive Bow").addLore(Arrays.asList("&7Hotkeyyy I", "", "&7&oAww.. Man...", "", "&7Shoots explosive projectiles", "&7that damage your enemies", "&7and launches them in the air.", "", "&a&lUNCOMMON BOW")).build();
    public static ItemStack teamInv = new ItemBuilder(Material.NETHER_STAR).name("&cTeam Inventory&7 (Rightclick)").addLore(Arrays.asList("", "&7Right-Click to open your", "&7Team's inventory.", "", "&8You can only put purchased", "&8Items in your Team-Inventory!")).build();

    public static ItemStack wooden_sword = new ItemBuilder(Material.WOODEN_SWORD).setCooldown(1.0D).name("&fWooden Sword").addLore(Arrays.asList("", "&7Your average Wooden Sword", "", "&f&lCOMMON SWORD")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack stone_sword = new ItemBuilder(Material.STONE_SWORD).setCooldown(1.0D).name("&aStone Sword").addLore(Arrays.asList("", "&7Upgraded version of the Wooden Sword", "", "&a&lUNCOMMON SWORD")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack golden_sword = new ItemBuilder(Material.GOLDEN_SWORD).setCooldown(2.0D).name("&9Golden Sword").addLore(Arrays.asList("", "&7Crafted by the assasins", "&7of the shadowlands.", "", "&9&lRARE SWORD")).enchant(Enchantment.DURABILITY, 1).enchant(Enchantment.DAMAGE_ALL, 1).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack iron_sword = new ItemBuilder(Material.IRON_SWORD).setCooldown(2.0D).name("&5Iron Sword").addLore(Arrays.asList("", "&7&oWe make war that we may live in peace.", "  &8&o- Aristotle", "", "&5&lEPIC SWORD")).flags(ItemFlag.HIDE_ATTRIBUTES).build();

    public static ItemStack used_platform = new ItemBuilder(Material.STICK).name("&7Used Platform").addLore(Arrays.asList("", "&7Once your safety, now a useless", "&7piece of wood.", "", "&7&lUSELESS TOOL")).build();

}
