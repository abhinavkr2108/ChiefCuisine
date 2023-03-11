package com.example.chiefcuisine.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chiefcuisine.R
import com.example.chiefcuisine.adapters.CategoriesItemsAdapter
import com.example.chiefcuisine.databinding.FragmentCategoryFoodItemsBinding
import com.example.chiefcuisine.ui.activities.FoodActivity
import com.example.chiefcuisine.ui.activities.FoodDetailsActivity
import com.example.chiefcuisine.viewmodels.DetailsViewModel
import com.example.chiefcuisine.viewmodels.HomeViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class CategoryFoodItemsFragment : Fragment() {
    private lateinit var categoryItemsBinding: FragmentCategoryFoodItemsBinding
    private lateinit var categoryAdapter: CategoriesItemsAdapter
    private lateinit var homeViewModel: HomeViewModel
    private val args: CategoryFoodItemsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val categoryItemsView = inflater.inflate(R.layout.fragment_category_food_items, container, false)
        categoryItemsBinding = FragmentCategoryFoodItemsBinding.bind(categoryItemsView)
       // detailsViewModel = (activity as FoodDetailsActivity).detailsViewModel
        homeViewModel = (activity as FoodActivity).homeViewModel
        categoryAdapter = CategoriesItemsAdapter()

        setUpViews()
        setUpCategoriesRecyclerView()
        observeCategoriesItems()
        onCategoryItemClicked()
        return categoryItemsView
    }

    private fun onCategoryItemClicked() {
        categoryAdapter.onCategoryItemClicked = {
            val intent = Intent(activity, FoodDetailsActivity::class.java)
            intent.putExtra("FOOD_ID", it.id)
            intent.putExtra("FOOD_NAME", it.title)
            intent.putExtra("FOOD_INSTRUCTIONS", it.instructions)
            intent.putExtra("FOOD_IMAGE", it.image)
            startActivity(intent)
        }
    }

    private fun observeCategoriesItems() {
        MainScope().launch {
            homeViewModel.getFoodByCategory("50", args.category.categoryName.lowercase())
            homeViewModel.categoryItemsLiveData.observe(viewLifecycleOwner, Observer{
                categoryAdapter.categoryItemDiffer.submitList(it.recipes)
            })
        }
    }

    private fun setUpCategoriesRecyclerView() {
        categoryItemsBinding.rcvCategoriesFoodItems.apply{
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setUpViews() {
        categoryItemsBinding.categoryName.text = args.category.categoryName
    }

}