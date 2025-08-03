package org.hyperoil.blockinfection.Recipes.RecipeInputs;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.hyperoil.blockinfection.Utils.BlocksHelper;
import org.hyperoil.blockinfection.Utils.ItemsHelper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public record BigBoyCraftingInput(List<ItemStack> input) implements RecipeInput {
    private static final Logger log = LoggerFactory.getLogger(BigBoyCraftingInput.class);

    @Override
    public @NotNull ItemStack getItem(int i) {
        if (input.size() != 81) {
            log.warn("input.size() != 81? That isn't right... cannot craft");
            return ItemStack.EMPTY;
        }
        return input.get(i);
    }

    @Override
    public int size() {
        return 81;
    }
}
