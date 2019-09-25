package net.marleyspoon.ui.detailsContent

import androidx.lifecycle.Observer
import net.marleyspoon.data.RepositorySource
import net.marleyspoon.ui.base.AbstractViewModel

class DetailsContentViewModel(val mRepositorySource: RepositorySource) : AbstractViewModel() {

    fun start() {
        getDetailsContent()
    }

    fun getDetailsContent() {
        setLoading(true)
        mRepositorySource.getDetailsContent().observe(this.getCurrentActivity()!!,
            Observer {
                setLoading(false)
                if (it.exception != null) {
                    setError(it.exception)
                } else {
                    getRecipeDetailsLiveData().value = it.recipeDetails
                }
            })
    }
}
