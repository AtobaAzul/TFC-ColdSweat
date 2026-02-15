package net.atobaazul.tfc_coldsweat.temperature.block.compat;

import com.eerussianguy.firmalife.common.blockentities.OvenBottomBlockEntity;
import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.util.math.CSMath;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BottomOvenBlockTemp extends BlockTemp {
    public BottomOvenBlockTemp() { //I'm not sure if there's a way to filter blocks by their BE, so I'm just getting everything.
        super(BuiltInRegistries.BLOCK.stream().toArray(Block[]::new));
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance) {
        BlockEntity be = level.getBlockEntity(pos);

        if (be instanceof OvenBottomBlockEntity oven) {
            return CSMath.blend(Temperature.convert(oven.getTemperature(), Temperature.Units.C, Temperature.Units.MC, true), 0, distance, 0.5, 16) / 45;
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
