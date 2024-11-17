package net.atobaazul.tfc_coldsweat.modifier;

import com.momosoftworks.coldsweat.api.temperature.modifier.TempModifier;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.util.world.WorldHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.function.Function;

import static com.momosoftworks.coldsweat.util.world.WorldHelper.canSeeSky;

public class SunlightTempModifier extends TempModifier {
    public SunlightTempModifier() {}

    @Override
    protected Function<Double, Double> calculate(LivingEntity livingEntity, Temperature.Trait trait) {
        Level level = livingEntity.level();
        float dayTime = (level.getDayTime() % 24000L);
        double dayTimePercent =  0.5*Math.sin(dayTime*(Math.PI)/12000)+0.5;

        if (canSeeSky(level, livingEntity.blockPosition().above(), level.getMaxBuildHeight()) && !WorldHelper.isRainingAt(level, livingEntity.blockPosition())) {

            return temp -> temp + (Temperature.convert(5, Temperature.Units.C, Temperature.Units.MC, true)* dayTimePercent);
        } else {
            return temp -> temp;
        }
    }
}
