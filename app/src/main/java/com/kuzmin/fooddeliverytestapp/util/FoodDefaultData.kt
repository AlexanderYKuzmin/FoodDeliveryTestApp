package com.kuzmin.fooddeliverytestapp.util

import android.content.res.Resources
import com.kuzmin.fooddeliverytestapp.R
import com.kuzmin.fooddeliverytestapp.domain.model.food.BannerItem
import com.kuzmin.fooddeliverytestapp.domain.model.food.CatalogItem
import com.kuzmin.fooddeliverytestapp.domain.model.food.CategoryItem
import com.kuzmin.fooddeliverytestapp.domain.model.food.DiscountItem
import com.kuzmin.fooddeliverytestapp.domain.model.food.FoodData

object FoodDefaultData {

    fun getFoodDefaultData(res: Resources): FoodData {
        return FoodData(
            categoryList = getCategories(res),
            bannerList = getBanners(res),
            discountList = getDiscounts(res),
            catalogList = emptyList()
        )
    }

    fun getCategories(res: Resources): List<CategoryItem> {
        return listOf(
            CategoryItem(title = res.getString(R.string.summer_picnic), picture = R.drawable.summer_picnic),
            CategoryItem(title = res.getString(R.string.summer_launch), picture = R.drawable.summer_launch),
            CategoryItem(title = res.getString(R.string.on_breakfast), picture = R.drawable.on_brakfast),
            CategoryItem(title = res.getString(R.string.on_dinner), picture = R.drawable.on_dinner),
            CategoryItem(title = res.getString(R.string.for_children), picture = R.drawable.for_children),
            CategoryItem(title = res.getString(R.string.for_company), picture = R.drawable.for_company),
            CategoryItem(title = res.getString(R.string.for_hungriest), picture = R.drawable.for_hungriest)
        )
    }

    fun getBanners(res: Resources): List<BannerItem> {
        return listOf(
            BannerItem(picture = R.drawable.promo_1),
            BannerItem(picture = R.drawable.promo_2),
            BannerItem(picture = R.drawable.promo_3),
            BannerItem(picture = R.drawable.promo_4),
            BannerItem(picture = R.drawable.promo_5),
            BannerItem(picture = R.drawable.promo_6),
            BannerItem(picture = R.drawable.promo_7)
        )
    }

    fun getDiscounts(res: Resources): List<DiscountItem> {
        return listOf(
            DiscountItem(
                title = res.getString(R.string.dish_tom_yum),
                picture = R.drawable.discount_1,
                discountValue = -25,
                isNew = 1,
                weight = 250,
                oldPrice = 700,
                newPrice = 525
            ),
            DiscountItem(
                title = res.getString(R.string.dish_pasta_carbonara),
                picture = R.drawable.discount_2,
                discountValue = -25,
                isNew = 0,
                weight = 250,
                oldPrice = 400,
                newPrice = 300
            ),
            DiscountItem(
                title = res.getString(R.string.dish_bbq_chicken_wings),
                picture = R.drawable.discount_3,
                discountValue = -30,
                isNew = 0,
                weight = 300,
                oldPrice = 900,
                newPrice = 630
            ),
            DiscountItem(
                title = res.getString(R.string.dish_caesar_salad),
                picture = R.drawable.discount_4,
                discountValue = -40,
                isNew = 0,
                weight = 160,
                oldPrice = 600,
                newPrice = 360
            ),
            DiscountItem(
                title = res.getString(R.string.dish_philadelphia_roll),
                picture = R.drawable.discount_5,
                discountValue = -10,
                isNew = 0,
                weight = 200,
                oldPrice = 500,
                newPrice = 450
            ),
            DiscountItem(
                title = res.getString(R.string.dish_risotto_with_mushrooms),
                picture = R.drawable.discount_6,
                discountValue = -15,
                isNew = 0,
                weight = 200,
                oldPrice = 600,
                newPrice = 510
            ),
            DiscountItem(
                title = res.getString(R.string.dish_beef_stroganoff),
                picture = R.drawable.discount_7,
                discountValue = -20,
                isNew = 0,
                weight = 300,
                oldPrice = 800,
                newPrice = 640
            )
        )
    }

    fun getCatalog(res: Resources): List<CatalogItem> {
        return listOf(
            CatalogItem(
                colorId = R.color.color_1,
                title = res.getString(R.string.catalog_borscht),
                pictureId = R.drawable.set
            ),
            CatalogItem(
                colorId = R.color.color_2,
                title = res.getString(R.string.catalog_spaghetti_bolognese),
                pictureId = R.drawable.pizza
            ),
            CatalogItem(
                colorId = R.color.color_3,
                title = res.getString(R.string.catalog_chicken_kiev),
                pictureId = R.drawable.pasta
            ),
            CatalogItem(
                colorId = R.color.color_4,
                title = res.getString(R.string.catalog_greek_salad),
                pictureId = R.drawable.rizotto
            ),
            CatalogItem(
                colorId = R.color.color_5,
                title = res.getString(R.string.catalog_vegetable_ratatouille),
                pictureId = R.drawable.salad
            ),
            CatalogItem(
                colorId = R.color.color_6,
                title = res.getString(R.string.catalog_sushi_set),
                pictureId = R.drawable.half_boiled
            ),
            CatalogItem(
                colorId = R.color.color_7,
                title = res.getString(R.string.catalog_beef_tenderloin),
                pictureId = R.drawable.dessert
            ),
            CatalogItem(
                colorId = R.color.color_8,
                title = res.getString(R.string.catalog_seafood_pizza),
                pictureId = R.drawable.complement
            ),
            CatalogItem(
                colorId = R.color.color_9,
                title = res.getString(R.string.catalog_pancakes_with_caviar),
                pictureId = R.drawable.beverage
            )
        )
    }
}