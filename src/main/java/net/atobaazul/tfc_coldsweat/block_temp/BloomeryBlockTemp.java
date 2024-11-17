package net.atobaazul.tfc_coldsweat.block_temp;

import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.api.util.Temperature;
import net.dries007.tfc.common.blockentities.PotBlockEntity;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class BloomeryBlockTemp extends BlockTemp
{
    public BloomeryBlockTemp()
    {   super(TFCBlocks.BLOOMERY.get(), TFCBlocks.MOLTEN.get()); //putting molten here because it just checks for State.
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance)
    {
        return state.getValue(BlockStateProperties.LIT) ? Temperature.convert(50, Temperature.Units.C, Temperature.Units.MC, true) : 0.0;
    }

    @Override
    public double maxEffect() {
        return Temperature.convert(100, Temperature.Units.C, Temperature.Units.MC, false);
    }

    @Override
    public double maxTemperature() {
        return Temperature.convert(40, Temperature.Units.C, Temperature.Units.MC, true);
    }
}