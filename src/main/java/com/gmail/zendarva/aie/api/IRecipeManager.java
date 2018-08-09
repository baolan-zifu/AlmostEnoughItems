package com.gmail.zendarva.aie.api;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by James on 8/5/2018.
 */
public interface IRecipeManager {

    public void addRecipe(String id, IRecipe recipe);
    public void addRecipe(String id, List<? extends IRecipe> recipes);
    public void addDisplayAdapter(IDisplayCategory adapter);

    public List<IRecipe> getRecipesFor(ItemStack stack);
}