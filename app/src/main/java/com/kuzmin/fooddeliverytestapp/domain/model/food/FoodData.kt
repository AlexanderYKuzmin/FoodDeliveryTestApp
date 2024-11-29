package com.kuzmin.fooddeliverytestapp.domain.model.food

data class FoodData(

    val categoryList: List<CategoryItem>,

    val bannerList: List<BannerItem>,

    val discountList: List<DiscountItem>,

    val catalogList: List<CatalogItem>
)
