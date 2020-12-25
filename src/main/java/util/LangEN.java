package util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Arrays;

public class LangEN {
    public static ItemStack chestPlus = new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).name("&fChestplate &c+").enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addLore(Arrays.asList("", "&7A little bit more advanced", "&7than your average", "&7leather chestplate", "", "&f&lCOMMON CHESTPLATE", "", "&7Price: &6100$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack ironChest = new ItemBuilder(Material.IRON_CHESTPLATE).name("&aIron Chestplate").addLore(Arrays.asList("", "&7Advanced forging technology", "&7allowed the creation of this", "&7stronger chestplate.", "", "&a&lUNCOMMON CHESTPLATE", "", "&7Price: &6300$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack chestPlusPlus = new ItemBuilder(Material.GOLDEN_CHESTPLATE).name("&aChestplate &c++").addLore(Arrays.asList("", "&7Advanced forging technology", "&7combined with complicated", "&7black magic", "&7allowed the creation of this", "&7stronger chestplate.", "", "&a&lUNCOMMON CHESTPLATE", "", "&7Price: &6400$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack diamondChest = new ItemBuilder(Material.DIAMOND_CHESTPLATE).name("&5Diamond Chestplate").addLore(Arrays.asList("", "&7Price: &6800$", "", "&7Tanky, Strong, Mighy", "&7what else could you need?", "", "&5&lEPIC CHESTPLATE", "", "&7Price: &6800$")).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 4).flags(ItemFlag.HIDE_ATTRIBUTES).build();

    public static ItemStack sandstone = new ItemBuilder(Material.SMOOTH_SANDSTONE).name("&fSandstone").addLore("", "&7Price: &6200$", "", "&f&lCOMMON BLOCK").amount(8).build();
    public static ItemStack endstone = new ItemBuilder(Material.END_STONE).name("&aEndstone").addLore("", "&7Price: &6150$", "", "&a&lUNCOMMON BLOCK").amount(6).build();
    public static ItemStack wool = new ItemBuilder(Material.WHITE_WOOL).name("&9Wool").addLore("", "&7Price: &6200$", "", "&9&lRARE BLOCK").amount(4).build();


    public static ItemStack shield = new ItemBuilder(Material.SHIELD).name("&aShield").enchant(Enchantment.DURABILITY, 1).addLore(Arrays.asList("", "&7Curve your enemies in style", "", "&a&lUNCOMMON TOOL")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack kbStick = new ItemBuilder(Material.STICK).name("&aKnockback Stick").enchant(Enchantment.KNOCKBACK, 1).addLore(Arrays.asList("", "&7Try to be as annoying as possible.", "", "&a&lCOMMON WEAPON", "", "&7Price: &6100$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack frag = new ItemBuilder(Material.SLIME_BALL).name("&dFrag").setCooldown(0.1D).addLore(Arrays.asList("", "&7Throw this grendate", "&7which will explode after &c1.2s&7.", "", "&d&k&lK &r&d&dMYTHIC WEAPON &k&lK", "", "&7Price: &6300$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack reaperScythe = new ItemBuilder(Material.DIAMOND_HOE).name("&6Reaper's Scythe").addLore(Arrays.asList("", "&7You'll become your enemies", "&7worst nightmare...", "", "&6&lLEGENDARY WEAPON", "", "&7Price: &6400$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack safetyPlatform = new ItemBuilder(Material.BLAZE_ROD).name("&5Safety Platform").addLore(Arrays.asList("", "&7Haha N00B.. You really", "&7need a sAfeTy PlAtFoRM?!?!", "", "&5&lEPIC TOOL","","&7Price: &6200$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack creeperBow = new ItemBuilder(Material.BOW).name("&aExplosive Bow").addLore(Arrays.asList("&7Hotkeyyy I", "", "&7&oAww.. Man...", "", "&7Shoots explosive projectiles", "&7that damage your enemies", "&7and launches them in the air.", "", "&a&lUNCOMMON BOW","","&7Price: &6500$")).build();
    public static ItemStack teamInv = new ItemBuilder(Material.NETHER_STAR).name("&cTeam Inventory&7 (Rightclick)").addLore(Arrays.asList("", "&7Right-Click to open your", "&7Team's inventory.", "", "&8You can only put purchased", "&8Items in your Team-Inventory!")).build();

    public static ItemStack wooden_sword = new ItemBuilder(Material.WOODEN_SWORD).setCooldown(1.0D).name("&fWooden Sword").addLore(Arrays.asList("", "&7Your average Wooden Sword", "", "&f&lCOMMON SWORD", "", "&7Price: &6FREE")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack stone_sword = new ItemBuilder(Material.STONE_SWORD).setCooldown(1.0D).name("&aStone Sword").addLore(Arrays.asList("", "&7Upgraded version of the Wooden Sword", "", "&a&lUNCOMMON SWORD", "", "&7Price: &6100$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack golden_sword = new ItemBuilder(Material.GOLDEN_SWORD).setCooldown(2.0D).name("&9Golden Sword").addLore(Arrays.asList("", "&7Crafted by the assasins", "&7of the shadowlands.", "", "&9&lRARE SWORD", "", "&7Price: &6300$")).enchant(Enchantment.DURABILITY, 1).enchant(Enchantment.DAMAGE_ALL, 1).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack iron_sword = new ItemBuilder(Material.IRON_SWORD).setCooldown(2.0D).name("&5Iron Sword").addLore(Arrays.asList("", "&7&oWe make war that we may live in peace.", "  &8&o- Aristotle", "", "&5&lEPIC SWORD", "", "&7Price: &6500$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();

    public static ItemStack used_platform = new ItemBuilder(Material.STICK).name("&7Used Platform").addLore(Arrays.asList("", "&7Once your safety, now a useless", "&7piece of wood.", "", "&7&lUSELESS TOOL")).build();


    public static ItemStack getWelcomeBook() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bm = (BookMeta) book.getItemMeta();

        bm.setAuthor("DrSoho");
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
