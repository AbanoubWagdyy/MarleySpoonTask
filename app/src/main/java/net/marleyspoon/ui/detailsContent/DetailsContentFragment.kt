package net.marleyspoon.ui.detailsContent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.details_content_fragment.*

import net.marleyspoon.R
import net.marleyspoon.ui.base.BaseFragment
import net.marleyspoon.utils.DialogUtils
import net.marleyspoon.utils.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsContentFragment : BaseFragment() {

    companion object {
        fun newInstance() = DetailsContentFragment()
    }

    val viewModel: DetailsContentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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

        observe(viewModel.getRecipeDetailsLiveData()) {
            it?.let {
                tvTitle.text = "Title: ${it.title}"
                tvDescription.text = "Description: ${it.description}"
                tvChefName.text = "Chef: ${it.chefName}"
                tvTags.text = "Tags: ${it.tags}"
                Glide.with(context!!)
                    .load(it.image)
                    .into(ivRecipePhoto)
            }
        }

        viewModel.start()
    }
}
