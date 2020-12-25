package util;


import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftItem;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.*;
import java.util.function.Consumer;

/**
 * Simple {@link ItemStack} builder
 *
 * @author MrMicky
 */
public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material) {
        this(new ItemStack(material));
    }

    public ItemBuilder(ItemStack item) {
        this.item = Objects.requireNonNull(item, "item");
        this.meta = item.getItemMeta();

        if (meta == null) {
            throw new IllegalArgumentException("The type " + item.getType() + " don't support item meta");
        }
    }

    public ItemBuilder type(Material material) {
        item.setType(material);
        return this;
    }

    public ItemBuilder data(int data) {
        return durability((short) data);
    }

    public ItemBuilder durability(int durability) {
        ((Damageable) item.getItemMeta()).setDamage(durability);
        return this;
    }

    public ItemBuilder amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment) {
        return enchant(enchantment, 1);
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder removeEnchant(Enchantment enchantment) {
        meta.removeEnchant(enchantment);
        return this;
    }

    public ItemBuilder setCooldown(Double cooldown) {
//        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
//        NBTTagCompound compound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier("speed", cooldown, AttributeModifier.Operation.ADD_SCALAR));

        return this;
    }

    public ItemBuilder removeEnchants() {
        meta.getEnchants().keySet().forEach(meta::removeEnchant);
        return this;
    }

    public ItemBuilder meta(Consumer<ItemMeta> metaConsumer) {
        metaConsumer.accept(meta);
        return this;
    }

    public <T extends ItemMeta> ItemBuilder meta(Class<T> metaClass, Consumer<T> metaConsumer) {
        if (metaClass.isInstance(meta)) {
            metaConsumer.accept(metaClass.cast(meta));
        }
        return this;
    }

    public ItemBuilder name(String name) {
        meta.setDisplayName(ValueHolder.tacc(name));
        return this;
    }

    public ItemBuilder lore(String lore) {
        return lore(Collections.singletonList(lore));
    }

    public ItemBuilder lore(String... lore) {
        return lore(Arrays.asList(lore));
    }

    public ItemBuilder lore(List<String> lore) {
        List<String> nLore = new ArrayList<>();

        for (String s : lore) {
            nLore.add(ValueHolder.tacc(s));
        }

        meta.setLore(nLore);
        return this;
    }

    public ItemBuilder addLore(String line) {
        List<String> lore = meta.getLore();

        if (lore == null) {
            return lore(line);
        }

        lore.add(line);
        return lore(lore);
    }

    public ItemBuilder addLore(String... lines) {
        return addLore(Arrays.asList(lines));
    }

    public ItemBuilder addLore(List<String> lines) {
        List<String> lore = meta.getLore();
        List<String> nLore = new ArrayList<>();

        for (String s : lines) {
            nLore.add(ValueHolder.tacc(s));
        }

        if (lore == null) {
            return lore(nLore);
        }

        lore.addAll(nLore);
        return lore(lore);
    }

    public ItemBuilder flags(ItemFlag... flags) {
        meta.addItemFlags(flags);
        return this;
    }

    public ItemBuilder flags() {
        return flags(ItemFlag.values());
    }

    public ItemBuilder removeFlags(ItemFlag... flags) {
        meta.removeItemFlags(flags);
        return this;
    }

    public ItemBuilder removeFlags() {
        return removeFlags(ItemFlag.values());
    }

    public ItemBuilder armorColor(Color color) {
        return meta(LeatherArmorMeta.class, armorMeta -> armorMeta.setColor(color));
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }


}
