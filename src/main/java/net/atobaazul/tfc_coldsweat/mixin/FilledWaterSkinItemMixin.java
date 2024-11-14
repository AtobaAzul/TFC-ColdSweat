package net.atobaazul.tfc_coldsweat.mixin;


import com.momosoftworks.coldsweat.common.item.FilledWaterskinItem;
import net.dries007.tfc.common.capabilities.food.TFCFoodData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FilledWaterskinItem.class)
public class FilledWaterSkinItemMixin extends Item {
    public FilledWaterSkinItemMixin(Properties pProperties) {
        super(pProperties);
    }

    @Inject(method="finishUsingItem", at=@At("TAIL"))
    private void tfc_coldsweat$finishUsingItem(ItemStack stack, Level level, LivingEntity entity, CallbackInfoReturnable<ItemStack> cir) {
        if (entity instanceof Player player && player.getFoodData() instanceof TFCFoodData foodData) {
            foodData.addThirst(10f);
        }
    }
}
