package net.atobaazul.tfc_coldsweat.events;

import com.momosoftworks.coldsweat.api.event.core.init.DefaultTempModifiersEvent;
import com.momosoftworks.coldsweat.api.event.core.registry.BlockTempRegisterEvent;
import com.momosoftworks.coldsweat.api.event.core.registry.TempModifierRegisterEvent;
import com.momosoftworks.coldsweat.api.temperature.modifier.*;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.api.util.placement.Matcher;
import com.momosoftworks.coldsweat.api.util.placement.Placement;
import com.momosoftworks.coldsweat.config.ConfigSettings;
import com.momosoftworks.coldsweat.core.init.ModEffects;
import com.momosoftworks.coldsweat.core.init.ModItems;
import com.momosoftworks.coldsweat.util.world.WorldHelper;
import net.atobaazul.tfc_coldsweat.CompatManager;
import net.atobaazul.tfc_coldsweat.TFCColdSweat;
import net.atobaazul.tfc_coldsweat.temperature.block.*;
import net.atobaazul.tfc_coldsweat.temperature.block.compat.ApplianceBlockTemp;
import net.atobaazul.tfc_coldsweat.temperature.block.compat.BottomOvenBlockTemp;
import net.atobaazul.tfc_coldsweat.temperature.modifier.ClimateTempModifier;
import net.atobaazul.tfc_coldsweat.temperature.modifier.ItemHeatTempModifier;
import net.dries007.tfc.common.player.IPlayerInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

@EventBusSubscriber
public class Events {
    public static final TempModifier TFCSeasonModifier = new ClimateTempModifier();
    public static final TempModifier ItemTempModifier = new ItemHeatTempModifier();

    //scale grace effect duration with temperature at spawn.
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
        if (!event.getLevel().isClientSide && event.getEntity() instanceof Player && ConfigSettings.GRACE_ENABLED.get() && !event.getEntity().getPersistentData().getBoolean("GivenGracePeriod")) {
            event.getEntity().getPersistentData().putBoolean("GivenGracePeriod", true);
            ((Player) event.getEntity()).addEffect(new MobEffectInstance(ModEffects.GRACE, (int) getSeasonalGraceDuration(event.getLevel(), (Player) event.getEntity()), 0, false, false, true));
        }
    }

    @SubscribeEvent
    public static void onUseItem(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player && event.getItem().is(ModItems.FILLED_WATERSKIN)) {
            IPlayerInfo.get(player).addThirst(20f);
        }
    }

    @SubscribeEvent()
    public static void defineDefaultModifiers(DefaultTempModifiersEvent event) {
        if (event.getEntity() instanceof Player) {
            if (CompatManager.TFC_ENABLED != null) {
                event.addModifier(Temperature.Trait.WORLD, TFCSeasonModifier, Placement.LAST.noDuplicates(Matcher.SAME_CLASS));
                event.addModifier(Temperature.Trait.CORE, ItemTempModifier, Placement.LAST.noDuplicates(Matcher.SAME_CLASS));
                event.getModifiers(Temperature.Trait.WORLD).removeIf(modifier -> modifier instanceof BiomeTempModifier || modifier instanceof CaveBiomeTempModifier || modifier instanceof ElevationTempModifier || modifier instanceof ShadeTempModifier);
            }
        }
    }

    @SubscribeEvent
    public static void registerTempModifiers(TempModifierRegisterEvent event) {
        if (CompatManager.TFC_ENABLED != null) {
            event.register(ResourceLocation.fromNamespaceAndPath(TFCColdSweat.MOD_ID, "climate"), ClimateTempModifier::new);
            event.register(ResourceLocation.fromNamespaceAndPath(TFCColdSweat.MOD_ID, "inventory_item_heat"), ItemHeatTempModifier::new);
        }
    }

    @SubscribeEvent
    public static void registerBlockTemps(BlockTempRegisterEvent event) {
        if (CompatManager.TFC_ENABLED != null) {
            event.register(new BloomeryBlockTemp());
            event.register(new CharcoalForgeBlockTemp());
            event.register(new SpringWaterBlockTemp());
            event.register(new TFCTorchBlockTemp());
            event.register(new TFCMagmaBlockTemp());
            event.register(new SeaIceBlockTemp());
            event.register(new TFCCandleBlockTemp());
            event.register(new TFCLampBlockTemp());
            event.register(new FluidLoggableBlockTemp());
            event.register(new IHeatableBlockTemp());
        }

        if (CompatManager.FIRMALIFE_ENABLED != null) {
            event.register(new BottomOvenBlockTemp());
            event.register(new ApplianceBlockTemp());
        }
    }
}
