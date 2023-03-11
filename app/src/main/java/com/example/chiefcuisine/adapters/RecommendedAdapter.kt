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
import kotlinx.android.synthetic.main.recommended_item_layout.view.*
import kotlinx.android.synthetic.main.top_picks_item_layout.view.*
import kotlinx.android.synthetic.main.top_picks_item_layout.view.foodImage
import kotlinx.android.synthetic.main.top_picks_item_layout.view.foodName

class RecommendedAdapter(): RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder>() {
    lateinit var onItemClick: ((RecommendedFoodItems)-> Unit)

    private val recommendedItemCallBack = object : DiffUtil.ItemCallback<RecommendedFoodItems>(){
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
    val recommendedItemDiffer = AsyncListDiffer(this, recommendedItemCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedViewHolder {
        return RecommendedViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recommended_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecommendedViewHolder, position: Int) {
        val recommendedItems = recommendedItemDiffer.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(recommendedItems.image).into(foodImage)
            foodName.text = recommendedItems.title
            foodCalories.text = "Calories: ${recommendedItems.calories}"
            foodCarbs.text = "Carbs: ${recommendedItems.carbs}"
            foodProteins.text = "Proteins: ${recommendedItems.protein}"

            setOnClickListener {
                onItemClick.invoke(recommendedItems)
            }
        }

    }

    override fun getItemCount(): Int {
        return recommendedItemDiffer.currentList.size
    }

    inner class RecommendedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}