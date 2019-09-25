package net.marleyspoon.ui.listContent

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.list_content_fragment.*

import net.marleyspoon.R
import net.marleyspoon.data.model.Recipe
import net.marleyspoon.ui.base.BaseFragment
import net.marleyspoon.ui.detailsContent.DetailsContentActivity
import net.marleyspoon.ui.listContent.adapter.ListContentAdapter
import net.marleyspoon.utils.DialogUtils
import net.marleyspoon.utils.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListContentFragment : BaseFragment(), ListContentAdapter.OnRecipeClickListener {

    override fun onRecipeClicked(recipe: Recipe) {
        viewModel.saveCurrentRecipe(recipe)
        val intent = Intent(activity!!, DetailsContentActivity::class.java)
        startActivity(intent)
    }

    companion object {
        fun newInstance() = ListContentFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUI()

        viewModel.setCurrentActivity(activity)

        observe(viewModel.getExceptionObserver()) {
            it?.let {
                if (it.message != null) {
                    Snackbar.make(
                        activity!!.findViewById(android.R.id.content),
                        it.message!!, Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

        observe(viewModel.getDataLoadingObserver()) {
            it?.let {
                if (it)
                    DialogUtils.showLoadingDialog(activity!!)
                else {
                    DialogUtils.dismissProgressDialog()
                }
            }
        }

        observe(viewModel.getRecipesLiveData()) {
            it?.let {
                mAdapter = ListContentAdapter(
                    context!!, it,
                    this@ListContentFragment
                )
                recyclerContents.adapter = mAdapter
            }
        }

        viewModel.start()
    }

    private fun initUI() {
        recyclerContents.layoutManager = GridLayoutManager(context, 2)
    }

    val viewModel: ListContentViewModel by viewModel()
    lateinit var mAdapter: ListContentAdapter
}