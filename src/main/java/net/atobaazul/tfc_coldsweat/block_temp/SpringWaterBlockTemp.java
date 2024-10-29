package net.atobaazul.tfc_coldsweat.block_temp;

import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.api.util.Temperature;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import com.momosoftworks.coldsweat.util.math.CSMath;
import net.minecraft.world.level.material.FluidState;

public class SpringWaterBlockTemp extends BlockTemp
{
    public SpringWaterBlockTemp()
    {   super(TFCBlocks.SPRING_WATER.get());
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance)
    {   FluidState fluidState = state.getFluidState();
        double temp = (fluidState.getAmount()/2f);
        return CSMath.blend(temp, 0, distance, 0.5, 7);
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