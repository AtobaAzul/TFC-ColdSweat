package net.atobaazul.tfc_coldsweat;

import com.mojang.logging.LogUtils;
import com.momosoftworks.coldsweat.core.init.ModItems;
import net.dries007.tfc.common.player.IPlayerInfo;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
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


        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (TFCColdSweat) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        EventListener.init(NeoForge.EVENT_BUS);
    }

    //TODO: Duplicate test event.
    @SubscribeEvent
    public static void onUseItem(LivingEntityUseItemEvent.Finish event) {
        System.out.println("event fired");
        if (event.getEntity() instanceof Player player && event.getItem().is(ModItems.FILLED_WATERSKIN)) {
            IPlayerInfo.get(player).addThirst(20f);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }
}
