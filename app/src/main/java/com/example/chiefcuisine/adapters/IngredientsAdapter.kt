package com.example.chiefcuisine.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chiefcuisine.R
import com.example.chiefcuisine.apiresponse.Ingredient
import kotlinx.android.synthetic.main.ingredients_layout.view.*

class IngredientsAdapter: RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    private val ingredientsDifferCallback = object : DiffUtil.ItemCallback<Ingredient>(){
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Ingredient,
            newItem: Ingredient
        ): Boolean {
            return oldItem == newItem
        }
    }

    val ingredientDiffer = AsyncListDiffer(this, ingredientsDifferCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        return IngredientsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ingredients_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val ingredientPosition = ingredientDiffer.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(ingredientPosition.image).into(foodImage)
            ingredientName.text = ingredientPosition.name
        }
    }

    override fun getItemCount(): Int {
        return ingredientDiffer.currentList.size
    }

    inner class IngredientsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}