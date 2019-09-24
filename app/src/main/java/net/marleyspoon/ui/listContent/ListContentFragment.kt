package net.marleyspoon.ui.listContent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import net.marleyspoon.R
import net.marleyspoon.ui.base.ViewLifecycleFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListContentFragment : ViewLifecycleFragment() {

    companion object {
        fun newInstance() = ListContentFragment()
    }

    val viewModel: ListContentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}