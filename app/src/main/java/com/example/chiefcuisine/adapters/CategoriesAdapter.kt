package com.example.chiefcuisine.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chiefcuisine.R
import com.example.chiefcuisine.ui.fragments.CategoriesFragment
import com.example.chiefcuisine.ui.fragments.CategoriesFragmentDirections
import com.example.chiefcuisine.utils.CategoryItems
import kotlinx.android.synthetic.main.category_item_layout.view.*

class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
//    lateinit var onItemClicked: ((CategoryItems)-> Unit)

    private val categoriesCallback = object : DiffUtil.ItemCallback<CategoryItems>(){
        override fun areItemsTheSame(oldItem: CategoryItems, newItem: CategoryItems): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryItems, newItem: CategoryItems): Boolean {
            return oldItem == newItem
        }
    }

    val categoryDiffer = AsyncListDiffer(this, categoriesCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.category_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.itemView.apply {
            val categoryItem = categoryDiffer.currentList[position]
            categoryImage.setImageResource(categoryItem.image)
            categoryName.text = categoryItem.categoryName
            setOnClickListener {
//                onItemClicked.invoke(categoryItem)
                val direction = CategoriesFragmentDirections.actionCategoriesFragmentToCategoryFoodItemsFragment(categoryItem)
                this.findNavController().navigate(direction)
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryDiffer.currentList.size
    }

    inner class CategoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}