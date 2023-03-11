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
import kotlinx.android.synthetic.main.top_picks_item_layout.view.*

class TopPicksAdapter(): RecyclerView.Adapter<TopPicksAdapter.TopPicksViewHolder>() {

    /**
     * Creating DiffUtil Callback Anonymous Class
     * Will be passed as a parameter in differ
     */
    lateinit var onItemClick: ((Recipe)-> Unit)
    private val topPicksDifferCallback = object : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

    val topPicksDiffer = AsyncListDiffer(this, topPicksDifferCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPicksViewHolder {
        return TopPicksViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.top_picks_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopPicksViewHolder, position: Int) {
        val itemTopPicks = topPicksDiffer.currentList[position]

        holder.itemView.apply {
            // Setting Up The image
            Glide.with(this).load(itemTopPicks.image).into(foodImage)
            // Setting the food category
            foodCategory.text = itemTopPicks.sourceName
            // Setting name of food
            foodName.text = itemTopPicks.title
            //Setting Health Score
            foodHealth.text = "Health Score: ${itemTopPicks.healthScore.toString()}"
            foodLikes.text = itemTopPicks.aggregateLikes.toString()
            foodReadyTime.text = "${itemTopPicks.readyInMinutes} min"

            setOnClickListener {
                onItemClick.invoke(itemTopPicks)
            }

        }
    }

    override fun getItemCount(): Int {
        return topPicksDiffer.currentList.size
    }

    inner class TopPicksViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}