package net.marleyspoon.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.annotation.CallSuper

/**
 */
abstract class AbstractViewModel : ViewModel() {

    val isDataLoading = MutableLiveData<Boolean>()

    val exception = MutableLiveData<Throwable>()

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

}