package com.pasey.peculiardevices.menu.base;

import com.pasey.peculiardevices.blockentities.base.MachineBlockEntity;
import com.pasey.peculiardevices.registration.PDBlocks;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

public abstract class PDMachineMenu<T extends MachineBlockEntity> extends AbstractContainerMenu {
    public static final int PLAYER_INVENTORY_SLOTS = 36;
    public static final int PLAYER_INVENTORY_ROWS = 3;
    public static final int PLAYER_INVENTORY_COLS = 9;
    public static final int SLOT_DISTANCE_PIXELS = 18;

    protected final int INVENTORY_SLOTS;
    protected final T blockEntity;
    protected final ContainerLevelAccess levelAccess;

    protected PDMachineMenu(@Nullable MenuType<?> pMenuType, int pContainerId, T pBlockEntity) {
        super(pMenuType, pContainerId);
        blockEntity = pBlockEntity;
        INVENTORY_SLOTS = T.INVENTORY_SLOTS;
        this.levelAccess =
                ContainerLevelAccess.create(Objects.requireNonNull(blockEntity.getLevel()), blockEntity.getBlockPos());
    }


    protected void createPlayerHotbar(Inventory playerInv) {
        for (int i = 0; i < PLAYER_INVENTORY_COLS; i++) {
            addSlot(new Slot(playerInv, i, 8 + 18 * i, 142));
        }
    }

    protected void createPlayerInventory(Inventory playerInv) {
        for (int row = 0; row < PLAYER_INVENTORY_ROWS; row++) {
            for (int col = 0; col < PLAYER_INVENTORY_COLS; col++) {
                addSlot(new Slot(
                        playerInv,
                        PLAYER_INVENTORY_COLS + col + (row * PLAYER_INVENTORY_COLS),
                        8 + col * SLOT_DISTANCE_PIXELS,
                        84 + (row * SLOT_DISTANCE_PIXELS)
                ));
            }
        }
    }


    @Override
    @NotNull
    @ParametersAreNonnullByDefault
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        Slot fromSlot = getSlot(pIndex);
        ItemStack fromStack = fromSlot.getItem();

        if(fromStack.getCount() <= 0) {
            fromSlot.set(ItemStack.EMPTY);
        }

        if(!fromSlot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack copyFromStack = fromStack.copy();

        if(pIndex < PLAYER_INVENTORY_SLOTS) {
            if(!moveItemStackTo(fromStack, PLAYER_INVENTORY_SLOTS,
                    PLAYER_INVENTORY_SLOTS + INVENTORY_SLOTS, false)) {
                return ItemStack.EMPTY;
            }
        } else if (pIndex < PLAYER_INVENTORY_SLOTS + INVENTORY_SLOTS) {
            if(!moveItemStackTo(fromStack, 0, PLAYER_INVENTORY_SLOTS, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.err.println("Invalid slot index: " + pIndex);
            return ItemStack.EMPTY;
        }

        fromSlot.setChanged();
        // fromSlot.onTake(pPlayer, fromStack);

        return copyFromStack;
    }


    @Override
    @ParametersAreNonnullByDefault
    public boolean stillValid(Player pPlayer) {
        return stillValid(levelAccess, pPlayer, PDBlocks.VIBRATORY_MILL.get());
    }


    public T getBlockEntity() {
        return blockEntity;
    }
}
