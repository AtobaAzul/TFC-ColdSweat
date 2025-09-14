package net.atobaazul.tfc_coldsweat.temperature.modifier;

import com.momosoftworks.coldsweat.api.temperature.modifier.TempModifier;
import com.momosoftworks.coldsweat.api.util.Temperature;
import net.dries007.tfc.common.component.heat.HeatCapability;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Function;

public class ItemHeatTempModifier extends TempModifier {

    @Override
    protected Function<Double, Double> calculate(LivingEntity entity, Temperature.Trait trait) {
        float[] totalHeat = {0};
        final float[] itemNumber = {0};
        double itemTempScale = 0.025;
        //TODO: Re-add hot or not integration if that comes to 1.21
        double heatMultiplier = 1; //entity.getOffhandItem().is(insulatingTag) ? 1 : 0.5;

        if (entity instanceof Player) {
            ((Player) entity).getInventory().items.forEach(item -> {
                float itemTemp = HeatCapability.getTemperature(item);
                if (itemTemp >= 480f) {
                    itemNumber[0] = itemNumber[0] + 1; //Diminishing returns
                    totalHeat[0] = (float) (totalHeat[0] + Temperature.convert(itemTemp * itemTempScale, Temperature.Units.C, Temperature.Units.MC, false) / Math.sqrt(itemNumber[0]));
                    //cap temp at 2 mc units (50ºC) so you don't get cremated if you hold too many ingots.
                    if (totalHeat[0] > 2) {
                        totalHeat[0] = 2;
                    }

                }
            });
        }
        return temp -> temp + (totalHeat[0] * heatMultiplier);
    }
}