package util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Arrays;

public class LangDE {
    public static ItemStack chestPlus = new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).name("&fBrustplatte &c+").enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addLore(Arrays.asList("", "&7Ein wenig besser, als die", "&7durchschnittliche ", "&7Leder-Brustplatte.", "", "&f&lGEW\u00D6HNLICHE BRUSTPLATTE", "", "&7Price: &6100$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack ironChest = new ItemBuilder(Material.IRON_CHESTPLATE).name("&aEisen Brustplatte").addLore(Arrays.asList("", "&7Fortschrittliche Schmiedekunst", "&7half bei der Herstellung dieser", "&7starken Brustplatte.", "", "&a&lUNGEW\u00D6HNLICHE BRUSTPLATTE", "", "&7Price: &6300$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack chestPlusPlus = new ItemBuilder(Material.GOLDEN_CHESTPLATE).name("&aBrustplatte &c++").addLore(Arrays.asList("", "&7Fortschrittliche Technologie", "&7kombiniert mit komplexer", "&7dunkler Magie halfen bei", "&7der Erstellung dieser", "&7st\u00e4rkeren Brustplatte.", "", "&a&lUNGEW\u00D6HNLICHE BRUSTPLATTE", "", "&7Price: &6400$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack diamondChest = new ItemBuilder(Material.DIAMOND_CHESTPLATE).name("&5Diamant Brustplatte").addLore(Arrays.asList("", "&7Stark, Resistent, M\u00e4chtig", "&7was braucht man mehr?", "", "&5&lEPISCHE BRUSTPLATTE", "", "&7Price: &6800$")).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 4).flags(ItemFlag.HIDE_ATTRIBUTES).build();

    public static ItemStack sandstone = new ItemBuilder(Material.SMOOTH_SANDSTONE).name("&fSandstein").addLore("", "&7Preis: &6200$", "", "&f&lGEW\u00D6HNLICHER BLOCK").amount(8).build();
    public static ItemStack endstone = new ItemBuilder(Material.END_STONE).name("&aEndstein").addLore("", "&7Preis: &6150$", "", "&a&lUNGEW\u00D6HNLICHER BLOCK").amount(6).build();
    public static ItemStack wool = new ItemBuilder(Material.WHITE_WOOL).name("&9Wolle").addLore("", "&7Preis: &6200$", "", "&9&lSELTENER BLOCK").amount(4).build();

    public static ItemStack shield = new ItemBuilder(Material.SHIELD).name("&aSchild").enchant(Enchantment.DURABILITY, 1).addLore(Arrays.asList("", "&7Weiche deinen Feinden mit Stil aus", "", "&a&lUNGEW\u00D6HNLICHES TOOL")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack kbStick = new ItemBuilder(Material.STICK).name("&aKnockback Stick").enchant(Enchantment.KNOCKBACK, 1).addLore(Arrays.asList("", "&7Versuche so nervig wie m\u00f6glich zu sein", "&7m\u00f6glich zu sein.", "", "&a&lUNGEW\u00D6HNLICHE WAFFE","","&7Preis: &6100$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack frag = new ItemBuilder(Material.SLIME_BALL).name("&dFrag").setCooldown(0.1D).addLore(Arrays.asList("", "&7Wirf die Granate, die nach", "&c1.2s &7Explodiert.", "", "&d&k&lK &r&d&lMYTHISCHE WAFFE &k&lK","","&7Preis: &6300$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack reaperScythe = new ItemBuilder(Material.DIAMOND_HOE).name("&6Reaper's Sense").addLore(Arrays.asList("", "&7Werde zu dem gr\u00f6ten Alptraum", "", "&6&lLEGEND\u00C4RE WAFFE","","&7Preis: &6400$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack safetyPlatform = new ItemBuilder(Material.BLAZE_ROD).name("&5Sicherheits Platform").addLore(Arrays.asList("", "&7Haha N00B.. tf???", "&7das hast du n\u00f6tig ?!?!", "", "&5&lEPISCHES TOOL","","&7Preis: &6200$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack creeperBow = new ItemBuilder(Material.BOW).name("&aExplosions Bogen").addLore(Arrays.asList("&7Hotkeyyy I", "", "&7&oAww.. Man...", "", "&7Schie\u00dft explosive Projektile", "&7die deinem Gegner Schaden machen", "&7und ihn in die Luft schleudern..", "", "&a&lUNGEW\u00D6HNLICHER BOGEN","","&7Preis: &6500$")).build();
    public static ItemStack teamInv = new ItemBuilder(Material.NETHER_STAR).name("&cTeam Inventar&7 (Rechtsklick)").addLore(Arrays.asList("", "&7Rechts-Click um dein Team-", "&7Inventar zu \u00f6ffnen.", "", "&8Du kannst nur gekaufte Items", "&8in's Team-Inventar verschieben.")).build();

    public static ItemStack wooden_sword = new ItemBuilder(Material.WOODEN_SWORD).setCooldown(1.0D).name("&fHolz Schwert").addLore(Arrays.asList("", "&7Durchschnittliches Holzschwert", "", "&f&lGEW\u00D6HNLICHES SCHWERT", "", "&7Preis: &6FREE")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack stone_sword = new ItemBuilder(Material.STONE_SWORD).setCooldown(1.0D).name("&aStein Schwert").addLore(Arrays.asList("", "&7Neue Version des Holz-Schwertes", "&7(Holzschwert v.2 sozusagen)", "", "&a&lUNGEW\u00D6HNLICHES SCHWERT", "", "&7Preis: &6100$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack golden_sword = new ItemBuilder(Material.GOLDEN_SWORD).setCooldown(2.0D).name("&9Gold Schwert").addLore(Arrays.asList("", "&7Hergestellt von den Assasinen", "&7der Schattenlande.", "", "&9&lSELTENES SCHWERT", "", "&7Preis: &6300$")).enchant(Enchantment.DURABILITY, 1).enchant(Enchantment.DAMAGE_ALL, 1).flags(ItemFlag.HIDE_ATTRIBUTES).build();
    public static ItemStack iron_sword = new ItemBuilder(Material.IRON_SWORD).setCooldown(2.0D).name("&5Eisen Schwert").addLore(Arrays.asList("", "&7&oWir f\u00fchren Krieg, nur damit", "&7&owir in Frieden leben k\u00f6nnen.", "  &8&o- Aristoteles", "", "&5&lEPISCHES SCHWERT", "", "&7Preis: &6500$")).flags(ItemFlag.HIDE_ATTRIBUTES).build();

    public static ItemStack used_platform = new ItemBuilder(Material.STICK).name("&7Nutzlose Platform").addLore(Arrays.asList("", "&7Einst der Boden unter dir...", "&7...jetzt ein nutzloses St\u00fcck Holz.", "", "&7&lNUTZLOSES TOOL")).build();


    public static ItemStack getWelcomeBook() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bm = (BookMeta) book.getItemMeta();

        bm.setAuthor("DrSoho");
        bm.addPage(ValueHolder.tacc("\n\n\n\n\n\n&c&l   Willkommen zu\n      BrawlBox.\n\n\n\n\n\n"));
        bm.addPage(ValueHolder.tacc("&a&lAnleitung:\n\n&rJedes Team startet in seinem&a Prep-Raum&r wo ihr &a15 Sekunde&r habt um euch vorzubereiten.\n\nIhr k\u00f6nnt in dieser Phase &cItems &r&&c Upgrades&r kaufen."));
        bm.addPage(ValueHolder.tacc("&a&lDer Shop:&r\n\n\u00d6ffne den Shop indem du den Dorfbewohner &crechtsklickst&r.\nSolltest du dir ein &ofalsches &rSchwert kaufen kannst du dein Geld zur\u00fcck bekommen, indem du ein g\u00fcnstigeres kaufst. &cDas funktioniert &lNUR&r&c f\u00fcr Schwerter."));
        bm.addPage(ValueHolder.tacc("Des Weiteren kannst du nur &ceins &rvon jedem &aSpezial-Item&r kaufen.\n\nAlle &aSpezial-Items &rsind recht selbsterkl\u00e4rend."));


        bm.addPage(ValueHolder.tacc(
                "&a&lZiel des Spiels:\n" +
                        "\n" +
                        "&rDein Ziel als Team ist es entweder das Gegnerteam auszul\u00f6schen, oder die 3x3 Zone in der Mitte mit deinem &cTeam-Block &rzu f\u00fcllen."
        ));

        bm.addPage(ValueHolder.tacc("&a&lAu\u00dferdem:\n\n&9&lBelohnungen:&r" +
                "\nKill&r: &6200$" +
                "\n&rRundensieg&r: &6400$" +
                "\n\n" +
                "&7ACHTUNG: Du bekommst nur eine Belohnung, wenn du einen Gegner im &c1 gegen 1&7 besiegst. Solltest du ihn in das Wasser schubsen bekommst du &lkeine."));
//        bm.setPage(1, "Welcome to BrawlBox");

        bm.setTitle(ValueHolder.tacc("&aWie geht's?"));
        book.setItemMeta(bm);
        return book;
    }

}
