package com.example.chiefcuisine.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chiefcuisine.R
import com.example.chiefcuisine.apiresponse.Recipe
import com.example.chiefcuisine.utils.CategoryItems
import kotlinx.android.synthetic.main.all_items_layout.view.*

class CategoriesItemsAdapter: RecyclerView.Adapter<CategoriesItemsAdapter.CategoriesItemsViewHolder>(){
    lateinit var onCategoryItemClicked: ((Recipe)-> Unit)

    private val categoryItemsCallback = object : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

    val categoryItemDiffer = AsyncListDiffer(this, categoryItemsCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesItemsViewHolder {
        return CategoriesItemsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.all_items_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoriesItemsViewHolder, position: Int) {
        val categoryItemPosition = categoryItemDiffer.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(categoryItemPosition.image).into(foodImage)
            foodCategory.text = categoryItemPosition.sourceName
            foodName.text = categoryItemPosition.title
            foodLikes.text = categoryItemPosition.aggregateLikes.toString()
            foodReadyTime.text = "${categoryItemPosition.readyInMinutes} min"

            setOnClickListener {
                onCategoryItemClicked.invoke(categoryItemPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryItemDiffer.currentList.size
    }

    inner class CategoriesItemsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}