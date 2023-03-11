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
import com.example.chiefcuisine.adapters.AllItemsAdapter
import com.example.chiefcuisine.databinding.FragmentAllFoodItemsBinding
import com.example.chiefcuisine.ui.activities.FoodActivity
import com.example.chiefcuisine.ui.activities.FoodDetailsActivity
import com.example.chiefcuisine.viewmodels.AllItemsViewModel


class AllFoodItemsFragment : Fragment() {
    lateinit var allFoodItemsBinding: FragmentAllFoodItemsBinding
    lateinit var allItemsAdapter: AllItemsAdapter
    lateinit var allItemsViewModel: AllItemsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val allItemsView = inflater.inflate(R.layout.fragment_all_food_items, container, false)
        allFoodItemsBinding = FragmentAllFoodItemsBinding.bind(allItemsView)
        allItemsAdapter = AllItemsAdapter()
        allItemsViewModel = (activity as FoodActivity).allItemsViewModel

        setUpAllItemsRecyclerView()
        observeAllItems()
        allItemsClickListener()
        return allItemsView
    }

    private fun allItemsClickListener() {
        allItemsAdapter.onItemClick = {
            val intent = Intent(activity, FoodDetailsActivity::class.java)
            intent.putExtra("FOOD_ID", it.id)
            intent.putExtra("FOOD_NAME", it.title)
            intent.putExtra("FOOD_INSTRUCTIONS", it.instructions)
            intent.putExtra("FOOD_IMAGE", it.image)
            startActivity(intent)
        }
    }

    private fun observeAllItems() {
        allItemsViewModel.allItemsLiveData.observe(viewLifecycleOwner, Observer {
            allItemsAdapter.allItemsDiffer.submitList(it.recipes)
            allFoodItemsBinding.toolbarHeading.text = "${allItemsAdapter.allItemsDiffer.currentList.size} Items"
        })
    }

    private fun setUpAllItemsRecyclerView() {
        allFoodItemsBinding.rcvAllFoodItems.apply {
            adapter = allItemsAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }


}