package net.atobaazul.tfc_coldsweat.blocks;

import net.atobaazul.tfc_coldsweat.registries.TFCColdSweatBlockEntities;
import net.atobaazul.tfc_coldsweat.blockentities.TFCColdSweatTickCounterBlockEntity;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.*;
import net.dries007.tfc.config.TFCConfig;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

public class TFCColdSweatMagmaBlock extends MagmaBlock implements IForgeBlockExtension, EntityBlockExtension {
    private final Block cooled;
    private final ExtendedProperties properties;

    public TFCColdSweatMagmaBlock(Properties properties, Block cooled, ExtendedProperties properties1) {
        super(properties);
        this.cooled = cooled;
        this.properties = properties1;
    }

    public static void onRandomTick(ServerLevel level, BlockPos pos, BlockState placeState)
    {

        System.out.println("Hey this works???????");
        level.getBlockEntity(pos, TFCColdSweatBlockEntities.TICK_COUNTER.get()).ifPresent(magma ->
        {
            final int torchTicks = TFCConfig.SERVER.torchTicks.get();
            if (magma.getTicksSinceUpdate() > torchTicks && torchTicks > 0)
            {
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
        System.out.println("Hey this works???????");
        TFCBubbleColumnBlock.updateColumnForFluid(level, pos);
    }

    @Override
    public ExtendedProperties getExtendedProperties() {
        return this.properties;
    }

    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
    {
        if (!level.isClientSide())
        {
            ItemStack held = player.getItemInHand(hand);
            if (Helpers.isItem(held.getItem(), TFCTags.Items.CAN_BE_LIT_ON_TORCH))
            {
                held.shrink(1);
                ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(TFCBlocks.TORCH.get()));
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        onRandomTick(level, pos, this.cooled.defaultBlockState());
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
    {
        level.getBlockEntity(pos, TFCColdSweatBlockEntities.TICK_COUNTER.get()).ifPresent(TFCColdSweatTickCounterBlockEntity::resetCounter);
        super.setPlacedBy(level, pos, state, placer, stack);
    }

}