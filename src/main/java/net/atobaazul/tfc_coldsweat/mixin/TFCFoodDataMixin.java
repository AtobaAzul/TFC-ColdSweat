package net.atobaazul.tfc_coldsweat.mixin;

import com.momosoftworks.coldsweat.api.util.Temperature;
import net.dries007.tfc.common.capabilities.food.TFCFoodData;
import net.dries007.tfc.config.TFCConfig;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.momosoftworks.coldsweat.util.world.WorldHelper.getTemperatureAt;
import static net.dries007.tfc.common.capabilities.food.TFCFoodData.MAX_TEMPERATURE_THIRST_DECAY;

@Mixin(TFCFoodData.class)
public class TFCFoodDataMixin {
    @Inject(method = "getThirstContributionFromTemperature", at = @At("HEAD"), remap = false, cancellable = true)
    private void getThirstContributionFromTemperature(Player player, CallbackInfoReturnable<Float> cir) {
        if (TFCConfig.SERVER.enableThirstOverheating.get()) {
            final double temp = Temperature.convert(getTemperatureAt(player.level(), player.blockPosition()), Temperature.Units.MC, Temperature.Units.C, true);

            cir.setReturnValue((float) Mth.clampedMap(temp, 22f, 34f, 0f, MAX_TEMPERATURE_THIRST_DECAY));
        }
    }
}
