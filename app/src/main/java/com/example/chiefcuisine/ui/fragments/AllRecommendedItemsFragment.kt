package com.example.chiefcuisine.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chiefcuisine.R
import com.example.chiefcuisine.adapters.AllRecommendedItemsAdapter
import com.example.chiefcuisine.adapters.RecommendedAdapter
import com.example.chiefcuisine.databinding.FragmentAllFoodItemsBinding
import com.example.chiefcuisine.databinding.FragmentAllRecommendedItemsBinding
import com.example.chiefcuisine.ui.activities.FoodActivity
import com.example.chiefcuisine.ui.activities.FoodDetailsActivity
import com.example.chiefcuisine.viewmodels.AllItemsViewModel
import com.example.chiefcuisine.viewmodels.HomeViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class AllRecommendedItemsFragment : Fragment() {
    lateinit var allRecommendedItemsBinding: FragmentAllRecommendedItemsBinding
    lateinit var allRecommendedAdapter: AllRecommendedItemsAdapter
    lateinit var allItemsViewModel: AllItemsViewModel
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val allRecommendedItemsView = inflater.inflate(R.layout.fragment_all_recommended_items, container, false)
        allRecommendedItemsBinding = FragmentAllRecommendedItemsBinding.bind(allRecommendedItemsView)
        allRecommendedAdapter = AllRecommendedItemsAdapter()
        allItemsViewModel = (activity as FoodActivity).allItemsViewModel
        homeViewModel = (activity as FoodActivity).homeViewModel

        setUpAllRecommendedItemsRecyclerView()
        observeAllRecommendedItems()
        allRecommendedItemsClickListener()


        return allRecommendedItemsView
    }

    private fun allRecommendedItemsClickListener() {
        allRecommendedAdapter.onItemClick = { recommendedItems->
            val intent = Intent(activity, FoodDetailsActivity::class.java)
            intent.putExtra("FOOD_ID", recommendedItems.id)
            intent.putExtra("FOOD_NAME", recommendedItems.title)
            intent.putExtra("FOOD_IMAGE", recommendedItems.image)
            val instructions = MainScope().launch {
                homeViewModel.getInformationById(recommendedItems.id)
            }
            homeViewModel.infoResponseLiveData.observe(viewLifecycleOwner, Observer{
                val instructionsResponse = it.instructions
                intent.putExtra("FOOD_INSTRUCTIONS", instructionsResponse)
            })
            //
            startActivity(intent)
        }
    }

    private fun observeAllRecommendedItems() {
        allItemsViewModel.allRecommendeItemsLiveData.observe(viewLifecycleOwner, Observer{
            allRecommendedAdapter.allRecommendedItemsDiffer.submitList(it)
            allRecommendedItemsBinding.toolbarHeading.text = "${allRecommendedAdapter.allRecommendedItemsDiffer.currentList.size} Items"
        })
    }

    private fun setUpAllRecommendedItemsRecyclerView() {
        allRecommendedItemsBinding.rcvAllFoodItems.apply {
            adapter = allRecommendedAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }

}