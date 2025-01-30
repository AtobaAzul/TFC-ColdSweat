package net.atobaazul.tfc_coldsweat.blocks;

import net.atobaazul.tfc_coldsweat.blockentities.TFCColdSweatTickCounterBlockEntity;
import net.atobaazul.tfc_coldsweat.registries.TFCColdSweatBlockEntities;
import net.dries007.tfc.common.blocks.EntityBlockExtension;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.dries007.tfc.common.blocks.TFCBubbleColumnBlock;
import net.dries007.tfc.config.TFCConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class TFCColdSweatMagmaBlock extends MagmaBlock implements IForgeBlockExtension, EntityBlockExtension {
    private final ResourceLocation cooled;
    private final ExtendedProperties properties;

    public TFCColdSweatMagmaBlock(Properties properties, ResourceLocation cooled, ExtendedProperties properties1) {
        super(properties);
        this.cooled = cooled;
        this.properties = properties1;
    }

    public static void onRandomTick(ServerLevel level, BlockPos pos, BlockState placeState) {

        level.getBlockEntity(pos, TFCColdSweatBlockEntities.TICK_COUNTER.get()).ifPresent(magma -> {
            final int torchTicks = TFCConfig.SERVER.torchTicks.get();
            if (magma.getTicksSinceUpdate() > torchTicks && torchTicks > 0) {
                level.setBlockAndUpdate(pos, placeState);
            }
        });
    }


    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState faceState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.UP && TFCBubbleColumnBlock.canExistIn(faceState.getFluidState().getType())) {
            level.scheduleTick(currentPos, this, 20);
        }
        return state;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        TFCBubbleColumnBlock.updateColumnForFluid(level, pos);
    }

    @Override
    public ExtendedProperties getExtendedProperties() {
        return this.properties;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        System.out.println(this.cooled);
        onRandomTick(level, pos, ForgeRegistries.BLOCKS.getValue(this.cooled).defaultBlockState());
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        level.getBlockEntity(pos, TFCColdSweatBlockEntities.TICK_COUNTER.get()).ifPresent(TFCColdSweatTickCounterBlockEntity::resetCounter);
        super.setPlacedBy(level, pos, state, placer, stack);
    }

}