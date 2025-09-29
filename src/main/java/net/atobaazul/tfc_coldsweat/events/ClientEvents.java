package net.atobaazul.tfc_coldsweat.events;

import net.atobaazul.tfc_coldsweat.TFCColdSweat;
import net.atobaazul.tfc_coldsweat.client.TFCColdSweatIClientExtensions;
import net.atobaazul.tfc_coldsweat.common.TFCColdSweatItems;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;

@EventBusSubscriber(value = Dist.CLIENT, modid = TFCColdSweat.MOD_ID)
public class ClientEvents {
    //I Knowwwww but it apparently works.
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    private static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        ArrayList<DeferredItem<Item>> clothingItems = new ArrayList<>();
        clothingItems.add(TFCColdSweatItems.WOOL_LEGGINGS);
        clothingItems.add(TFCColdSweatItems.WOOL_CHESTPLATE);
        clothingItems.add(TFCColdSweatItems.WOOL_HELMET);
        clothingItems.add(TFCColdSweatItems.SILK_HELMET);
        clothingItems.add(TFCColdSweatItems.SILK_CHESTPLATE);
        clothingItems.add(TFCColdSweatItems.SILK_LEGGINGS);
        clothingItems.add(TFCColdSweatItems.BURLAP_CHESTPLATE);
        clothingItems.add(TFCColdSweatItems.BURLAP_LEGGINGS);
        clothingItems.add(TFCColdSweatItems.BURLAP_HELMET);

        TFCColdSweatIClientExtensions clientExtension = new TFCColdSweatIClientExtensions();

        for (DeferredItem<Item> clothingItem : clothingItems) {
            event.registerItem(clientExtension, clothingItem);
        }
    }
}
