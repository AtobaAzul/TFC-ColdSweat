package net.atobaazul.tfc_coldsweat.common.armor_material;

import net.atobaazul.tfc_coldsweat.TFCColdSweat;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, TFCColdSweat.MOD_ID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> WOOL = ARMOR_MATERIALS.register("wool", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.HELMET, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 2);
        map.put(ArmorItem.Type.LEGGINGS, 2);
        map.put(ArmorItem.Type.BOOTS, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(TFCItems.WOOL_CLOTH), List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(TFCColdSweat.MOD_ID, "wool"), "", true), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(TFCColdSweat.MOD_ID, "wool"), "_overlay", false)), 0.0F, 0.0F));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BURLAP = ARMOR_MATERIALS.register("burlap", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.HELMET, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 2);
        map.put(ArmorItem.Type.LEGGINGS, 2);
        map.put(ArmorItem.Type.BOOTS, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(TFCItems.WOOL_CLOTH), List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(TFCColdSweat.MOD_ID, "burlap"), "", true), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(TFCColdSweat.MOD_ID, "burlap"), "_overlay", false)), 0.0F, 0.0F));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SILK = ARMOR_MATERIALS.register("silk", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.HELMET, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 2);
        map.put(ArmorItem.Type.LEGGINGS, 2);
        map.put(ArmorItem.Type.BOOTS, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(TFCItems.WOOL_CLOTH), List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(TFCColdSweat.MOD_ID, "silk"), "", true), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(TFCColdSweat.MOD_ID, "silk"), "_overlay", false)), 0.0F, 0.0F));

}
