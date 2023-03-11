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
import kotlinx.android.synthetic.main.all_items_layout.view.*

class AllItemsAdapter: RecyclerView.Adapter<AllItemsAdapter.AllItemsViewHolder>() {
    lateinit var onItemClick: ((Recipe)-> Unit)
    private val allItemsCallback = object : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

    val allItemsDiffer = AsyncListDiffer(this, allItemsCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllItemsViewHolder {
        return AllItemsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.all_items_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AllItemsViewHolder, position: Int) {
        val allItemsPosition = allItemsDiffer.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(allItemsPosition.image).into(foodImage)
            foodCategory.text = allItemsPosition.sourceName
            foodName.text = allItemsPosition.title
            foodLikes.text = allItemsPosition.aggregateLikes.toString()
            foodReadyTime.text = "${allItemsPosition.readyInMinutes} min"
            setOnClickListener {
                onItemClick.invoke(allItemsPosition)
            }

        }
    }

    override fun getItemCount(): Int {
        return allItemsDiffer.currentList.size
    }

    inner class AllItemsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}