package net.atobaazul.tfc_coldsweat;

import com.momosoftworks.coldsweat.api.event.core.init.GatherDefaultTempModifiersEvent;
import com.momosoftworks.coldsweat.api.event.core.registry.BlockTempRegisterEvent;
import com.momosoftworks.coldsweat.api.event.core.registry.TempModifierRegisterEvent;
import com.momosoftworks.coldsweat.api.temperature.modifier.BiomeTempModifier;
import com.momosoftworks.coldsweat.api.temperature.modifier.TempModifier;
import com.momosoftworks.coldsweat.api.temperature.modifier.UndergroundTempModifier;
import com.momosoftworks.coldsweat.api.util.Placement;
import com.momosoftworks.coldsweat.api.util.Temperature;
import net.atobaazul.tfc_coldsweat.block_temp.*;
import net.atobaazul.tfc_coldsweat.inv_temp.HotItemsTempModifier;
import net.atobaazul.tfc_coldsweat.modifier.TFCSeasonTempModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.momosoftworks.coldsweat.common.capability.handler.EntityTempManager.isTemperatureEnabled;

@Mod.EventBusSubscriber
public class EventListener {
    public final static TempModifier TFCSeasonModifier = new TFCSeasonTempModifier();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void defineDefaultModifiers(GatherDefaultTempModifiersEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getTrait() == Temperature.Trait.WORLD) {
                event.addModifier(TFCSeasonModifier, Placement.Duplicates.BY_CLASS, Placement.BEFORE_FIRST);
                event.addModifier(new HotItemsTempModifier(), Placement.Duplicates.BY_CLASS, Placement.BEFORE_FIRST);
            }
        }
    }

    //Due to priority reasons, this is a seperate event at a lower priority than the one above.
    @SubscribeEvent()
    public static void onGatherDefaultTempModifiers(GatherDefaultTempModifiersEvent event) {
        if (event.getEntity() instanceof Player) {
            event.getModifiers().removeIf(modifier ->
                    modifier instanceof BiomeTempModifier
                            || modifier instanceof UndergroundTempModifier);
        }
    }

    @SubscribeEvent
    public static void registerTempModifiers(TempModifierRegisterEvent event) {
        event.register(new ResourceLocation(TFCColdSweat.MODID, "season"), TFCSeasonTempModifier::new);
        event.register(new ResourceLocation(TFCColdSweat.MODID, "hot_items"), HotItemsTempModifier::new);
    }

    @SubscribeEvent
    public static void registerBlockTemps(BlockTempRegisterEvent event) {
        event.register(new BlastFurnaceBlockTemp());
        event.register(new BloomeryBlockTemp());
        event.register(new CharcoalForgeBlockTemp());
        event.register(new FirepitBlockTemp());
        event.register(new GrillBlockTemp());
        event.register(new PotBlockTemp());
        event.register(new SpringWaterBlockTemp());
        event.register(new TFCTorchBlockTemp());

        if (TFCColdSweat.firmalifeEnabled != null) {

            event.register(new BottomOvenBlockTemp());
            event.register(new InsulatedBottomOvenBlockTemp());
        }

        if (TFCColdSweat.castirongrillEnabled != null) {
            event.register(new CastIronGrillBlockTemp());
        }
    }
}
