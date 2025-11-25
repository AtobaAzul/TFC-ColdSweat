package net.atobaazul.tfc_coldsweat.inv_temp;

import com.momosoftworks.coldsweat.api.temperature.modifier.InventoryItemsTempModifier;
import com.momosoftworks.coldsweat.api.temperature.modifier.TempModifier;
import com.momosoftworks.coldsweat.api.util.Temperature;
import net.dries007.tfc.common.capabilities.heat.HeatCapability;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class HotItemsTempModifier extends TempModifier {
    //For some reason requiring hotornot in the gradle breaks, so we'll just create this tag here.
    TagKey<Item> insulatingTag = TagKey.create(BuiltInRegistries.ITEM.key(), new ResourceLocation("tfchotornot", "insulating"));

    float totalHeat;
    float itemNumber;
    double itemTempScale = 0.025;

    @Override
    protected Function<Double, Double> calculate(LivingEntity entity, Temperature.Trait trait) {
        totalHeat = 0;
        itemNumber = 0;

        double heatMultiplier = entity.getOffhandItem().is(insulatingTag) ? 1 : 0.5;

        if (entity instanceof Player) {
            
            ((Player) entity).getInventory().items.forEach(item -> {
                float itemTemp = HeatCapability.getTemperature(item);

                if (itemTemp >= 480f) { //ignore tiny temps, since they'd be inconsequential anyway.
                    /*
                    Dividing by 10 (0.1x) gives roughly a range between 4.8 and 16 - which is fine for one item, but too much for several.
                    So diving 20 (0.05x) is enough to make individual items noticeable, and many hot items dangerous, but not a death sentence
                    */
                    itemNumber++; //Diminishing returns
                    totalHeat = (float) (totalHeat + Temperature.convert(itemTemp * itemTempScale, Temperature.Units.C, Temperature.Units.MC, false) / Math.sqrt(itemNumber));
                    //cap temp at 2 mc units (50ºC) so you don't get cremated if you hold too many ingots.
                    if (totalHeat > 2) {
                        totalHeat = 2;
                    }
                }
            });
        }
        return temp -> temp + (totalHeat * heatMultiplier);
    }
}
