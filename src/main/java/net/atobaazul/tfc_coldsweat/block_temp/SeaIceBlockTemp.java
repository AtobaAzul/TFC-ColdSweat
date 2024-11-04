package net.atobaazul.tfc_coldsweat.block_temp;

import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.util.math.CSMath;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SeaIceBlockTemp extends BlockTemp
{
    public SeaIceBlockTemp()
    {   super(TFCBlocks.SEA_ICE.get());
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance)
    {
        return CSMath.blend(-0.15, 0, distance, 0.5, 7);
    }

    @Override
    public double maxEffect() {
        return 4;
    }

    @Override
    public double maxTemperature() {
        return Temperature.convert(-10, Temperature.Units.C, Temperature.Units.MC, true);
    }
}