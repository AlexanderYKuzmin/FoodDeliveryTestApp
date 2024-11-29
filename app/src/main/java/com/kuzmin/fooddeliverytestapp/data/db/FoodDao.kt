package com.kuzmin.fooddeliverytestapp.data.db

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.kuzmin.fooddeliverytestapp.data.db.model.CatalogDb
import com.kuzmin.fooddeliverytestapp.data.db.model.DiscountDb
import com.kuzmin.fooddeliverytestapp.data.db.model.FoodDataDb
import com.kuzmin.fooddeliverytestapp.data.db.model.PromoActionDb
import com.kuzmin.fooddeliverytestapp.data.db.model.PromoSectionDb

@Dao
interface FoodDao {

    @Query("SELECT * FROM promo_sections")
    fun getSections(): List<PromoSectionDb>

    @Query("SELECT * FROM promo_actions")
    fun getActions(): List<PromoActionDb>

    @Query("SELECT * FROM discounts")
    fun getDiscounts(): List<DiscountDb>

    @Query("SELECT * FROM catalog")
    fun getCatalog(): List<CatalogDb>

    @Transaction
    suspend fun getAll(): FoodDataDb {
        return FoodDataDb(
            sections = getSections(),
            actions = getActions(),
            discounts = getDiscounts(),
            catalog = getCatalog()
        )
    }

    @Insert
    suspend fun insertPromoSections(promoSections: List<PromoSectionDb>): List<Long>

    @Insert
    suspend fun insertPromoActions(promoActions: List<PromoActionDb>): List<Long>

    @Insert
    suspend fun insertDiscounts(discounts: List<DiscountDb>): List<Long>

    @Insert
    suspend fun insertCatalog(catalog: List<CatalogDb>): List<Long>


    @Transaction
    suspend fun insertAll(foodDataDb: FoodDataDb) {
        Log.d("DB", "Inserting: $foodDataDb")
        insertPromoSections(foodDataDb.sections)
        insertPromoActions(foodDataDb.actions)
        insertDiscounts(foodDataDb.discounts)
        insertCatalog(foodDataDb.catalog)
    }
}