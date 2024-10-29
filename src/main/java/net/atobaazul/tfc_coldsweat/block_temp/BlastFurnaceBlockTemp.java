package net.atobaazul.tfc_coldsweat.block_temp;

import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.util.math.CSMath;
import net.dries007.tfc.common.blockentities.BlastFurnaceBlockEntity;
import net.dries007.tfc.common.blockentities.CharcoalForgeBlockEntity;
import net.dries007.tfc.common.blockentities.GrillBlockEntity;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlastFurnaceBlockTemp extends BlockTemp
{
    public BlastFurnaceBlockTemp()
    {   super(TFCBlocks.BLAST_FURNACE.get());
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance)
    {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof BlastFurnaceBlockEntity) {
            return CSMath.blend(Temperature.convert(((BlastFurnaceBlockEntity) be).getTemperature(), Temperature.Units.C, Temperature.Units.MC, true), 0, distance, 0.5, 16)/30;
        }
        return 0.0;
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