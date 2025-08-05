package com.pasey.peculiardevices.blocks.devices;

import com.pasey.peculiardevices.blocks.base.BaseDeviceBlock;
import com.pasey.peculiardevices.registration.PDBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GeoGenerator extends BaseDeviceBlock {
    public GeoGenerator() {
        super(Properties.of()
                .mapColor(MapColor.METAL)
                .requiresCorrectToolForDrops()
                .strength(1.5f, 3.0f)
                .sound(SoundType.METAL)
                .noOcclusion());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return PDBlockEntities.GEO_GENERATOR_BE.get().create(pPos, pState);
    }
}

