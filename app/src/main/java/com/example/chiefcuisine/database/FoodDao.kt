package com.example.chiefcuisine.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.chiefcuisine.apiresponse.Recipe

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodItem(recipe: Recipe)

    @Delete
    suspend fun deleteFoodItem(recipe: Recipe)


}