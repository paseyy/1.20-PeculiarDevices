package com.pasey.peculiardevices.blocks.machines;

import com.pasey.peculiardevices.blocks.base.BaseMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

public class VibratoryMill extends BaseMachineBlock {
    public VibratoryMill() {
        super(Properties.of()
                .mapColor(MapColor.COLOR_ORANGE)
                .requiresCorrectToolForDrops()
                .strength(1.5f, 3.0f)
                .sound(SoundType.COPPER)
                .noOcclusion());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return null;
    }
}
