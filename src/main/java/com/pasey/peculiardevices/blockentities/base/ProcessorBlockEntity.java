package com.pasey.peculiardevices.blockentities.base;

import com.pasey.peculiardevices.recipe.base.BaseRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class ProcessorBlockEntity<T extends BaseRecipe<T>> extends MachineBlockEntity {
    public ProcessorBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int inventorySlots) {
        super(pType, pPos, pBlockState, inventorySlots);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, BlockEntity pBlockEntity) {

    }

    private boolean hasRecipe() {
        Optional<T> recipe = getCurrentRecipe();

        if(recipe.isEmpty())
            return false;

        NonNullList<ItemStack> result = recipe.get().getOutputs();
        return canInsertItemIntoOutput(result);
    }

    private boolean canInsertItemIntoOutput(NonNullList<ItemStack> result) {
        NonNullList<Integer> outputIndices = getOutputSlots();
        long emptyOutputSlots = outputIndices.stream().filter(i -> inventory.getStackInSlot(i).isEmpty()).count();

        if (result.size() <= emptyOutputSlots) {
            return true;
        }

        // We compare every resulting item with every filled output slot. If a result item
        // cannot be added to any output slot, we add 1 to the number of new (not yet present) items!
        // If we have more new items than free output slots, we cannot insert.
        int cannot_insert = 0;
        for (ItemStack resultItem : result)
        {
            for (ItemStack presentItem : outputIndices
                .stream()
                .map(inventory::getStackInSlot)
                .filter(Predicate.not(ItemStack::isEmpty))
                .toList())
            {
                if (resultItem.equals(presentItem)
                        && presentItem.getCount() + resultItem.getCount() < presentItem.getMaxStackSize())
                {
                    break;
                }
            }
            cannot_insert += 1;
        }

        return cannot_insert <= emptyOutputSlots;
    }

    private Optional<T> getCurrentRecipe() {
        SimpleContainer container = new SimpleContainer(this.inventory.getSlots());

        for (int i = 0; i < this.inventory.getSlots(); i++) {
            container.setItem(i, this.inventory.getStackInSlot(i));
        }

        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(getRecipeType(), container, level);
    }

    protected abstract RecipeType<T> getRecipeType();


    protected abstract NonNullList<Integer> getInputSlots();

    protected abstract NonNullList<Integer> getOutputSlots();
}
