package net.marleyspoon.ui.listContent.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recipe_list_item.view.*
import net.marleyspoon.R
import net.marleyspoon.data.model.Recipe

class ListContentAdapter(
    val mContext: Context,
    var recipes: List<Recipe>,
    val listener: OnRecipeClickListener
) :
    RecyclerView.Adapter<ListContentAdapter.ListContentHolder>() {

    private val inflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListContentHolder {
        return ListContentHolder(
            inflater.inflate(
                R.layout.recipe_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: ListContentHolder, position: Int) {
        val recipe = recipes[position]
        holder.bingRecipeData(recipe)
    }


    inner class ListContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bingRecipeData(recipe: Recipe) {
            itemView.tvRecipeTitle.text = recipe.title
            Glide.with(mContext)
                .asBitmap()
                .load(recipe.imageURL)
                .into(itemView.ivRecipe)

            itemView.setOnClickListener {
                listener.onRecipeClicked(recipe)
            }
        }
    }

    interface OnRecipeClickListener {
        fun onRecipeClicked(recipe: Recipe)
    }
}