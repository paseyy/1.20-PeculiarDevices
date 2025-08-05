package com.pasey.peculiardevices.blockentities.base;

import com.pasey.peculiardevices.PeculiarDevices;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class DeviceBlockEntity extends BlockEntity implements MenuProvider, TickableBlockEntity {
    public static int INVENTORY_SLOTS;
    protected Component TITLE;
    public static BlockEntityType<? extends DeviceBlockEntity> TYPE;
    private final ItemStackHandler inventory;
    private final LazyOptional<IItemHandler>[] sidedHandlers;
    protected final LazyOptional<ItemStackHandler> allOptional = LazyOptional.of(this::getInventory);
    public abstract int[] getSlotsForFace(Direction side);
    public abstract boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction side);
    public abstract boolean canTakeItemThroughFace(int slot, Direction side);

    public DeviceBlockEntity(BlockEntityType<? extends DeviceBlockEntity> pType, BlockPos pPos, BlockState pBlockState, int inventorySlots) {
        super(pType, pPos, pBlockState);

        INVENTORY_SLOTS = inventorySlots;
        TYPE = pType;
        inventory = new ItemStackHandler(inventorySlots) {
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                DeviceBlockEntity.this.setChanged();
                // BaseMachineBlockEntity.this.level.sendBlockUpdated(BaseMachineBlockEntity.this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        };

        this.sidedHandlers = SidedItemHandler.createSidedHandlers(this);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER && side != null) {
            return sidedHandlers[side.ordinal()].cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<IItemHandler> handler : sidedHandlers) {
            handler.invalidate();
        }
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        CompoundTag data = pTag.getCompound(PeculiarDevices.MODID);
        getInventory().deserializeNBT(data.getCompound("Inventory"));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        var data = new CompoundTag();
        data.put("Inventory", getInventory().serializeNBT());
        pTag.put(PeculiarDevices.MODID, data);
    }

    @Override
    @NotNull
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public static <E extends BlockEntity> BlockEntityTicker<E> createTickerHelper(
            BlockEntityType<E> blockEntityType,
            BlockEntityType<? extends DeviceBlockEntity> expectedType) {

        // Verify the blockEntityType matches the expected type
        return blockEntityType == expectedType ? (level, pos, state, entity) -> {
            if (entity instanceof TickableBlockEntity tickableEntity) {
                tickableEntity.tick();
            }
        } : null;
    }

    @Override
    @NotNull
    public Component getDisplayName() {
        return TITLE;
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public ItemStack getStackInSlot(int slot) {
        return this.inventory.getStackInSlot(slot);
    }

    public void setStackInSlot(int slot, ItemStack stack) {
        this.inventory.setStackInSlot(slot, stack);
    }

    public LazyOptional<ItemStackHandler> getOptional() {
        return allOptional;
    }

}
