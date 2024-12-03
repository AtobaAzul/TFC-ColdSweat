package net.atobaazul.tfc_coldsweat.block_temp;

import com.hermitowo.castirongrill.common.blockentities.CastIronGrillBlockEntity;
import com.hermitowo.castirongrill.common.blocks.CIGBlocks;
import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.util.math.CSMath;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CastIronGrillBlockTemp extends BlockTemp {
    public CastIronGrillBlockTemp() {
        super(CIGBlocks.CAST_IRON_GRILL_FIREPIT.get());
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof CastIronGrillBlockEntity) {
            return CSMath.blend(Temperature.convert(((CastIronGrillBlockEntity) be).getTemperature(), Temperature.Units.C, Temperature.Units.MC, true), 0, distance, 0.5, 16) / 30;
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