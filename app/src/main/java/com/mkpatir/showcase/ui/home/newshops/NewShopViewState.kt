package com.mkpatir.showcase.ui.home.newshops

import android.content.Context
import com.mkpatir.showcase.R
import com.mkpatir.showcase.api.models.ShopModel

data class NewShopViewState(
    private val context: Context,
    private val item: ShopModel
) {

    fun getShopName(): String = item.name.orEmpty()

    fun getFirstCharShop(): String = (item.name?.firstOrNull() ?: "").toString()

    fun getDefinition(): String = item.definition.orEmpty()

    fun getShopLogoUrl(): String = item.logo?.thumbnailPhoto?.url.orEmpty()

    fun getShopImageUrl(): String = item.coverPhoto?.url.orEmpty()

    fun getProductCount(): String = context.getString(R.string.product_count,item.productCount ?: 0)

    fun isShowLogoImg(): Boolean = item.logo?.thumbnailPhoto?.url != null

}