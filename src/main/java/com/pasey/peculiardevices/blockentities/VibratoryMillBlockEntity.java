package com.pasey.peculiardevices.blockentities;

import com.pasey.peculiardevices.blockentities.base.BaseMachineBlockEntity;
import com.pasey.peculiardevices.registration.PDBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class VibratoryMillBlockEntity extends BaseMachineBlockEntity {
    public static final int INVENTORY_SLOTS = 4;

    private int currentSlot = -1;

    public VibratoryMillBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(PDBlockEntities.VIBRATORY_MILL_BE.get(), pPos, pBlockState, INVENTORY_SLOTS);
    }


    public boolean isFull() {
        return currentSlot == INVENTORY_SLOTS - 1;
    }

    public ItemStack push(ItemStack toInsert) {
        if(isFull()) {
            return toInsert;
        }
        // we can insert!
        currentSlot++;
        return inventory.insertItem(currentSlot, toInsert, false);
    }

    public ItemStack pop() {
        if(currentSlot < 0) {
            return ItemStack.EMPTY;
        }

        var result = inventory.extractItem(currentSlot, inventory.getSlotLimit(currentSlot), false);
        currentSlot--;

        return result;
    }

    public ItemStack peek() {
        if(currentSlot < 0) {
            return ItemStack.EMPTY;
        }

        return inventory.getStackInSlot(currentSlot);
    }
}
