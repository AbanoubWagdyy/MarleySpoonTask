package net.marleyspoon.ui.listContent

import androidx.lifecycle.Observer
import net.marleyspoon.data.RepositorySource
import net.marleyspoon.data.model.Recipe
import net.marleyspoon.ui.base.AbstractViewModel

class ListContentViewModel(private val mRepositorySource: RepositorySource?) :
    AbstractViewModel() {

    fun start() {
        getListingItems()
    }

    fun getListingItems() {
        setLoading(true)
        mRepositorySource!!.getContent().observe(getCurrentActivity()!!, Observer {
            setLoading(false)
            if (it.exception != null) {
                setError(it.exception)
            } else {
                getRecipesLiveData().value = it.items
            }
        })
    }

    fun saveCurrentRecipe(recipe: Recipe) {
        mRepositorySource!!.saveRecipe(recipe)
    }
}