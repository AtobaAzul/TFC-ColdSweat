package net.atobaazul.tfc_coldsweat;

import com.mojang.logging.LogUtils;
import net.atobaazul.tfc_coldsweat.common.ModArmorMaterials;
import net.atobaazul.tfc_coldsweat.common.TFCColdSweatItems;
import net.atobaazul.tfc_coldsweat.datagen.DataGenerators;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(TFCColdSweat.MOD_ID)
public class TFCColdSweat {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "tfc_coldsweat";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public TFCColdSweat(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(EventListener.class);

        TFCColdSweatItems.ITEMS.register(modEventBus);
        ModArmorMaterials.ARMOR_MATERIALS.register(modEventBus);

        modEventBus.addListener(this::registerColorHandler);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    private void registerColorHandler(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : DyedItemColor.getOrDefault(stack, 0xFFFFFFFF),
                TFCColdSweatItems.WOOL_LEGGINGS, TFCColdSweatItems.WOOL_CHESTPLATE, TFCColdSweatItems.WOOL_HELMET);
    }
}
