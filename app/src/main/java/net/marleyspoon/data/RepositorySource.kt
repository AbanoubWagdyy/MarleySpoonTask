package net.marleyspoon.data

import androidx.lifecycle.MutableLiveData
import net.marleyspoon.data.model.Recipe
import net.marleyspoon.data.model.RecipeDetailsResponse
import net.marleyspoon.data.model.RecipesResponse

interface RepositorySource {
    fun getContent() : MutableLiveData<RecipesResponse>
    fun getDetailsContent(): MutableLiveData<RecipeDetailsResponse>
    fun saveRecipe(recipe: Recipe)
    fun getRecipe(): Recipe
}