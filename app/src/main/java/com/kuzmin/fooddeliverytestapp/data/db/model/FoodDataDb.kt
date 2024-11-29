package com.kuzmin.fooddeliverytestapp.data.db.model

data class FoodDataDb(
    val sections: List<PromoSectionDb>,

    val actions: List<PromoActionDb>,

    val discounts: List<DiscountDb>,

    val catalog: List<CatalogDb>
)