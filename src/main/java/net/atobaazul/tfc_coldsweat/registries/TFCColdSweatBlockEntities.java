/*
 * Licensed under the EUPL, Version 1.2.
 * You may obtain a copy of the Licence at:
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 */

package net.atobaazul.tfc_coldsweat.registries;

import net.atobaazul.tfc_coldsweat.blockentities.TFCColdSweatTickCounterBlockEntity;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static net.atobaazul.tfc_coldsweat.TFCColdSweat.MODID;

@SuppressWarnings("unused")
public final class TFCColdSweatBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);


    public static final RegistryObject<BlockEntityType<TFCColdSweatTickCounterBlockEntity>> TICK_COUNTER = register("tick_counter", TFCColdSweatTickCounterBlockEntity::new, Stream.of(
                    TFCColdSweatBlocks.MAGMA_BLOCKS.values()
            ).<Supplier<? extends Block>>flatMap(Helpers::flatten)
    );


    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Supplier<? extends Block> block) {
        return TFCColdSweatRegistrationHelpers.register(BLOCK_ENTITIES, name, factory, block);
    }

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Stream<? extends Supplier<? extends Block>> blocks) {
        return TFCColdSweatRegistrationHelpers.register(BLOCK_ENTITIES, name, factory, blocks);
    }

    private static Stream<? extends Supplier<? extends Block>> woodBlocks(Wood.BlockType type) {
        return TFCBlocks.WOODS.values().stream().map(map -> map.get(type));
    }
}