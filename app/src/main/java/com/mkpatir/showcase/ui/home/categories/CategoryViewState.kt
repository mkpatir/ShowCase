package com.mkpatir.showcase.ui.home.categories

import com.mkpatir.showcase.api.models.CategoryModel

data class CategoryViewState(
    private val item: CategoryModel
) {

    fun getCategoryName(): String = item.name.orEmpty()

    fun getCategoryLogoUrl(): String = item.logo?.mediumPhoto?.url.orEmpty()
}