package com.pasey.peculiardevices.registration;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.blockentities.VibratoryMillBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PDBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PeculiarDevices.MODID);

    public static final RegistryObject<BlockEntityType<VibratoryMillBlockEntity>> VIBRATORY_MILL_BE =
            BLOCK_ENTITIES.register("vibratory_mill_be",
                    () -> BlockEntityType.Builder.of(VibratoryMillBlockEntity::new,
                                    PDBlocks.VIBRATORY_MILL.get()).build(null));
}
