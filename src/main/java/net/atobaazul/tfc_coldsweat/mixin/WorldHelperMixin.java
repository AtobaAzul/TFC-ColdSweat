package net.atobaazul.tfc_coldsweat.mixin;


import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.momosoftworks.coldsweat.config.ConfigSettings;
import com.momosoftworks.coldsweat.util.world.WorldHelper;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.dries007.tfc.util.tracker.WeatherHelpers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.momosoftworks.coldsweat.util.world.WorldHelper.canSeeSky;

@Mixin(WorldHelper.class)
public class WorldHelperMixin {
    @Inject(method = "isRainingAt", at = @At("HEAD"), remap = false, cancellable = true)
    private static void tfc_coldsweat$isRainingAt(Level level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(WeatherHelpers.getPrecipitationAt(level, pos, Biome.Precipitation.NONE) == Biome.Precipitation.RAIN && canSeeSky(level, pos, level.getMaxBuildHeight()));
    }

    @WrapMethod(method="isInWater")
    private static boolean tfc_coldsweat$isInWater(Entity entity, Operation<Boolean> original) {
        return entity.isInFluidType(TFCFluids.SPRING_WATER.getType()) || original.call(entity);
    }

    @Inject(method = "shouldMelt", at = @At("HEAD"), remap = false, cancellable = true)
    private static void tfc_coldsweat$shouldMelt(LevelAccessor levelReader, BlockPos pos, boolean mustBeAtEdge, CallbackInfoReturnable<Boolean> cir) {
        if (pos.getY() >= levelReader.getMinBuildHeight() && pos.getY() < levelReader.getMaxBuildHeight() && levelReader instanceof ServerLevel serverLevel) {
            BlockState state = serverLevel.getBlockState(pos);
            if (state.is(TFCBlocks.SEA_ICE.get())) {
                cir.setReturnValue(false);
            }
        }
    }

    @WrapMethod(method = "getWaterTemperatureAt", remap= false)
    private static double tfc_coldsweat$getWaterTemperatureAt(Level level, BlockPos pos, Operation<Double> original) {
        if (level.getBlockState(pos).is(TFCBlocks.SPRING_WATER.get())) {
            return Math.abs(ConfigSettings.DEFAULT_WATER_TEMPERATURE.get());
        }
        return original.call(level, pos);
    }
}
