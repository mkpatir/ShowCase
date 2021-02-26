package com.mkpatir.showcase.ui.home.products

import com.mkpatir.showcase.api.models.ProductModel

data class ProductViewState(
    private val productItem: ProductModel
) {

    fun getProductName(): String = productItem.title.orEmpty()

    fun getShopName(): String = productItem.shop?.name.orEmpty()

    fun getProductImageUrl(): String {
        return if (productItem.images.isNullOrEmpty()){
            ""
        }
        else {
            productItem.images[0].url.orEmpty()
        }
    }

}