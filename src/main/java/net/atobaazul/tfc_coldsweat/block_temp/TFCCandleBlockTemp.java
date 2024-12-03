package net.atobaazul.tfc_coldsweat.block_temp;

import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.util.math.CSMath;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.CandleBlock.LIT;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.CANDLES;

public class TFCCandleBlockTemp extends BlockTemp {


    public TFCCandleBlockTemp() {
        super(TFCBlocks.CANDLE.get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.BLACK).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.BLUE).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.BROWN).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.CYAN).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.GRAY).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.GREEN).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.LIGHT_BLUE).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.LIGHT_GRAY).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.LIME).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.MAGENTA).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.ORANGE).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.PINK).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.PURPLE).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.RED).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.WHITE).get(),
                TFCBlocks.DYED_CANDLE.get(DyeColor.YELLOW).get()
        );
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance) {
        return CSMath.blend(Temperature.convert(state.getValue(LIT) ? (2.5/4) * state.getValue(CANDLES) : 0, Temperature.Units.C, Temperature.Units.MC, false), 0, distance, 0.5, 4);
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