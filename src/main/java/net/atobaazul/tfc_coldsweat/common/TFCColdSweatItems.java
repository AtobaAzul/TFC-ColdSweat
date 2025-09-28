package net.atobaazul.tfc_coldsweat.common;


import net.atobaazul.tfc_coldsweat.TFCColdSweat;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TFCColdSweatItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TFCColdSweat.MOD_ID);

    static final Item.Properties WOOL_ARMOR_PROPERTIES = new Item.Properties().stacksTo(1);

    //Armor items
    public static final DeferredItem<Item> WOOL_HELMET = ITEMS.register("wool_helmet", () ->
            new WoolClothingArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.HELMET, WOOL_ARMOR_PROPERTIES.durability(ArmorItem.Type.HELMET.getDurability(14))));
    public static final DeferredItem<Item> WOOL_CHESTPLATE = ITEMS.register("wool_chestplate", () ->
            new WoolClothingArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.CHESTPLATE, WOOL_ARMOR_PROPERTIES.durability(ArmorItem.Type.CHESTPLATE.getDurability(14))));
    public static final DeferredItem<Item> WOOL_LEGGINGS = ITEMS.register("wool_leggings", () ->
            new WoolClothingArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.LEGGINGS, WOOL_ARMOR_PROPERTIES.durability(ArmorItem.Type.LEGGINGS.getDurability(14))));



}
