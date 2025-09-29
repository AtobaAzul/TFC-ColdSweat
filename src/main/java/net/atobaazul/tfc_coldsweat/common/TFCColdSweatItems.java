package net.atobaazul.tfc_coldsweat.common;


import net.atobaazul.tfc_coldsweat.TFCColdSweat;
import net.atobaazul.tfc_coldsweat.common.armor_material.ModArmorMaterials;
import net.atobaazul.tfc_coldsweat.common.item.WoolClothingArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TFCColdSweatItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TFCColdSweat.MOD_ID);

    static final Item.Properties ARMOR_PROPERTIES = new Item.Properties().stacksTo(1);

    //Armor items
    public static final DeferredItem<Item> WOOL_HELMET = ITEMS.register("wool_helmet", () ->
            new WoolClothingArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.HELMET, ARMOR_PROPERTIES.durability(ArmorItem.Type.HELMET.getDurability(14))));
    public static final DeferredItem<Item> WOOL_CHESTPLATE = ITEMS.register("wool_chestplate", () ->
            new WoolClothingArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.CHESTPLATE, ARMOR_PROPERTIES.durability(ArmorItem.Type.CHESTPLATE.getDurability(14))));
    public static final DeferredItem<Item> WOOL_LEGGINGS = ITEMS.register("wool_leggings", () ->
            new WoolClothingArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.LEGGINGS, ARMOR_PROPERTIES.durability(ArmorItem.Type.LEGGINGS.getDurability(14))));


    public static final DeferredItem<Item> SILK_HELMET = ITEMS.register("silk_helmet", () ->
            new WoolClothingArmorItem(ModArmorMaterials.SILK, ArmorItem.Type.HELMET, ARMOR_PROPERTIES.durability(ArmorItem.Type.HELMET.getDurability(14))));
    public static final DeferredItem<Item> SILK_CHESTPLATE = ITEMS.register("silk_chestplate", () ->
            new WoolClothingArmorItem(ModArmorMaterials.SILK, ArmorItem.Type.CHESTPLATE, ARMOR_PROPERTIES.durability(ArmorItem.Type.CHESTPLATE.getDurability(14))));
    public static final DeferredItem<Item> SILK_LEGGINGS = ITEMS.register("silk_leggings", () ->
            new WoolClothingArmorItem(ModArmorMaterials.SILK, ArmorItem.Type.LEGGINGS, ARMOR_PROPERTIES.durability(ArmorItem.Type.LEGGINGS.getDurability(14))));

    public static final DeferredItem<Item> BURLAP_HELMET = ITEMS.register("burlap_helmet", () ->
            new WoolClothingArmorItem(ModArmorMaterials.BURLAP, ArmorItem.Type.HELMET, ARMOR_PROPERTIES.durability(ArmorItem.Type.HELMET.getDurability(14))));
    public static final DeferredItem<Item> BURLAP_CHESTPLATE = ITEMS.register("burlap_chestplate", () ->
            new WoolClothingArmorItem(ModArmorMaterials.BURLAP, ArmorItem.Type.CHESTPLATE, ARMOR_PROPERTIES.durability(ArmorItem.Type.CHESTPLATE.getDurability(14))));
    public static final DeferredItem<Item> BURLAP_LEGGINGS = ITEMS.register("burlap_leggings", () ->
            new WoolClothingArmorItem(ModArmorMaterials.BURLAP, ArmorItem.Type.LEGGINGS, ARMOR_PROPERTIES.durability(ArmorItem.Type.LEGGINGS.getDurability(14))));

}
