package net.atobaazul.tfc_coldsweat;

import net.dries007.tfc.common.capabilities.food.FoodTrait;
import net.dries007.tfc.util.Helpers;

@SuppressWarnings("unused")
public class TFCColdSweatFoodTraits {

    public static final FoodTrait CHILLED = register("chilled", 0.33f);

    public static void registerFoodTraits() { }

    private static FoodTrait register(String name, float decayModifier)
    {
        return FoodTrait.register(Helpers.identifier(name), new FoodTrait(() -> decayModifier, "tfc.tooltip.food_trait." + name));
    }

}
