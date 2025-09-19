package net.atobaazul.tfc_coldsweat.temperature.block;

import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.util.math.CSMath;
import net.dries007.tfc.common.fluids.FluidProperty;
import net.dries007.tfc.common.fluids.IFluidLoggable;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class FluidLoggableBlockTemp extends BlockTemp {
    public FluidLoggableBlockTemp() {
        super(BuiltInRegistries.BLOCK.stream().filter(block -> block instanceof IFluidLoggable).toArray(Block[]::new));
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance) {
        FluidProperty.FluidKey fluidType = state.getValue(((IFluidLoggable) state.getBlock()).getFluidProperty());

        if (fluidType.getFluid() == Fluids.EMPTY) return 0;

        if (fluidType.is(Fluids.LAVA)) {
            return CSMath.blend(0.25, 0, distance, 0.5, 7);
        } else if (fluidType.is(TFCFluids.SPRING_WATER.getSource())) {
            return CSMath.blend(0.125, 0, distance, 0.5, 7);
        }
        return 0;
    }
}