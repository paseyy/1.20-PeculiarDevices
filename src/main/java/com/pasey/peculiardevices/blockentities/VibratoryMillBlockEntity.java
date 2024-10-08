package com.pasey.peculiardevices.blockentities;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.blockentities.base.BaseMachineBlockEntity;
import com.pasey.peculiardevices.menu.VibratoryMillMenu;
import com.pasey.peculiardevices.registration.PDBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VibratoryMillBlockEntity extends BaseMachineBlockEntity implements MenuProvider {
    private static final Component TITLE =
            Component.translatable("block." + PeculiarDevices.MODID + ".vibratory_mill");
    public static final int INVENTORY_SLOTS = 4;


    public VibratoryMillBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(PDBlockEntities.VIBRATORY_MILL_BE.get(), pPos, pBlockState, INVENTORY_SLOTS);
    }


    @Override
    @NotNull
    public Component getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new VibratoryMillMenu(pContainerId, pPlayerInventory, this);
    }
}
