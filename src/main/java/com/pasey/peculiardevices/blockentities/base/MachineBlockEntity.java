package com.pasey.peculiardevices.blockentities.base;

import com.pasey.peculiardevices.PeculiarDevices;
import net.minecraft.core.BlockPos;
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
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class MachineBlockEntity extends BlockEntity implements MenuProvider, BlockEntityTicker<BlockEntity> {
    public static int INVENTORY_SLOTS;
    protected Component TITLE;
    protected final ItemStackHandler inventory;
    protected final LazyOptional<ItemStackHandler> optional;

    public MachineBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int inventorySlots) {
        super(pType, pPos, pBlockState);

        INVENTORY_SLOTS = inventorySlots;
        inventory = new ItemStackHandler(inventorySlots) {
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                MachineBlockEntity.this.setChanged();
                // BaseMachineBlockEntity.this.level.sendBlockUpdated(BaseMachineBlockEntity.this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        };
        optional = LazyOptional.of(() -> this.inventory);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return this.optional.cast();
        }

        return super.getCapability(cap);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.optional.invalidate();
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        CompoundTag data = pTag.getCompound(PeculiarDevices.MODID);
        this.inventory.deserializeNBT(data.getCompound("Inventory"));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        var data = new CompoundTag();
        data.put("Inventory", this.inventory.serializeNBT());
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
        return optional;
    }

}
