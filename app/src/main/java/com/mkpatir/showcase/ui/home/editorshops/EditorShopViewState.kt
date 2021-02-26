package com.mkpatir.showcase.ui.home.editorshops

import com.mkpatir.showcase.api.models.ShopModel

data class EditorShopViewState(
    private val item: ShopModel
) {

    fun getShopName(): String = item.name.orEmpty()

    fun getDefinition(): String = item.definition.orEmpty()

    fun getShopLogoUrl(): String = item.logo?.thumbnailPhoto?.url.orEmpty()

    fun getProductImage1Url(): String = if (item.popularProducts.isNullOrEmpty() || item.popularProducts[0].images.isNullOrEmpty()) "" else item.popularProducts[0].images?.get(0)?.thumbnailPhoto?.url.orEmpty()

    fun getProductImage2Url(): String = if ((item.popularProducts?.size ?: 0) < 2 || item.popularProducts?.get(1)?.images.isNullOrEmpty()) "" else item.popularProducts?.get(1)?.images?.get(0)?.thumbnailPhoto?.url.orEmpty()

    fun getProductImage3Url(): String = if ((item.popularProducts?.size ?: 0) < 3 || item.popularProducts?.get(2)?.images.isNullOrEmpty()) "" else item.popularProducts?.get(2)?.images?.get(0)?.thumbnailPhoto?.url.orEmpty()

}