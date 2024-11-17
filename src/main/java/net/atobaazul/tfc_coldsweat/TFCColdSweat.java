package net.atobaazul.tfc_coldsweat;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TFCColdSweat.MODID)
public class TFCColdSweat {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "tfc_coldsweat";


    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public TFCColdSweat() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }


}
