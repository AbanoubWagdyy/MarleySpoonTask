package net.marleyspoon.ui.base

import android.os.Bundle
import android.view.View

open class BaseFragment : ViewLifecycleFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}