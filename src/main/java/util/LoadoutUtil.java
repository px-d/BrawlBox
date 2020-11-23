package util;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LoadoutUtil {


    public static void redLoadout(Player p) {
        ItemStack sword = new ItemBuilder(Material.WOODEN_SWORD).flags(ItemFlag.HIDE_ATTRIBUTES).build();
        ItemStack pick = new ItemBuilder(Material.IRON_PICKAXE).flags(ItemFlag.HIDE_ATTRIBUTES).build();
        pick.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        pick.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        pick.getItemMeta().setUnbreakable(true);
        ItemStack blocks = new ItemBuilder(Material.RED_CONCRETE).amount(9).build();

        ItemStack helmet = new ItemBuilder(Material.LEATHER_HELMET).build();
        LeatherArmorMeta lamHelmet = (LeatherArmorMeta) helmet.getItemMeta();
        assert lamHelmet != null;
        lamHelmet.setColor(Color.RED);
        helmet.setItemMeta(lamHelmet);


        ItemStack chest = new ItemBuilder(Material.LEATHER_CHESTPLATE).build();
        LeatherArmorMeta lamChest = (LeatherArmorMeta) helmet.getItemMeta();
        lamChest.setColor(Color.RED);
        chest.setItemMeta(lamChest);

        ItemStack legs = new ItemBuilder(Material.LEATHER_LEGGINGS).build();
        LeatherArmorMeta lamLegs = (LeatherArmorMeta) helmet.getItemMeta();
        lamLegs.setColor(Color.RED);
        legs.setItemMeta(lamLegs);

        ItemStack boots = new ItemBuilder(Material.LEATHER_BOOTS).build();
        LeatherArmorMeta lamBoots = (LeatherArmorMeta) helmet.getItemMeta();
        lamBoots.setColor(Color.RED);
        boots.setItemMeta(lamBoots);

        p.getInventory().setArmorContents(new ItemStack[]{boots, legs, chest, helmet});
        p.getInventory().addItem(sword);
        p.getInventory().addItem(pick);
        p.getInventory().setItem(8, blocks);
        p.getInventory().setItem(17, ValueHolder.teamInv);
    }

    public static void blueLoadout(Player p) {
        ItemStack sword = new ItemBuilder(Material.WOODEN_SWORD).flags(ItemFlag.HIDE_ATTRIBUTES).build();
        ItemStack pick = new ItemBuilder(Material.IRON_PICKAXE).flags(ItemFlag.HIDE_ATTRIBUTES).build();
        pick.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        pick.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        pick.getItemMeta().setUnbreakable(true);
        ItemStack blocks = new ItemBuilder(Material.BLUE_CONCRETE).amount(9).build();

        ItemStack helmet = new ItemBuilder(Material.LEATHER_HELMET).build();
        LeatherArmorMeta lamHelmet = (LeatherArmorMeta) helmet.getItemMeta();
        assert lamHelmet != null;
        lamHelmet.setColor(Color.BLUE);
        helmet.setItemMeta(lamHelmet);


        ItemStack chest = new ItemBuilder(Material.LEATHER_CHESTPLATE).build();
        LeatherArmorMeta lamChest = (LeatherArmorMeta) helmet.getItemMeta();
        lamChest.setColor(Color.BLUE);
        chest.setItemMeta(lamChest);

        ItemStack legs = new ItemBuilder(Material.LEATHER_LEGGINGS).build();
        LeatherArmorMeta lamLegs = (LeatherArmorMeta) helmet.getItemMeta();
        lamLegs.setColor(Color.BLUE);
        legs.setItemMeta(lamLegs);

        ItemStack boots = new ItemBuilder(Material.LEATHER_BOOTS).build();
        LeatherArmorMeta lamBoots = (LeatherArmorMeta) helmet.getItemMeta();
        lamBoots.setColor(Color.BLUE);
        boots.setItemMeta(lamBoots);

        p.getInventory().setArmorContents(new ItemStack[]{boots, legs, chest, helmet});
        p.getInventory().addItem(sword);
        p.getInventory().addItem(pick);
        p.getInventory().setItem(8, blocks);
        p.getInventory().setItem(17, ValueHolder.teamInv);
    }


}
