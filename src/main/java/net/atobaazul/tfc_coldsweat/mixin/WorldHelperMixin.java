package net.atobaazul.tfc_coldsweat.mixin;

import com.momosoftworks.coldsweat.util.compat.CompatManager;
import com.momosoftworks.coldsweat.util.world.WorldHelper;
import net.dries007.tfc.util.EnvironmentHelpers;
import net.dries007.tfc.util.climate.Climate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.momosoftworks.coldsweat.util.world.WorldHelper.canSeeSky;


@Mixin(WorldHelper.class)
public abstract class WorldHelperMixin {
    @Inject(method = "isRainingAt", at = @At("HEAD"), remap = false, cancellable = true)
    private static void tfc_coldsweat$isRainingAt(Level level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        {
            cir.setReturnValue(
                    EnvironmentHelpers.isRainingOrSnowing(level, pos)
                            && Climate.getPrecipitation(level, pos) == Biome.Precipitation.RAIN
                            && canSeeSky(level, pos, level.getMaxBuildHeight())
                            || CompatManager.isRainstormAt(level, pos)
                            && canSeeSky(level, pos.above(), level.getMaxBuildHeight())
                            && !CompatManager.isColdEnoughToSnow(level, pos));
        }
    }
}
