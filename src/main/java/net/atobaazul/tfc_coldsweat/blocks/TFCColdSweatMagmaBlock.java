package net.atobaazul.tfc_coldsweat.blocks;

import net.atobaazul.tfc_coldsweat.blockentities.TFCColdSweatTickCounterBlockEntity;
import net.atobaazul.tfc_coldsweat.registries.TFCColdSweatBlockEntities;
import net.dries007.tfc.common.blocks.EntityBlockExtension;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.dries007.tfc.common.blocks.TFCBubbleColumnBlock;
import net.dries007.tfc.common.capabilities.Capabilities;
import net.dries007.tfc.common.fluids.FluidHelpers;
import net.dries007.tfc.config.TFCConfig;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

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
    public InteractionResult use(BlockState pState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        final ItemStack held = player.getItemInHand(hand);
        final int lavaRequired = 100;
        final FluidStack lava = new FluidStack(Fluids.LAVA, lavaRequired);

        final IFluidHandler fluidHandler = Helpers.getCapability(held, Capabilities.FLUID_ITEM);

        if (fluidHandler != null)
        {
            final FluidStack simulatedDrained = fluidHandler.drain(lavaRequired, FluidAction.SIMULATE);

            if (simulatedDrained.containsFluid(lava))
            {
                fluidHandler.drain(lavaRequired, FluidAction.EXECUTE);
                FluidHelpers.playTransferSound(level, pos, lava, FluidHelpers.Transfer.DRAIN);
                level.getBlockEntity(pos, TFCColdSweatBlockEntities.TICK_COUNTER.get()).ifPresent(TFCColdSweatTickCounterBlockEntity::resetCounter);

                // Particles
                if (!level.isClientSide)
                {
                    for (int i = 0; i < 5; ++i)
                    {
                        ((ServerLevel) level).sendParticles(
                                ParticleTypes.LANDING_LAVA,
                                (double) pos.getX() + level.random.nextDouble(),
                                (double) pos.getY() + 1,
                                (double) pos.getZ() + level.random.nextDouble(),
                                1, 0.0, 0.0, 0.0, 1.0);
                    }
                }

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;

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

        for (Direction direction : Direction.values()) {

            BlockPos adjacentPos = pos.relative(direction);
            FluidState adjacentState = level.getFluidState(adjacentPos);

            if (adjacentState.is(Fluids.LAVA)) {
                // Reset counter if lava is found next to the block
                level.getBlockEntity(pos, TFCColdSweatBlockEntities.TICK_COUNTER.get())
                        .ifPresent(TFCColdSweatTickCounterBlockEntity::resetCounter);
                break; // Exit loop early since lava was found
            }
        }
    }

    @Override
    public ExtendedProperties getExtendedProperties() {
        return this.properties;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        onRandomTick(level, pos, ForgeRegistries.BLOCKS.getValue(this.cooled).defaultBlockState());
        for (Direction direction : Direction.values()) {

            BlockPos adjacentPos = pos.relative(direction);
            FluidState adjacentState = level.getFluidState(adjacentPos);

            if (adjacentState.is(Fluids.LAVA)) {
                // Reset counter if lava is found next to the block
                level.getBlockEntity(pos, TFCColdSweatBlockEntities.TICK_COUNTER.get())
                        .ifPresent(TFCColdSweatTickCounterBlockEntity::resetCounter);
                break; // Exit loop early since lava was found
            }
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        level.getBlockEntity(pos, TFCColdSweatBlockEntities.TICK_COUNTER.get()).ifPresent(TFCColdSweatTickCounterBlockEntity::resetCounter);
        super.setPlacedBy(level, pos, state, placer, stack);
    }

}