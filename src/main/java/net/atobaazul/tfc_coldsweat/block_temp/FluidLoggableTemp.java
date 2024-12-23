package net.atobaazul.tfc_coldsweat.block_temp;

import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.util.math.CSMath;
import net.dries007.tfc.common.blocks.TFCBlockStateProperties;
import net.dries007.tfc.common.fluids.IFluidLoggable;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidLoggableTemp extends BlockTemp {
    public FluidLoggableTemp() {
        super(ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block instanceof IFluidLoggable).toArray(Block[]::new));
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance) {
        final Fluid containedFluid = state.getValue(TFCBlockStateProperties.ALL_WATER).getFluid();
        if (containedFluid == TFCFluids.SPRING_WATER.getSource()) {
            return CSMath.blend(1.5, 0, distance, 0.5, 7);
        }
        return 0;
    }

    @Override
    public double maxEffect() {
        return Temperature.convert(37.0, Temperature.Units.C, Temperature.Units.MC, false);
    }

    @Override
    public double maxTemperature() {
        return Temperature.convert(40, Temperature.Units.C, Temperature.Units.MC, true);
    }
}