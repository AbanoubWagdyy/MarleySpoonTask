package net.marleyspoon.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.annotation.CallSuper
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import net.marleyspoon.data.model.Recipe
import net.marleyspoon.data.model.RecipeDetails

abstract class AbstractViewModel : ViewModel() {

    private val isDataLoading = MutableLiveData<Boolean>()
    private val recipesLiveData = MutableLiveData<List<Recipe>>()
    private val recipeDetailsLiveData = MutableLiveData<RecipeDetails>()
    private val exception = MutableLiveData<Throwable>()

    private var activity: FragmentActivity? = null

    @CallSuper
    override fun onCleared() {
        super.onCleared()
    }

    open fun setLoading(isLoading: Boolean? = true) {
        isDataLoading.value = isLoading

        if (isLoading == true) {
            exception.value = null
        }
    }

    open fun setError(t: Throwable) {
        exception.value = t
    }

    open fun getDataLoadingObserver(): MutableLiveData<Boolean> {
        return isDataLoading
    }

    open fun getExceptionObserver(): MutableLiveData<Throwable> {
        return exception
    }

    open fun getRecipesLiveData(): MutableLiveData<List<Recipe>> {
        return recipesLiveData
    }

    open fun getRecipeDetailsLiveData(): MutableLiveData<RecipeDetails> {
        return recipeDetailsLiveData
    }

    open fun setRecipes(recipeDetails: List<Recipe>?) {
        recipesLiveData.value = recipeDetails
    }

    open fun setRecipeDetails(recipeDetails: RecipeDetails?) {
        recipeDetailsLiveData.value = recipeDetails
    }

    open fun setCurrentActivity(activity: FragmentActivity?) {
        this.activity = activity
    }

    open fun getCurrentActivity(): LifecycleOwner? {
        return this.activity
    }
}