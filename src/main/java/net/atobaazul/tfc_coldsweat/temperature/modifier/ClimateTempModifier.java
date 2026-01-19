package net.atobaazul.tfc_coldsweat.temperature.modifier;

import com.momosoftworks.coldsweat.api.temperature.modifier.TempModifier;
import com.momosoftworks.coldsweat.api.util.Temperature;
import net.dries007.tfc.util.climate.Climate;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Function;

public class ClimateTempModifier extends TempModifier {
    @Override
    public Function<Double, Double> calculate(LivingEntity livingEntity, Temperature.Trait trait) {
        float actual_temperature = Climate.getInstantTemperature(livingEntity.level(), livingEntity.blockPosition());
        return temp -> temp + Temperature.convert(actual_temperature, Temperature.Units.C, Temperature.Units.MC, true);
    }
}
