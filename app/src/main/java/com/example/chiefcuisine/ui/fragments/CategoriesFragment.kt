package com.example.chiefcuisine.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chiefcuisine.R
import com.example.chiefcuisine.adapters.CategoriesAdapter
import com.example.chiefcuisine.databinding.FragmentCategoriesBinding
import com.example.chiefcuisine.databinding.SimilarItemsLayoutBinding
import com.example.chiefcuisine.utils.CategoryItems


class CategoriesFragment : Fragment() {
    lateinit var categoryBinding: FragmentCategoriesBinding
    lateinit var categoriesAdapter: CategoriesAdapter
    lateinit var categoryList: MutableList<CategoryItems>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val categoryView = inflater.inflate(R.layout.fragment_categories, container, false)
        categoryBinding = FragmentCategoriesBinding.bind(categoryView)
        categoriesAdapter = CategoriesAdapter()

        categoryList = mutableListOf<CategoryItems>()
        categoryList.add(CategoryItems(
            1, R.drawable.main_course, "Main course"
        ))

        categoryList.add(CategoryItems(
            2, R.drawable.bread, "Bread"
        ))

        categoryList.add(CategoryItems(
            3, R.drawable.dessert, "Dessert"
        ))

        categoryList.add(CategoryItems(
            4, R.drawable.salad, "Salad"
        ))

        categoryList.add(CategoryItems(
            5, R.drawable.drink, "Drinks"
        ))

        categoryList.add(CategoryItems(
            6, R.drawable.vegetarian, "Vegetarian"
        ))


        setUpCategoriesRecyclerView()
        observeCategories()
        onCategoryItemClicked()

        return categoryView
    }

    private fun onCategoryItemClicked() {

    }

    private fun observeCategories() {
        categoriesAdapter.categoryDiffer.submitList(categoryList)
    }

    private fun setUpCategoriesRecyclerView() {
        categoryBinding.rcvCategories.apply {
            adapter = categoriesAdapter
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        }
    }

}