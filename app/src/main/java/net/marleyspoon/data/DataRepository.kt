package net.marleyspoon.data

import android.annotation.SuppressLint
import com.contentful.java.cda.CDAClient
import com.contentful.java.cda.CDAEntry
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.contentful.java.cda.CDAArray
import com.contentful.java.cda.CDAAsset
import net.marleyspoon.data.model.Recipe
import net.marleyspoon.data.model.RecipeDetails
import net.marleyspoon.data.model.RecipeDetailsResponse
import net.marleyspoon.data.model.RecipesResponse

class DataRepository constructor(val client: CDAClient) : RepositorySource {

    @SuppressLint("CheckResult")
    override fun getDetailsContent(): MutableLiveData<RecipeDetailsResponse> {

        client.observe(CDAEntry::class.java)
            .one(currentRecipe.id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                val recipeDetails = RecipeDetails(it)
                recipeDetails.image = currentRecipe.imageURL
                recipeDetailsLiveData.value = RecipeDetailsResponse(null, recipeDetails)
            }, {
                recipeDetailsLiveData.value = RecipeDetailsResponse(it, null)
            })

        return recipeDetailsLiveData
    }

    override fun getRecipe(): Recipe {
        return this.currentRecipe
    }

    override fun saveRecipe(recipe: Recipe) {
        this.currentRecipe = recipe
    }

    private lateinit var currentRecipe: Recipe
    val liveData = MutableLiveData<RecipesResponse>()
    val recipeDetailsLiveData = MutableLiveData<RecipeDetailsResponse>()

    @SuppressLint("CheckResult")
    override fun getContent(): MutableLiveData<RecipesResponse> {
        val recipes = mutableListOf<Recipe>()
        client.observe(CDAEntry::class.java)
            .all()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                val entries = it.items().filter { it ->
                    (it as CDAEntry).rawFields().containsKey("title") &&
                            (it).rawFields().containsKey("photo")
                }
                for (entry1 in entries) {
                    val entry = entry1 as CDAEntry
                    recipes.add(
                        Recipe(
                            entry.id(),
                            "http:${(entry.getField("photo") as CDAAsset).url()}",
                            entry.getField("title")
                        )
                    )
                }

                val recipesResponse = RecipesResponse(null, recipes)
                liveData.value = recipesResponse

            }, {
                val recipesResponse = RecipesResponse(it, null)
                liveData.value = recipesResponse
            })

        return liveData
    }
}