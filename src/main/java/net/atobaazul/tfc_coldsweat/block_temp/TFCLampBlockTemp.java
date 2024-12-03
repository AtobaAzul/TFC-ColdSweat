package net.atobaazul.tfc_coldsweat.block_temp;

import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.util.math.CSMath;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.util.Metal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import static net.dries007.tfc.common.blocks.devices.LampBlock.LIT;

public class TFCLampBlockTemp extends BlockTemp {


    public TFCLampBlockTemp() {
        super(TFCBlocks.METALS.get(Metal.Default.BISMUTH_BRONZE).get(Metal.BlockType.LAMP).get(),
                TFCBlocks.METALS.get(Metal.Default.BLACK_BRONZE).get(Metal.BlockType.LAMP).get(),
                TFCBlocks.METALS.get(Metal.Default.BRONZE).get(Metal.BlockType.LAMP).get(),
                TFCBlocks.METALS.get(Metal.Default.COPPER).get(Metal.BlockType.LAMP).get(),
                TFCBlocks.METALS.get(Metal.Default.WROUGHT_IRON).get(Metal.BlockType.LAMP).get(),
                TFCBlocks.METALS.get(Metal.Default.STEEL).get(Metal.BlockType.LAMP).get(),
                TFCBlocks.METALS.get(Metal.Default.BLUE_STEEL).get(Metal.BlockType.LAMP).get(),
                TFCBlocks.METALS.get(Metal.Default.RED_STEEL).get(Metal.BlockType.LAMP).get()
        );
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance) {
        return CSMath.blend(Temperature.convert(state.getValue(LIT) ? 2.5 : 0, Temperature.Units.C, Temperature.Units.MC, false), 0, distance, 0.5, 4);
    }

    @Override
    public double maxEffect() {
        return Temperature.convert(15.0, Temperature.Units.C, Temperature.Units.MC, false);
    }

    @Override
    public double maxTemperature() {
        return Temperature.convert(20, Temperature.Units.C, Temperature.Units.MC, true);
    }
}