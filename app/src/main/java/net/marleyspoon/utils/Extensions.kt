package net.marleyspoon.utils

import androidx.lifecycle.MutableLiveData
import net.marleyspoon.ui.base.ViewLifecycleFragment

fun <T> ViewLifecycleFragment.observe(data: MutableLiveData<T>, function: (data: T?) -> Unit) {
    viewLifecycleOwner.let { it ->
        data.observe(it, androidx.lifecycle.Observer {
            function(it)
        })
    }
}