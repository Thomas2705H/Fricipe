package com.Fricipe_2.Model;

public class RecipeResult {
    private final int ingrCount;
    private final int recipeId;

    public RecipeResult(int recipeId, int ingrCount) {
        this.ingrCount = ingrCount;
        this.recipeId = recipeId;
    }

    public int get_ingrCount() {
        return ingrCount;
    }

    public int get_recipeId() {
        return recipeId;
    }
}
