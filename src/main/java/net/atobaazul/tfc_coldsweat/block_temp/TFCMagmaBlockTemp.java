package net.atobaazul.tfc_coldsweat.block_temp;

import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.util.math.CSMath;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class TFCMagmaBlockTemp extends BlockTemp {
    public TFCMagmaBlockTemp() {

        super(TFCBlocks.MAGMA_BLOCKS.get(Rock.GRANITE).get(),
                TFCBlocks.MAGMA_BLOCKS.get(Rock.DIORITE).get(),
                TFCBlocks.MAGMA_BLOCKS.get(Rock.GABBRO).get(),
                TFCBlocks.MAGMA_BLOCKS.get(Rock.RHYOLITE).get(),
                TFCBlocks.MAGMA_BLOCKS.get(Rock.BASALT).get(),
                TFCBlocks.MAGMA_BLOCKS.get(Rock.ANDESITE).get(),
                TFCBlocks.MAGMA_BLOCKS.get(Rock.DACITE).get()
        );
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance) {
        return CSMath.blend(1, 0, distance, 0.5, 7);
    }

    //not realistic values, but for the sake of balancing instead.
    @Override
    public double maxEffect() {
        return 3;
    }

    @Override
    public double maxTemperature() {
        return Temperature.convert(100, Temperature.Units.C, Temperature.Units.MC, true);
    }
}