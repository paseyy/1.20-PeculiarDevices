package com.pasey.peculiardevices.blockentities;

import com.pasey.peculiardevices.blockentities.base.PDGenerator;
import com.pasey.peculiardevices.registration.PDBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GeoGeneratorBlockEntity extends BlockEntity implements MenuProvider, PDGenerator {
    public GeoGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(PDBlockEntities.GEO_GENERATOR_BE.get(), pPos, pBlockState);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        return null;
    }
}
