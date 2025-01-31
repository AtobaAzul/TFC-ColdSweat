package net.atobaazul.tfc_coldsweat.mixin;


import net.atobaazul.tfc_coldsweat.TFCColdSweat;
import net.atobaazul.tfc_coldsweat.blockentities.TFCColdSweatTickCounterBlockEntity;
import net.atobaazul.tfc_coldsweat.registries.TFCColdSweatBlockEntities;
import net.atobaazul.tfc_coldsweat.registries.TFCColdSweatBlocks;
import net.dries007.tfc.common.blocks.rock.MossGrowingBlock;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.capabilities.Capabilities;
import net.dries007.tfc.common.fluids.FluidHelpers;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MossGrowingBlock.class)
public class CobbleToMagmaMixin extends Block {
    public CobbleToMagmaMixin(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        final ItemStack held = player.getItemInHand(hand);
        final int lavaRequired = 100;
        final FluidStack lava = new FluidStack(Fluids.LAVA, lavaRequired);

        final IFluidHandler fluidHandler = Helpers.getCapability(held, Capabilities.FLUID_ITEM);

        if (fluidHandler != null)
        {
            final FluidStack simulatedDrained = fluidHandler.drain(lavaRequired, IFluidHandler.FluidAction.SIMULATE);

            if (simulatedDrained.containsFluid(lava))
            {
                fluidHandler.drain(lavaRequired, IFluidHandler.FluidAction.EXECUTE);
                FluidHelpers.playTransferSound(level, pos, lava, FluidHelpers.Transfer.DRAIN);
                //TODO: Check if the block at pos is of tfc:rock/cobble/<rock type> and replace it with a tfc_coldsweat:rock/magma/<rock_type>

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
}
