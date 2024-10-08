package com.pasey.peculiardevices.recipe.base;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.recipe.MillingRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

public abstract class BaseRecipe<T extends BaseRecipe<T>> implements Recipe<SimpleContainer> {
    protected final NonNullList<Ingredient> inputItems;
    protected final NonNullList<ItemStack> outputItems;
    private final ResourceLocation id;

    public BaseRecipe(NonNullList<Ingredient> input, NonNullList<ItemStack> output, ResourceLocation id) {
        this.inputItems = input;
        this.outputItems = output;
        this.id = id;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide())
            return false;

        return inputItems.get(0).test(pContainer.getItem(0));
    }

    @Override
    @NotNull
    @ParametersAreNonnullByDefault
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    @NotNull
    @ParametersAreNonnullByDefault
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    @NotNull
    public ResourceLocation getId() {
        return id;
    }

    @Override
    @NotNull
    public abstract RecipeSerializer<?> getSerializer();

    @Override
    @NotNull
    public abstract RecipeType<T> getType();


    public NonNullList<ItemStack> getOutputs() {
        return outputItems;
    }

    public static class Serializer<T extends BaseRecipe<T>> implements RecipeSerializer<T> {
        private final RecipeFactory<T> recipeFactory;

        public Serializer(RecipeFactory<T> recipeFactory) {
            this.recipeFactory = recipeFactory;
        }

        @Override
        @NotNull
        @ParametersAreNonnullByDefault
        public T fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            JsonArray result = GsonHelper.getAsJsonArray(pSerializedRecipe, "outputs");
            NonNullList<ItemStack> outputs = NonNullList.create();

            for (var item : result) {
                outputs.add(Ingredient.fromJson(item).getItems()[0]);
            }

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.create();

            for (var item : ingredients) {
                inputs.add(Ingredient.fromJson(item));
            }

            return recipeFactory.create(inputs, outputs, pRecipeId);
        }

        @Override
        public @Nullable T fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            NonNullList<ItemStack> outputs = NonNullList.withSize(pBuffer.readInt(), ItemStack.EMPTY);
            for (int i = 0; i < outputs.size(); i++) {
                outputs.set(i, pBuffer.readItem());
            }

            return recipeFactory.create(inputs, outputs, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, T pRecipe) {
            pBuffer.writeInt(pRecipe.inputItems.size());
            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeInt(pRecipe.outputItems.size());
            for(ItemStack itemStack : pRecipe.getOutputs()) {
                pBuffer.writeItem(itemStack);
            }
        }

        public interface RecipeFactory<T extends BaseRecipe<?>> {
            T create(NonNullList<Ingredient> input, NonNullList<ItemStack> output, ResourceLocation id);
        }
    }


}
