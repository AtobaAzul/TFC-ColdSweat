package net.atobaazul.tfc_coldsweat;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static net.atobaazul.tfc_coldsweat.TFCColdSweat.MOD_ID;

public class TFCColdSweatTags {
    public static final TagKey<Item> WOOL_CLOTHES = createItemTag("wool_clothes");
    public static final TagKey<Item> BURLAP_CLOTHES = createItemTag("burlap_clothes");
    public static final TagKey<Item> SILK_CLOTHES = createItemTag("silk_clothes");

    private static TagKey<Block> createBlockTag(String tagName){
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, tagName));
    }

    private static TagKey<Item> createItemTag(String tagName){
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, tagName));
    }
}
