package util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Arrays;

public class ValueHolder {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', "&7[&bBrawl&fBox&7]&f ");
    public static String redPrefix = "§c[RED]§r ";
    public static String bluePrefix = "§9[BLUE]§r ";

    public static Location lobby = new Location(Bukkit.getWorld("lobby"), 0.5, 10.0, 0.5, 90, 0);

    public static Location center = new Location(Bukkit.getWorld("game"), 0.5, 20.0, 0.5);


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
    public static ItemStack diamondChest = new ItemBuilder(Material.DIAMOND_CHESTPLATE).name("&5Diamond Chestplate").addLore(Arrays.asList("", "&7Price: &6800$", "", "&7Tanky, Strong, Mighy", "&7what else could you need?", "", "&5&lEPIC CHESTPLATE")).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 4).flags(ItemFlag.HIDE_ATTRIBUTES).build();

    public static ItemStack shield = new ItemBuilder(Material.SHIELD).name("&aShield").enchant(Enchantment.DURABILITY, 1).addLore(Arrays.asList("", "&7Curve your enemies in style", "", "&a&lUNCOMMON TOOL")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack kbStick = new ItemBuilder(Material.STICK).name("&aKnockback Stick").enchant(Enchantment.KNOCKBACK, 1).addLore(Arrays.asList("", "&7Try to be as annoying as possible.", "", "&a&lCOMMON WEAPON")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack jarnbjorn = new ItemBuilder(Material.IRON_AXE).name("&dJarnbjorn").addLore(Arrays.asList("", "&7Throw this axe to deal", "&7massive damage.", "", "&d&k&lK &r&d&lMYTHIC WEAPON&d&k&lK")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack reaperScythe = new ItemBuilder(Material.DIAMOND_HOE).name("&6Reaper's Scythe").addLore(Arrays.asList("", "&7You'll become your enemies", "&7worst nightmare...", "", "&6&lLEGENDARY WEAPON")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack safetyPlatform = new ItemBuilder(Material.BLAZE_ROD).name("&5Safety Platform").addLore(Arrays.asList("", "&7Haha N00B.. You really", "&7need a sAfeTy PlAtFoRM?!?!", "", "&5&lEPIC TOOL")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack creeperBow = new ItemBuilder(Material.BOW).name("&aHotkeyyy Bow").addLore(Arrays.asList("&7Explosive I", "", "&7&oAww.. Man...", "", "&7Shoots explosive projectiles", "&7that damage your enemies", "&7and launches them in the air.", "", "&a&lUNCOMMON BOW")).build();
    public static ItemStack teamInv = new ItemBuilder(Material.NETHER_STAR).name("&cTeam Inventory&7 (Rightclick)").addLore(Arrays.asList("", "&7Right-Click to open your", "&7Team's inventory.", "", "&8You can only put purchased", "&8Items in your Team-Inventory!")).build();

    public static ItemStack wooden_sword = new ItemBuilder(Material.WOODEN_SWORD).name("&fWooden Sword").addLore(Arrays.asList("", "&7Your average Wooden Sword", "", "&f&lCOMMON SWORD")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack stone_sword = new ItemBuilder(Material.STONE_SWORD).name("&aStone Sword").addLore(Arrays.asList("", "&7Upgraded version of the Wooden Sword", "", "&a&lUNCOMMON SWORD")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack golden_sword = new ItemBuilder(Material.GOLDEN_SWORD).name("&9Golden Sword").addLore(Arrays.asList("", "&7Crafted by the assasins", "&7of the shadowlands.", "", "&9&lRARE SWORD")).enchant(Enchantment.DURABILITY, 1).enchant(Enchantment.DAMAGE_ALL, 1).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack iron_sword = new ItemBuilder(Material.IRON_SWORD).name("&5Iron Sword").addLore(Arrays.asList("", "&7&oWe make war that we may live in peace.", "  &8&o- Aristotle", "", "&5&lEPIC SWORD")).flags(ItemFlag.HIDE_ATTRIBUTES).build();

    public static ItemStack used_platform = new ItemBuilder(Material.STICK).name("&7Used Platform").addLore(Arrays.asList("", "&7Once your safety, now a useless", "&7piece of wood.", "", "&7&lUSELESS TOOL")).build();

    public static ItemStack getWelcomeBook() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bm = (BookMeta) book.getItemMeta();

        bm.setAuthor("soho");
        bm.addPage(ValueHolder.tacc("\n\n\n\n\n\n&c&l     Welcome to\n      BrawlBox.\n\n\n\n\n\n"));
        bm.addPage(ValueHolder.tacc("&a&lCourse of the Game:\n\n&rEvery Team will start in their own&a Prep-Room&r where they have &a20 Seconds&r to get ready for the upcoming battle.\n\nYou can buy various &cItems &r&&c Upgrades&r during this period"));
        bm.addPage(ValueHolder.tacc("&a&lThe Shop:&r\n\nYou can access the shop by right-clicking the &cEnd-Portal-Frame&r in your &aPrep-Room&r\nIf you accidentally buy the wrong sword you can simply &crefund &rit by '&9buying&r' a cheaper one or '&9upgrading&r' to a more expensive one &7(only pay the difference)&r."));
        bm.addPage(ValueHolder.tacc("If you however buy something else such as &cBlocks&r or &cSpecial Items&r by accident &lyou won't be able&r to refund them. Furthermore you will only be able to carry &cone of each &rspecial item.\n\nAll Special-Items are pretty self explanatory."));


        bm.addPage(ValueHolder.tacc(
                "&a&lObjective:\n" +
                        "\n" +
                        "&rYour objective as a team is to either eliminate your opponents or fill the &c3x3&r area in the middle with your &cTeam-Block \n&8&o(Concrete)"
        ));

        bm.addPage(ValueHolder.tacc("&a&lFurther:\n\n&9&lRewards:&r" +
                "\nKill&r: &6200$" +
                "\n&rRound Win&r: &6400$" +
                "\n\n\n" +
                "&7Notice: Only Hand-To-Hand Combat Kills are eligible for a rewards. Pushing your enemy down into the water doesn't qualify."));
//        bm.setPage(1, "Welcome to BrawlBox");

        bm.setTitle(ValueHolder.tacc("&aHow To Play"));
        book.setItemMeta(bm);
        return book;
    }


}
