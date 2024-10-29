package net.atobaazul.tfc_coldsweat.modifier;

import com.momosoftworks.coldsweat.api.temperature.modifier.TempModifier;
import com.momosoftworks.coldsweat.api.util.Temperature;
import net.dries007.tfc.util.climate.Climate;
import net.minecraft.world.entity.LivingEntity;
import java.util.function.Function;

public class TFCSeasonTempModifier extends TempModifier {
    public TFCSeasonTempModifier() {}

    @Override
    public Function<Double, Double> calculate(LivingEntity livingEntity, Temperature.Trait trait) {
            float actual_temperature = Climate.getTemperature(livingEntity.level(), livingEntity.blockPosition());
            return temp -> temp + Temperature.convert(actual_temperature, Temperature.Units.C, Temperature.Units.MC, true);
    }
}
