package com.pasey.peculiardevices.menu.base;

import com.pasey.peculiardevices.blockentities.base.ProcessorBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

public abstract class ProcessorMenu<T extends ProcessorBlockEntity<?>> extends MachineMenu<T> {
    protected final ContainerData data;

    protected ProcessorMenu(@Nullable MenuType<?> pMenuType, Inventory playerInv, int pContainerId, T pBlockEntity, ContainerData data) {
        super(pMenuType, pContainerId, pBlockEntity);
        this.data = data;

        checkContainerSize(playerInv, INVENTORY_SLOTS);
        addDataSlots(data);
    }

    @Override
    public T getBlockEntity() {
        return blockEntity;
    }

    public int getProgress() {
        return data.get(0);
    }

    public int getMaxProgress() {
        return data.get(1);
    }

    public boolean isCrafting() {
        return getProgress() > 0;
    }

    public float getScaledProgress() {
        return getMaxProgress() == 0 ? 0.0f : ((float) getProgress()) / getMaxProgress();
    }
}
