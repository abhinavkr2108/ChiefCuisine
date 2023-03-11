package com.example.chiefcuisine.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chiefcuisine.R
import com.example.chiefcuisine.apiresponse.RecommendedFoodItems
import kotlinx.android.synthetic.main.all_recommended_items_layout.view.*

class AllRecommendedItemsAdapter: RecyclerView.Adapter<AllRecommendedItemsAdapter.AllRecommendedItemsViewHolder>() {
    lateinit var onItemClick: ((RecommendedFoodItems)-> Unit)

    private val allRecommendedItemsCallBack = object : DiffUtil.ItemCallback<RecommendedFoodItems>(){
        override fun areItemsTheSame(
            oldItem: RecommendedFoodItems,
            newItem: RecommendedFoodItems
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecommendedFoodItems,
            newItem: RecommendedFoodItems
        ): Boolean {
            return oldItem == newItem
        }
    }

    val allRecommendedItemsDiffer = AsyncListDiffer(this, allRecommendedItemsCallBack)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllRecommendedItemsViewHolder {
        return AllRecommendedItemsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.all_recommended_items_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AllRecommendedItemsViewHolder, position: Int) {
        val allRecommendedItemsPosition = allRecommendedItemsDiffer.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(allRecommendedItemsPosition.image).into(foodImage)
            foodName.text = allRecommendedItemsPosition.title
            foodCalories.text = "Calories: ${allRecommendedItemsPosition.calories}"
            foodCarbs.text = "Carbs: ${allRecommendedItemsPosition.carbs}"
            foodProteins.text = "Proteins: ${allRecommendedItemsPosition.protein}"

            setOnClickListener {
                onItemClick.invoke(allRecommendedItemsPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return allRecommendedItemsDiffer.currentList.size
    }

    inner class AllRecommendedItemsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}