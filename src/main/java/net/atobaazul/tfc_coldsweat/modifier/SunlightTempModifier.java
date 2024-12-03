package net.atobaazul.tfc_coldsweat.modifier;

import com.momosoftworks.coldsweat.api.temperature.modifier.TempModifier;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.util.world.WorldHelper;
import net.atobaazul.tfc_coldsweat.TFCColdSweat;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.function.Function;

import static com.momosoftworks.coldsweat.util.world.WorldHelper.canSeeSky;

public class SunlightTempModifier extends TempModifier {
    public SunlightTempModifier() {
    }

    @Override
    protected Function<Double, Double> calculate(LivingEntity livingEntity, Temperature.Trait trait) {
        if (livingEntity instanceof Player player) {
            Level level = player.level();
            float dayTime = (level.getDayTime() % 24000L);
            double dayTimePercent = 0.5 * Math.sin(dayTime * (Math.PI) / 12000) + 0.5;

            if (canSeeSky(level, player.blockPosition().above(), level.getMaxBuildHeight())
                    && !WorldHelper.isRainingAt(level, livingEntity.blockPosition())
                    && !(player.getItemBySlot(EquipmentSlot.HEAD).is(TFCColdSweat.sunlightProtection)
                    || player.getItemBySlot(EquipmentSlot.MAINHAND).is(TFCColdSweat.sunlightProtection)
                    || player.getItemBySlot(EquipmentSlot.OFFHAND).is(TFCColdSweat.sunlightProtection))) {
                return temp -> temp + (Temperature.convert(7.5, Temperature.Units.C, Temperature.Units.MC, true) * dayTimePercent);
            } else {
                return temp -> temp;
            }
        }
        return temp -> temp;
    }
}
