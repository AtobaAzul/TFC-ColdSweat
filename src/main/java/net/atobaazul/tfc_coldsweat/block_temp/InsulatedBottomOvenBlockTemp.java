package net.atobaazul.tfc_coldsweat.block_temp;



import com.eerussianguy.firmalife.common.blockentities.OvenBottomBlockEntity;
import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.eerussianguy.firmalife.common.blocks.OvenType;
import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.util.math.CSMath;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

//i'm gonna lose it why are all the bototm ovens different blocks instead of 2 blocks with blcosktate textures
public class InsulatedBottomOvenBlockTemp extends BlockTemp
{
    public InsulatedBottomOvenBlockTemp()
    {   super(FLBlocks.INSULATED_OVEN_BOTTOM.get(OvenType.BRICK).get(), FLBlocks.INSULATED_OVEN_BOTTOM.get(OvenType.RUSTIC).get(), FLBlocks.INSULATED_OVEN_BOTTOM.get(OvenType.TILE).get(),FLBlocks.INSULATED_OVEN_BOTTOM.get(OvenType.STONE).get() );
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance)
    {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof OvenBottomBlockEntity) { //divided by 40 instead of 30 to be a little less painful to do metalworking!
            return CSMath.blend(Temperature.convert(((OvenBottomBlockEntity) be).getTemperature(), Temperature.Units.C, Temperature.Units.MC, true), 0, distance, 0.5, 16)/40;
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