package com.example.chiefcuisine.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.ui.setupWithNavController
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.chiefcuisine.R
import com.example.chiefcuisine.databinding.ActivityFoodBinding
import com.example.chiefcuisine.repository.MealRepository
import com.example.chiefcuisine.viewmodels.AllItemsViewModel
import com.example.chiefcuisine.viewmodels.AllItemsViewModelFactory
import com.example.chiefcuisine.viewmodels.HomeViewModel
import com.example.chiefcuisine.viewmodels.HomeViewModelFactory

class FoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodBinding
    private lateinit var repository: MealRepository
    private lateinit var homeViewModelFactory: HomeViewModelFactory
    private lateinit var allItemsViewModelFactory: AllItemsViewModelFactory
    lateinit var allItemsViewModel: AllItemsViewModel
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_food)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food)

        // Setting Up Bottom Navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavView.setupWithNavController(navController)

        // Creating instance of ViewModels
        repository = MealRepository()
        homeViewModelFactory = HomeViewModelFactory(application, repository)
        allItemsViewModelFactory = AllItemsViewModelFactory(application, repository)
        homeViewModel = ViewModelProvider(this,homeViewModelFactory).get(HomeViewModel::class.java)
        allItemsViewModel = ViewModelProvider(this, allItemsViewModelFactory).get(AllItemsViewModel::class.java)

    }
}