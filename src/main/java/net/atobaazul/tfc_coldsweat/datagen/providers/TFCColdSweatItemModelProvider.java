package net.atobaazul.tfc_coldsweat.datagen.providers;

import net.atobaazul.tfc_coldsweat.common.TFCColdSweatItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.atobaazul.tfc_coldsweat.TFCColdSweat.MOD_ID;

public class TFCColdSweatItemModelProvider extends ItemModelProvider {
    public TFCColdSweatItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        dyeableItem(TFCColdSweatItems.WOOL_HELMET);
        dyeableItem(TFCColdSweatItems.WOOL_CHESTPLATE);
        dyeableItem(TFCColdSweatItems.WOOL_LEGGINGS);
        dyeableItem(TFCColdSweatItems.SILK_HELMET);
        dyeableItem(TFCColdSweatItems.SILK_CHESTPLATE);
        dyeableItem(TFCColdSweatItems.SILK_LEGGINGS);
        dyeableItem(TFCColdSweatItems.BURLAP_HELMET);
        dyeableItem(TFCColdSweatItems.BURLAP_CHESTPLATE);
        dyeableItem(TFCColdSweatItems.BURLAP_LEGGINGS);
    }

    private ItemModelBuilder simpleItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(), ResourceLocation.fromNamespaceAndPath("minecraft", "item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/"+item.getId().getPath()));
    }

    private void dyeableItem(DeferredItem<Item> item) {
        withExistingParent(item.getId().getPath(), ResourceLocation.fromNamespaceAndPath("minecraft", "item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/" + item.getId().getPath()))
                .texture("layer1", ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/" + item.getId().getPath() + "_overlay"));

    }
}
