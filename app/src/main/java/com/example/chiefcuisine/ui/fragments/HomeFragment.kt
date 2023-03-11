package com.example.chiefcuisine.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chiefcuisine.R
import com.example.chiefcuisine.adapters.RecommendedAdapter
import com.example.chiefcuisine.adapters.TopPicksAdapter
import com.example.chiefcuisine.databinding.FragmentHomeBinding
import com.example.chiefcuisine.ui.activities.FoodActivity
import com.example.chiefcuisine.ui.activities.FoodDetailsActivity
import com.example.chiefcuisine.viewmodels.HomeViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var topPicksAdapter: TopPicksAdapter
    private lateinit var recommendedAdapter: RecommendedAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        topPicksAdapter = TopPicksAdapter()
        recommendedAdapter = RecommendedAdapter()
        homeBinding = FragmentHomeBinding.bind(view)
        homeViewModel = (activity as FoodActivity).homeViewModel

        setUpRecyclerView()
        observeTopPicks()
        topPicksItemClicked()

        homeBinding.topPicksSeeMore.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToAllFoodItemsFragment()
            it.findNavController().navigate(direction)
        }

        setUpRecommendedRecyclerView()
        observeRecommendedMeals()

        homeBinding.recommendedSeeMore.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToAllRecommendedItemsFragment()
            it.findNavController().navigate(direction)
        }
        recommendedFoodItemClicked()


        return view
    }

    private fun recommendedFoodItemClicked() {
        recommendedAdapter.onItemClick={ recommendedItems->
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

    private fun topPicksItemClicked() {
        topPicksAdapter.onItemClick ={
            val intent = Intent(activity, FoodDetailsActivity::class.java)
            intent.putExtra("FOOD_ID", it.id)
            intent.putExtra("FOOD_NAME", it.title)
            intent.putExtra("FOOD_INSTRUCTIONS", it.instructions)
            intent.putExtra("FOOD_IMAGE", it.image)
            startActivity(intent)
        }
    }

    private fun observeRecommendedMeals() {
        homeViewModel.recommendedMealLiveData.observe(viewLifecycleOwner, Observer {
            recommendedAdapter.recommendedItemDiffer.submitList(it)
        })

    }

    private fun setUpRecommendedRecyclerView() {
        homeBinding.rcvRecommended.apply {
            adapter = recommendedAdapter
            layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.HORIZONTAL, false)
        }
    }


    private fun setUpRecyclerView() {
        homeBinding.rcvTopPicks.apply {
            adapter = topPicksAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

    }

    private fun observeTopPicks() {
        homeViewModel.randomMealLiveData.observe(viewLifecycleOwner, Observer {
            topPicksAdapter.topPicksDiffer.submitList(it.recipes)
        })
    }

}