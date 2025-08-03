package org.hyperoil.blockinfection.Recipes.Recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.level.Level;
import org.hyperoil.blockinfection.Recipes.RecipeInputs.BigBoyCraftingInput;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BigBoyCraftingRecipe implements Recipe<BigBoyCraftingInput> {
    private static final Logger log = LoggerFactory.getLogger(BigBoyCraftingRecipe.class);
    private final List<Ingredient> inputIngredients;
    private final ItemStack result;
    public BigBoyCraftingRecipe(List<Ingredient> ingredients, ItemStack resultItemStack) {
        inputIngredients = ingredients;
        result = resultItemStack;
    }

    @Override
    public boolean matches(BigBoyCraftingInput input, @NotNull Level level) {
        List<ItemStack> inputtedItems = input.input();

        if (inputtedItems.size() != inputIngredients.size()) {
            log.warn("inputted ingredients and items amount don't match.");
            return false;
        }

        for (int i = 0; i < inputtedItems.size(); i++) {
            if (!inputIngredients.get(i).test(inputtedItems.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull BigBoyCraftingInput input, HolderLookup.@NotNull Provider provider) {
        return result.copy();
    }

    @Override
    public @NotNull RecipeSerializer<? extends Recipe<BigBoyCraftingInput>> getSerializer() {

    }

    @Override
    public @NotNull RecipeType<? extends Recipe<BigBoyCraftingInput>> getType() {
        return null;
    }

    @Override
    public @NotNull PlacementInfo placementInfo() {
        return null;
    }

    @Override
    public @NotNull RecipeBookCategory recipeBookCategory() {
        return null;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public List<RecipeDisplay> display() {
        return Recipe.super.display();
    }
}
