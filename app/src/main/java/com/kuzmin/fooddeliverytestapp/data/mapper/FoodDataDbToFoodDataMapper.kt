package com.kuzmin.fooddeliverytestapp.data.mapper

import com.kuzmin.fooddeliverytestapp.data.db.model.CatalogDb
import com.kuzmin.fooddeliverytestapp.data.db.model.DiscountDb
import com.kuzmin.fooddeliverytestapp.data.db.model.FoodDataDb
import com.kuzmin.fooddeliverytestapp.data.db.model.PromoActionDb
import com.kuzmin.fooddeliverytestapp.data.db.model.PromoSectionDb
import com.kuzmin.fooddeliverytestapp.domain.model.food.BannerItem
import com.kuzmin.fooddeliverytestapp.domain.model.food.CatalogItem
import com.kuzmin.fooddeliverytestapp.domain.model.food.DiscountItem
import com.kuzmin.fooddeliverytestapp.domain.model.food.FoodData
import com.kuzmin.fooddeliverytestapp.domain.model.food.CategoryItem
import javax.inject.Inject

class FoodDataDbToFoodDataMapper @Inject constructor() {

    fun mapFoodDataDbToFoodData(foodDataDb: FoodDataDb): FoodData {
        /*with(foodDataDb) {
            return FoodData(
                categoryList = mapSectionsDbToSections(sections),
                bannerList = mapActionsDbToActions(actions),
                discountList = mapDiscountsDbToDiscounts(discounts),
                catalogList = mapCatalogDbToCatalog(catalog)
            )
        }*/
        return TODO()
    }

    private fun mapSectionsDbToSections(sectionsDb: List<PromoSectionDb>): List<CategoryItem> {
        return sectionsDb.map { mapSectionDbToSection(it) }
    }


    private fun mapSectionDbToSection(sectionDb: PromoSectionDb): CategoryItem {
        return CategoryItem(
            title = sectionDb.title,
            picture = sectionDb.picture
        )
    }


    private fun mapActionsDbToActions(actionsDb: List<PromoActionDb>): List<BannerItem> {
        return actionsDb.map { mapActionDbToAction(it) }
    }


    private fun mapActionDbToAction(actionDb: PromoActionDb): BannerItem {
        return BannerItem(
            picture = actionDb.picture
        )
    }


    private fun mapDiscountsDbToDiscounts(discountsDb: List<DiscountDb>): List<DiscountItem> {
        return discountsDb.map { mapDiscountDbToDiscount(it) }
    }


    private fun mapCatalogDbToCatalog(catalogDb: List<CatalogDb>): List<CatalogItem> {
        return catalogDb.map { mapCatalogDbToCatalog(it) }
    }


    private fun mapCatalogDbToCatalog(catalogDb: CatalogDb): CatalogItem {
        TODO()
    }


    private fun mapDiscountDbToDiscount(discountDb: DiscountDb): DiscountItem {
        with(discountDb) {
            return DiscountItem(
                title = title,
                picture = picture,
                discountValue = discount,
                isNew = isNew,
                weight = weight,
                oldPrice = oldPrice,
                newPrice = newPrice
            )
        }
    }
}