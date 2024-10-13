package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.datagen.recipes.BaseRecipeBuilder;
import com.pasey.peculiardevices.registration.PDItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class PDRecipeProvider extends RecipeProvider {
    private static final List<ItemLike> LITHIUM_SMELTABLES = List.of(
            PDItems.RAW_LITHIUM.get()
    );

    public PDRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        PDOreSmelting(consumer, LITHIUM_SMELTABLES, RecipeCategory.MISC, PDItems.LITHIUM_INGOT.get(), 0.25f, 200, "lithium_ingot");
        PDOreBlasting(consumer, LITHIUM_SMELTABLES, RecipeCategory.MISC, PDItems.LITHIUM_INGOT.get(), 0.25f, 100, "lithium_ingot");

        PDMilling(consumer, List.of(PDItems.RAW_LITHIUM.get()), List.of(new ItemStack(PDItems.LITHIUM_ORE_ITEM.get(), 2)), 0.25f, 200);
    }


    protected static void PDMilling(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
                                    List<ItemStack> pOutputs, float pExperience,
                                    int pCraftingTime) {
        List<Ingredient> ingredientList = itemLikeListAsIngredientList(pIngredients);
        String ingredientNames = itemLikeListAsString(pIngredients);
        String outputNames = itemListAsString(pOutputs);

        pFinishedRecipeConsumer.accept(
                BaseRecipeBuilder
                        .milling(RecipeCategory.MISC, ingredientList, pOutputs, pExperience, pCraftingTime,
                            new ResourceLocation(
                                PeculiarDevices.MODID,outputNames + "_from_milling_" + ingredientNames))
                        .build()
        );
    }

    protected static void PDOreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
                                        RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme,
                                        String pGroup) {
        PDOreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void PDOreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
                                        RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime,
                                        String pGroup) {
        PDOreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void PDOreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer,
                                       RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer,
                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                       float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, PeculiarDevices.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static String itemListAsString(List<ItemStack> items) {
        return items.stream()
                .map(ItemStack::getItem)
                .map(ForgeRegistries.ITEMS::getKey)
                .filter(Objects::nonNull)
                .map(ResourceLocation::getPath)
                .reduce((String a, String b) -> "_and_" + b).orElseThrow();
    }

    protected static String itemLikeListAsString(List<ItemLike> items) {
        return items.stream()
                .map(RecipeProvider::getItemName)
                .reduce((String a, String b) -> a + "_and_" + b).orElseThrow();
    }

    protected static List<Ingredient> itemLikeListAsIngredientList(List<ItemLike> items) {
        List<Ingredient> result = new java.util.ArrayList<>(Collections.emptyList());
        for (ItemLike itemLike : items) {
            result.add(Ingredient.of(itemLike));
        }
        return result;
    }
}
