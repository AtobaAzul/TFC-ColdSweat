package net.atobaazul.tfc_coldsweat;

import com.mojang.logging.LogUtils;
import com.momosoftworks.coldsweat.api.event.core.registry.TempModifierRegisterEvent;
import net.atobaazul.tfc_coldsweat.modifier.TFCSeasonTempModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.moddiscovery.ModFileInfo;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TFCColdSweat.MODID)
public class TFCColdSweat {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "tfc_coldsweat";

    public static final ModFileInfo firmalifeEnabled = FMLLoader.getLoadingModList().getModFileById("firmalife");
    public static final ModFileInfo castirongrillEnabled = FMLLoader.getLoadingModList().getModFileById("castirongrill");

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
