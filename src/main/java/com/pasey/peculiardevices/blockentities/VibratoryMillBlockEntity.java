package com.pasey.peculiardevices.blockentities;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.blockentities.base.ProcessorBlockEntity;
import com.pasey.peculiardevices.menu.VibratoryMillMenu;
import com.pasey.peculiardevices.recipe.MillingRecipe;
import com.pasey.peculiardevices.registration.PDBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

public class VibratoryMillBlockEntity extends ProcessorBlockEntity<MillingRecipe> {
    public static final int INVENTORY_SLOTS = 4;


    public VibratoryMillBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(PDBlockEntities.VIBRATORY_MILL_BE.get(), pPos, pBlockState, INVENTORY_SLOTS);

        TITLE = Component.translatable("block." + PeculiarDevices.MODID + ".vibratory_mill");
    }

    @Override
    @Nullable
    @ParametersAreNonnullByDefault
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new VibratoryMillMenu(pContainerId, pPlayerInventory, this);
    }

    @Override
    protected RecipeType<MillingRecipe> getRecipeType() {
        return MillingRecipe.Type.INSTANCE;
    }

    @Override
    protected NonNullList<Integer> getInputSlots() {
        return NonNullList.of(0, 0);
    }

    @Override
    protected NonNullList<Integer> getOutputSlots() {
        return NonNullList.of(0,1, 2, 3);
    }
}
