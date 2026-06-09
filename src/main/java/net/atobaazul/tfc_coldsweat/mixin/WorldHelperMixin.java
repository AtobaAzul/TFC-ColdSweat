package net.atobaazul.tfc_coldsweat.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.momosoftworks.coldsweat.compat.CompatManager;
import com.momosoftworks.coldsweat.config.ConfigSettings;
import com.momosoftworks.coldsweat.util.math.CSMath;
import com.momosoftworks.coldsweat.util.world.WorldHelper;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.dries007.tfc.util.EnvironmentHelpers;
import net.dries007.tfc.util.climate.Climate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.momosoftworks.coldsweat.util.world.WorldHelper.*;


@Mixin(WorldHelper.class)
public abstract class WorldHelperMixin {
    @Inject(method = "isRainingAt", at = @At("HEAD"), remap = false, cancellable = true)
    private static void tfc_coldsweat$isRainingAt(Level level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        {
            cir.setReturnValue(EnvironmentHelpers.isRainingOrSnowing(level, pos)
                            && Climate.getPrecipitation(level, pos) == Biome.Precipitation.RAIN
                            && canSeeSky(level, pos, level.getMaxBuildHeight())
                            || CompatManager.Weather2.isRainstormAt(level, pos)
                            && canSeeSky(level, pos.above(), level.getMaxBuildHeight())
                            && !CompatManager.SereneSeasons.isColdEnoughToSnow(level, pos));
        }
    }

    @WrapMethod(method = "isInWater", remap = false)
    private static boolean tfc_coldsweat$isInWater(Entity entity, Operation<Boolean> original) {
        return entity.isInFluidType(((fluidType, height) -> fluidType.equals(TFCFluids.SPRING_WATER.type().get()))) || original.call(entity);
    }

    @WrapMethod(method = "getWaterTemperatureAt", remap = false)
    private static double tfc_coldsweat$getWaterTemperatureAt(Level level, BlockPos pos, Operation<Double> original) {
        pos = sublevelToWorld(level, pos);
        Holder<Biome> biome = level.getBiome(pos);
        double biomeTemp = CSMath.averagePair(getBiomeTemperatureRange(level, biome));

        if (level.getBlockState(pos).is(TFCBlocks.SPRING_WATER.get())) {
            return Math.abs(getDefaultWaterTemp(biomeTemp));
        }
        return original.call(level, pos);
    }
}
