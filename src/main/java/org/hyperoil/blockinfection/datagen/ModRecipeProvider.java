package org.hyperoil.blockinfection.datagen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.capabilities.Capabilities;
import org.hyperoil.blockinfection.Utils.ItemsHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        ShapedRecipeBuilder
                .shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC,
                        new ItemStack(ItemsHelper.INFECTION_RESISTANT_BLOCK.get(), 16))
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .define('#', Items.EMERALD)
                .define('X', Items.DIAMOND)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(this.output);
        ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC,
                new ItemStack(ItemsHelper.STRUCTURE_CATALYST.get(), 1))
                .requires(Items.NETHER_STAR)
                .requires(Items.DRAGON_EGG)
                .requires(Items.DRIED_GHAST)
                .requires(Items.SCULK_SENSOR)
                .requires(Items.SCULK_SHRIEKER)
                .requires(Items.CALIBRATED_SCULK_SENSOR)
                .requires(Items.NETHERITE_BLOCK, 3)
                .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
                .save(this.output);
    }

    public static class Runner extends RecipeProvider.Runner {
        protected Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider, @NotNull RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public @NotNull String getName() {
            return "BlockInfection recipe";
        }
    }
}
