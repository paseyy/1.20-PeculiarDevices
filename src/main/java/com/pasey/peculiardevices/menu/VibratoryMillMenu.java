package com.pasey.peculiardevices.menu;

import com.pasey.peculiardevices.blockentities.VibratoryMillBlockEntity;
import com.pasey.peculiardevices.menu.base.PDMachineMenu;
import com.pasey.peculiardevices.menu.util.OutputSlotItemHandler;
import com.pasey.peculiardevices.registration.PDMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;

public class VibratoryMillMenu extends PDMachineMenu<VibratoryMillBlockEntity> {

    // Client Constructor
    public VibratoryMillMenu(int containerId, Inventory playerInv, FriendlyByteBuf additionalData) {
        this(
                containerId,
                playerInv,
                Objects.requireNonNull(playerInv.player.level().getBlockEntity(additionalData.readBlockPos()))
        );
    }

    // Server Constructor
    public VibratoryMillMenu(int containerId, Inventory playerInv, BlockEntity blockEntity) {
        super(PDMenus.VIBRATORY_MILL_MENU.get(), containerId, (VibratoryMillBlockEntity) blockEntity);

        createPlayerHotbar(playerInv);
        createPlayerInventory(playerInv);

        createBlockEntityInventory((VibratoryMillBlockEntity) blockEntity);
    }

    public void createBlockEntityInventory(VibratoryMillBlockEntity be) {
        be.getOptional().ifPresent(inventory -> {
            addSlot(new SlotItemHandler(inventory, 0, 59, 35));
            addSlot(new OutputSlotItemHandler(inventory, 1, 109, 16));
            addSlot(new OutputSlotItemHandler(inventory, 2, 109, 35));
            addSlot(new OutputSlotItemHandler(inventory, 3, 109, 54));
        });
    }
}
