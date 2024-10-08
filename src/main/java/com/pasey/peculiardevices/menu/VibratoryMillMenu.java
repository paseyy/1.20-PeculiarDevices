package com.pasey.peculiardevices.menu;

import com.pasey.peculiardevices.blockentities.VibratoryMillBlockEntity;
import com.pasey.peculiardevices.menu.util.OutputSlotItemHandler;
import com.pasey.peculiardevices.registration.PDBlocks;
import com.pasey.peculiardevices.registration.PDMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;

public class VibratoryMillMenu extends AbstractContainerMenu {
    public static final int PLAYER_INVENTORY_SLOTS = 36;
    public static final int PLAYER_INVENTORY_ROWS = 3;
    public static final int PLAYER_INVENTORY_COLS = 9;
    public static final int SLOT_DISTANCE_PIXELS = 18;
    public static final int INVENTORY_SLOTS = 4;

    private final VibratoryMillBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;

    // Client Constructor
    public VibratoryMillMenu(int containerId, Inventory playerInv, FriendlyByteBuf additionalData) {
        this(containerId, playerInv, playerInv.player.level().getBlockEntity(additionalData.readBlockPos()));
    }

    // Server Constructor
    public VibratoryMillMenu(int containerId, Inventory playerInv, BlockEntity blockEntity) {
        super(PDMenus.VIBRATORY_MILL_MENU.get(), containerId);

        if(blockEntity instanceof VibratoryMillBlockEntity be) {
            this.blockEntity = be;
        } else {
            throw new IllegalStateException(
                    "Incorrect block entity class (%s) passed into VibratoryMillMenu"
                            .formatted(blockEntity.getClass().getCanonicalName())
            );
        }

        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        createPlayerHotbar(playerInv);
        createPlayerInventory(playerInv);
        createBlockEntityInventory(be);
    }


    private void createPlayerHotbar(Inventory playerInv) {
        for (int i = 0; i < PLAYER_INVENTORY_COLS; i++) {
            addSlot(new Slot(playerInv, i, 8 + 18 * i, 142));
        }
    }

    private void createPlayerInventory(Inventory playerInv) {
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

    public void createBlockEntityInventory(VibratoryMillBlockEntity be) {
        be.getOptional().ifPresent(inventory -> {
            addSlot(new SlotItemHandler(inventory, 0, 59, 35));
            addSlot(new OutputSlotItemHandler(inventory, 1, 109, 16));
            addSlot(new OutputSlotItemHandler(inventory, 2, 109, 35));
            addSlot(new OutputSlotItemHandler(inventory, 3, 109, 54));
        });
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
        fromSlot.onTake(pPlayer, fromStack);

        return copyFromStack;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.levelAccess, pPlayer, PDBlocks.VIBRATORY_MILL.get());
    }

    public VibratoryMillBlockEntity getBlockEntity() {
        return blockEntity;
    }
}
