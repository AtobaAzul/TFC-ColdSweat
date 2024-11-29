package net.atobaazul.tfc_coldsweat.mixin.compat;

import com.alekiponi.alekiships.common.entity.vehiclehelper.compartment.EmptyCompartmentEntity;
import com.momosoftworks.coldsweat.util.world.WorldHelper;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(WorldHelper.class)
public abstract class AlekishipsWorldHelperMixin {
    @Inject(method = "isInWater", at = @At("HEAD"), remap = false, cancellable = true)
    private static void tfc_coldsweat$isInWater(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity.getVehicle() instanceof EmptyCompartmentEntity) {
            cir.setReturnValue(false);
        }
    }
}
