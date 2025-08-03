package org.hyperoil.blockinfection.Utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.hyperoil.blockinfection.Recipes.RecipeInputs.BigBoyCraftingInput;
import org.hyperoil.blockinfection.Recipes.Recipes.BigBoyCraftingRecipe;
import org.hyperoil.blockinfection.hyperoil;

import java.util.function.Supplier;

public class RecipeHelper {
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPE_REGISTER = DeferredRegister.create(Registries.RECIPE_TYPE, hyperoil.MODID);
    public static final Supplier<RecipeType<BigBoyCraftingRecipe>> BIG_BOY_CRAFTING = RECIPE_TYPE_REGISTER.register("big_boy_crafting",
            RecipeType::simple);
}
