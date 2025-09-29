package net.atobaazul.tfc_coldsweat.client;

import net.atobaazul.tfc_coldsweat.TFCColdSweatTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class TFCColdSweatIClientExtensions implements IClientItemExtensions {
    @Override
    public int getDefaultDyeColor(ItemStack stack) {
        int color = DyedItemColor.LEATHER_COLOR;
        if (stack.is(ItemTags.DYEABLE)) {
            if (stack.is(TFCColdSweatTags.BURLAP_CLOTHES)) {
                color = 11246192;
            } else if (stack.is(TFCColdSweatTags.WOOL_CLOTHES)) {
                color = 16383998;
            } else if (stack.is(TFCColdSweatTags.SILK_CLOTHES)) {
                color = 16383998;
            }
        }

        return stack.is(ItemTags.DYEABLE) ? FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(stack, color)) : 0xFFFFFFFF;
    }
}
