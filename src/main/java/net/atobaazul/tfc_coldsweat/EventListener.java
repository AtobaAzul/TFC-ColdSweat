package net.atobaazul.tfc_coldsweat;

import com.momosoftworks.coldsweat.api.event.core.init.GatherDefaultTempModifiersEvent;
import com.momosoftworks.coldsweat.api.event.core.registry.BlockTempRegisterEvent;
import com.momosoftworks.coldsweat.api.event.core.registry.TempModifierRegisterEvent;
import com.momosoftworks.coldsweat.api.temperature.modifier.BiomeTempModifier;
import com.momosoftworks.coldsweat.api.temperature.modifier.DepthBiomeTempModifier;
import com.momosoftworks.coldsweat.api.temperature.modifier.ElevationTempModifier;
import com.momosoftworks.coldsweat.api.temperature.modifier.TempModifier;
import com.momosoftworks.coldsweat.api.util.Placement;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.config.ConfigSettings;
import com.momosoftworks.coldsweat.util.registries.ModEffects;
import com.momosoftworks.coldsweat.util.world.WorldHelper;
import net.atobaazul.tfc_coldsweat.block_temp.*;
import net.atobaazul.tfc_coldsweat.inv_temp.HotItemsTempModifier;
import net.atobaazul.tfc_coldsweat.modifier.ClimateTempModifier;
import net.atobaazul.tfc_coldsweat.modifier.SunlightTempModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventListener {
    public final static TempModifier TFCSeasonModifier = new ClimateTempModifier();
    public static final TempModifier TFCSunlightModifier = new SunlightTempModifier();

    private static double getSeasonalGraceDuration(Level level, Player player) {
        int baseDuration = ConfigSettings.GRACE_LENGTH.get();
        double baseTemp = Temperature.convert(27, Temperature.Units.C, Temperature.Units.MC, true);
        double tempAtSpawn = WorldHelper.getTemperatureAt(level, player.blockPosition());
        double tempPercent = baseTemp / tempAtSpawn;
        if (tempPercent < 1) {
            tempPercent = 1 + (1 - tempPercent);
        }

        tempPercent = Math.min(tempPercent, 2);
        return baseDuration * tempPercent;
    }

    @SubscribeEvent
    public static void onSpawn(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide && event.getEntity() instanceof Player
                && ConfigSettings.GRACE_ENABLED.get() && !event.getEntity().getPersistentData().getBoolean("GivenGracePeriod")) {
            event.getEntity().getPersistentData().putBoolean("GivenGracePeriod", true);
            ((Player) event.getEntity()).addEffect(new MobEffectInstance(ModEffects.GRACE, (int) getSeasonalGraceDuration(event.getLevel(), (Player) event.getEntity()), 0, false, false, true));
        }
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void defineDefaultModifiers(GatherDefaultTempModifiersEvent event) {
        if (event.getEntity() instanceof Player) {
            if (CompatManager.TFC_ENABLED != null) {
                if (event.getTrait() == Temperature.Trait.WORLD) {
                    event.addModifier(TFCSeasonModifier, Placement.Duplicates.BY_CLASS, Placement.BEFORE_FIRST);
                    event.addModifier(TFCSunlightModifier, Placement.Duplicates.BY_CLASS, Placement.BEFORE_FIRST);
                    event.addModifier(new HotItemsTempModifier(), Placement.Duplicates.BY_CLASS, Placement.BEFORE_FIRST);
                }
            }
        }
    }

    //Due to priority reasons, this is a seperate event at a lower priority than the one above.
    @SubscribeEvent()
    public static void onGatherDefaultTempModifiers(GatherDefaultTempModifiersEvent event) {
        if (event.getEntity() instanceof Player) {
            if (CompatManager.TFC_ENABLED != null) {
                event.getModifiers().removeIf(modifier ->
                        modifier instanceof BiomeTempModifier
                                || modifier instanceof DepthBiomeTempModifier || modifier instanceof ElevationTempModifier);
            }
        }
    }

    @SubscribeEvent
    public static void registerTempModifiers(TempModifierRegisterEvent event) {
        if (CompatManager.TFC_ENABLED != null) {
            event.register(new ResourceLocation(TFCColdSweat.MODID, "season"), ClimateTempModifier::new);
            event.register(new ResourceLocation(TFCColdSweat.MODID, "sunlight"), SunlightTempModifier::new);
            event.register(new ResourceLocation(TFCColdSweat.MODID, "hot_items"), HotItemsTempModifier::new);
        }
    }

    @SubscribeEvent
    public static void registerBlockTemps(BlockTempRegisterEvent event) {
        if (CompatManager.TFC_ENABLED != null) {
            event.register(new BlastFurnaceBlockTemp());
            event.register(new BloomeryBlockTemp());
            event.register(new CharcoalForgeBlockTemp());
            event.register(new FirepitBlockTemp());
            event.register(new GrillBlockTemp());
            event.register(new PotBlockTemp());
            event.register(new SpringWaterBlockTemp());
            event.register(new TFCTorchBlockTemp());
            event.register(new TFCMagmaBlockTemp());
            event.register(new SeaIceBlockTemp());
            event.register(new CrucibleBlockTemp());
            event.register(new TFCCandleBlockTemp());
            event.register(new TFCLampBlockTemp());
            event.register(new FluidLoggableTemp());
        }

        if (CompatManager.FIRMALIFE_ENABLED != null) {
            event.register(new BottomOvenBlockTemp());
            event.register(new InsulatedBottomOvenBlockTemp());
        }

        if (CompatManager.CASTIRONGRILL_ENABLED != null) {
            event.register(new CastIronGrillBlockTemp());
        }
    }
}
