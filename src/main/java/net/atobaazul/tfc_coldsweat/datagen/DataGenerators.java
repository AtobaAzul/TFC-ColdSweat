package net.atobaazul.tfc_coldsweat.datagen;

import net.atobaazul.tfc_coldsweat.datagen.providers.TFCColdSweatBlockTagProvider;
import net.atobaazul.tfc_coldsweat.datagen.providers.TFCColdSweatItemModelProvider;
import net.atobaazul.tfc_coldsweat.datagen.providers.TFCColdSweatItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        TFCColdSweatBlockTagProvider blockTagProvider = generator.addProvider(event.includeServer(), new TFCColdSweatBlockTagProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new TFCColdSweatItemTagsProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeClient(), new TFCColdSweatItemModelProvider(packOutput, existingFileHelper));
    }
}
