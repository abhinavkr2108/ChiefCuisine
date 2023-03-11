package com.example.chiefcuisine.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chiefcuisine.R
import com.example.chiefcuisine.apiresponse.SimilarItemsItem
import kotlinx.android.synthetic.main.similar_items_layout.view.foodImage
import kotlinx.android.synthetic.main.similar_items_layout.view.foodName
import kotlinx.android.synthetic.main.similar_items_layout.view.foodReadyTime


class SimilarItemsAdapter: RecyclerView.Adapter<SimilarItemsAdapter.SimilarItemsViewHolder>() {

    private val similarItemsCallBack = object :DiffUtil.ItemCallback<SimilarItemsItem>(){
        override fun areItemsTheSame(
            oldItem: SimilarItemsItem,
            newItem: SimilarItemsItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SimilarItemsItem,
            newItem: SimilarItemsItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val similarItemsDiffer = AsyncListDiffer(this, similarItemsCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarItemsViewHolder {
        return SimilarItemsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.similar_items_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SimilarItemsViewHolder, position: Int) {
        val similarItemsPosition = similarItemsDiffer.currentList[position]
        holder.itemView.apply{
            Glide.with(this).load("https://spoonacular.com/recipeImages/"+similarItemsPosition.id+"-556x370."+similarItemsPosition.imageType).into(foodImage)
            foodName.text = similarItemsPosition.title
            foodReadyTime.text = "${similarItemsPosition.readyInMinutes.toString()} min"
        }
    }

    override fun getItemCount(): Int {
        return similarItemsDiffer.currentList.size
    }

    inner class SimilarItemsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}