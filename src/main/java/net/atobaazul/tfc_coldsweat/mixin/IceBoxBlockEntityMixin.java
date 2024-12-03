package net.atobaazul.tfc_coldsweat.mixin;


import com.momosoftworks.coldsweat.common.blockentity.HearthBlockEntity;
import com.momosoftworks.coldsweat.common.blockentity.IceboxBlockEntity;
import com.momosoftworks.coldsweat.config.ConfigSettings;
import net.atobaazul.tfc_coldsweat.TFCColdSweatFoodTraits;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.capabilities.food.FoodCapability;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IceboxBlockEntity.class)
public abstract class IceBoxBlockEntityMixin extends HearthBlockEntity {

    @Shadow
    @Final
    private ContainerOpenersCounter openersCounter;

    public IceBoxBlockEntityMixin(BlockEntityType type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Shadow
    public abstract int getFuel();

    @Shadow
    public abstract void setFuel(int amount);

    @Inject(method = "tick(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)V", at = @At("TAIL"), remap = false)
    private void tfc_coldsweat$iceboxTick(Level level, BlockState state, BlockPos pos, CallbackInfo ci) {
        boolean hasFoodItem = false;
        int stacksize = 0;

        for (int i = 1; i < 10; i++) {
            ItemStack stack = getItem(i);

            if (!stack.isEmpty() && stack.is(TFCTags.Items.FOODS)) {
                hasFoodItem = true;
                stacksize = stacksize + stack.getCount();
            }

            if (getFuel() > 0 && this.openersCounter.getOpenerCount() == 0) {
                if (!FoodCapability.hasTrait(stack, TFCColdSweatFoodTraits.CHILLED)) {
                    FoodCapability.applyTrait(stack, TFCColdSweatFoodTraits.CHILLED);
                }
            } else {
                if (FoodCapability.hasTrait(stack, TFCColdSweatFoodTraits.CHILLED)) {
                    FoodCapability.removeTrait(stack, TFCColdSweatFoodTraits.CHILLED);
                }
            }
        }

        if (ticksExisted % (int) (20 / Math.max(1, ConfigSettings.TEMP_RATE.get())) == 0) {
            if (hasFoodItem) setFuel((getFuel() - 1));
        }

    }
}
