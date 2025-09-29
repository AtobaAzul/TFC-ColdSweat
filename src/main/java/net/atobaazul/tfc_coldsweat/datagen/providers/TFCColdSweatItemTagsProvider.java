package net.atobaazul.tfc_coldsweat.datagen.providers;

import net.atobaazul.tfc_coldsweat.TFCColdSweatTags;
import net.atobaazul.tfc_coldsweat.common.TFCColdSweatItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import static net.atobaazul.tfc_coldsweat.TFCColdSweat.MOD_ID;

public class TFCColdSweatItemTagsProvider extends ItemTagsProvider {
    public TFCColdSweatItemTagsProvider(@NotNull PackOutput output, @NotNull CompletableFuture<HolderLookup.Provider> lookupProvider, @NotNull CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MOD_ID, existingFileHelper);
    }


    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.DYEABLE)
                .replace(false)
                .add(TFCColdSweatItems.WOOL_HELMET.get())
                .add(TFCColdSweatItems.WOOL_CHESTPLATE.get())
                .add(TFCColdSweatItems.WOOL_LEGGINGS.get())
                .add(TFCColdSweatItems.SILK_HELMET.get())
                .add(TFCColdSweatItems.SILK_CHESTPLATE.get())
                .add(TFCColdSweatItems.SILK_LEGGINGS.get())
                .add(TFCColdSweatItems.BURLAP_HELMET.get())
                .add(TFCColdSweatItems.BURLAP_CHESTPLATE.get())
                .add(TFCColdSweatItems.BURLAP_LEGGINGS.get());

        tag(TFCColdSweatTags.WOOL_CLOTHES)
                .add(TFCColdSweatItems.WOOL_HELMET.get())
                .add(TFCColdSweatItems.WOOL_CHESTPLATE.get())
                .add(TFCColdSweatItems.WOOL_LEGGINGS.get());

        tag(TFCColdSweatTags.SILK_CLOTHES)
                .add(TFCColdSweatItems.SILK_HELMET.get())
                .add(TFCColdSweatItems.SILK_CHESTPLATE.get())
                .add(TFCColdSweatItems.SILK_LEGGINGS.get());

        tag(TFCColdSweatTags.BURLAP_CLOTHES)
                .add(TFCColdSweatItems.BURLAP_HELMET.get())
                .add(TFCColdSweatItems.BURLAP_CHESTPLATE.get())
                .add(TFCColdSweatItems.BURLAP_LEGGINGS.get());

    }
}
