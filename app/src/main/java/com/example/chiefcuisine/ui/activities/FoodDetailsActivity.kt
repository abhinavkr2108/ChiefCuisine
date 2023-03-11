package com.example.chiefcuisine.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.chiefcuisine.R
import com.example.chiefcuisine.adapters.IngredientsAdapter
import com.example.chiefcuisine.adapters.RecommendedAdapter
import com.example.chiefcuisine.adapters.SimilarItemsAdapter
import com.example.chiefcuisine.databinding.ActivityFoodDetailsBinding
import com.example.chiefcuisine.repository.MealRepository
import com.example.chiefcuisine.viewmodels.DetailsViewModel
import com.example.chiefcuisine.viewmodels.DetailsViewModelFactory
import com.example.chiefcuisine.viewmodels.HomeViewModel
import com.example.chiefcuisine.viewmodels.HomeViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class FoodDetailsActivity : AppCompatActivity() {
    private lateinit var detailsBinding: ActivityFoodDetailsBinding
    lateinit var detailsViewModel: DetailsViewModel
    private lateinit var detailsViewModelFactory: DetailsViewModelFactory
    private lateinit var repository: MealRepository

    private lateinit var ingredientsAdapter: IngredientsAdapter
    private lateinit var similarItemsAdapter: SimilarItemsAdapter

    lateinit var name: String
    lateinit var instructions: String
    lateinit var image: String
    lateinit var job : Job
    var id by Delegates.notNull<Int>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding
        detailsBinding = DataBindingUtil.setContentView(this,R.layout.activity_food_details)
        // ViewModels
        repository = MealRepository()
        detailsViewModelFactory = DetailsViewModelFactory(application, repository)
        detailsViewModel = ViewModelProvider(this, detailsViewModelFactory).get(DetailsViewModel::class.java)
        // Adapters
        ingredientsAdapter = IngredientsAdapter()
        similarItemsAdapter = SimilarItemsAdapter()
        val getIntent = intent
        // Top picks food items information
        id = getIntent.getIntExtra("FOOD_ID", 0)
        name = getIntent.getStringExtra("FOOD_NAME").toString()
        instructions = getIntent.getStringExtra("FOOD_INSTRUCTIONS").toString()
        image = getIntent.getStringExtra("FOOD_IMAGE").toString()







        setUpIngredientsRecyclerView()
        job = MainScope().launch {
             detailsViewModel.getIngredients(id)
        }


        observeIngredients()

        setUpViews()

        setUpSimilarItemsRecyclerView()
        val similarItems = MainScope().launch {
            detailsViewModel.getSimilarFoodItems(id)
        }
        observeSimilarItems()


    }

    private fun observeSimilarItems() {
        detailsViewModel.similarItemsLiveData.observe(this, Observer{
            similarItemsAdapter.similarItemsDiffer.submitList(it)
        })
    }

    private fun setUpSimilarItemsRecyclerView() {
        detailsBinding.rcvSimilarItems.apply{
            adapter = similarItemsAdapter
            layoutManager = LinearLayoutManager(this@FoodDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setUpViews() {
        detailsBinding.apply {
            collapseToolBar.title = name
            Glide.with(this@FoodDetailsActivity).load(image).into(imgMealDetails)
            tvInstructionsSteps.text = instructions
        }
    }

    private fun setUpIngredientsRecyclerView() {
        detailsBinding.rcvIngredients.apply {
            adapter = ingredientsAdapter
            layoutManager = LinearLayoutManager(this@FoodDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeIngredients(){
        detailsViewModel.ingredientsLiveData.observe(this, Observer{

            ingredientsAdapter.ingredientDiffer.submitList(it.ingredients)

        })
    }
}