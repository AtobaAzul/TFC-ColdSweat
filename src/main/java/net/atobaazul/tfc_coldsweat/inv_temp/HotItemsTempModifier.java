package net.atobaazul.tfc_coldsweat.inv_temp;

import com.momosoftworks.coldsweat.api.temperature.modifier.InventoryItemsTempModifier;
import com.momosoftworks.coldsweat.api.util.Temperature;
import net.dries007.tfc.common.capabilities.heat.HeatCapability;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class HotItemsTempModifier extends InventoryItemsTempModifier {
    TagKey<Item> insulatingTag = TagKey.create(
            BuiltInRegistries.ITEM.key(),
            new ResourceLocation("tfchotornot", "insulating"));

    @Override
    protected Function<Double, Double> calculate(LivingEntity entity, Temperature.Trait trait) {
        final float[] totalHeat = {0};
        if (entity instanceof Player) {
            ((Player) entity).getInventory().items.forEach(item -> {
                if (HeatCapability.getTemperature(item) > 0 && totalHeat[0] < 1) {
                    double heatValue = entity.getOffhandItem().is(insulatingTag) ? 80 : 40;
                    double itemTemp = Temperature.convert(HeatCapability.getTemperature(item), Temperature.Units.C, Temperature.Units.MC, false) / heatValue;
                    totalHeat[0] = (float) (totalHeat[0] + itemTemp);
                } else if (totalHeat[0] > 1) {
                    totalHeat[0] = 1;
                }
            });
        }
        return temp -> temp + totalHeat[0];
    }
}
