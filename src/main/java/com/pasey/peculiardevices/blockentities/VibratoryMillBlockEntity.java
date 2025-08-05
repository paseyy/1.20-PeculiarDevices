package com.pasey.peculiardevices.blockentities;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.blockentities.base.ProcessorBlockEntity;
import com.pasey.peculiardevices.menu.VibratoryMillMenu;
import com.pasey.peculiardevices.recipe.MillingRecipe;
import com.pasey.peculiardevices.registration.PDBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.pasey.peculiardevices.blockentities.base.SidedItemHandler.*;

public class VibratoryMillBlockEntity extends ProcessorBlockEntity<MillingRecipe> {
    public static final int INVENTORY_SLOTS = 4;

    public VibratoryMillBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(PDBlockEntities.VIBRATORY_MILL_BE.get(), pPos, pBlockState, INVENTORY_SLOTS, new int[] {0}, new int[] {1, 2, 3});

        TITLE = Component.translatable("block." + PeculiarDevices.MODID + ".vibratory_mill");
    }

    @Override
    @Nullable
    @ParametersAreNonnullByDefault
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new VibratoryMillMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        BlockState state = this.getBlockState();
        if (side == getRight(state) || side == Direction.UP) return inputSlots; // input
        if (side == getLeft(state) || side == Direction.DOWN) return outputSlots; // output
        return new int[] {0}; // default fallback
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction side) {
        BlockState state = this.getBlockState();
        return contains(inputSlots, slot) && (side == Direction.UP || side == getRight(state));
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, Direction side) {
        BlockState state = this.getBlockState();
        return contains(outputSlots, slot) && (side == Direction.DOWN || side == getLeft(state));
    }

    @Override
    protected RecipeType<MillingRecipe> getRecipeType() {
        return MillingRecipe.Type.INSTANCE;
    }
}
