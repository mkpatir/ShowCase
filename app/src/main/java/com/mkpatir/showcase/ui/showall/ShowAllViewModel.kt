package com.mkpatir.showcase.ui.showall

import com.mkpatir.showcase.api.models.CollectionModel
import com.mkpatir.showcase.api.models.DiscoverResponseModel
import com.mkpatir.showcase.api.models.ProductModel
import com.mkpatir.showcase.ui.base.BaseViewModel
import com.mkpatir.showcase.ui.home.HomeViewModel

class ShowAllViewModel: BaseViewModel() {

    val discoverList: ArrayList<DiscoverResponseModel> = arrayListOf()

    fun getNewProductsList(): ArrayList<ProductModel> {
        return discoverList.find { item -> item.type == HomeViewModel.PRODUCTS }?.products ?: arrayListOf()
    }

    fun getCollectionsList(): ArrayList<CollectionModel> {
        return discoverList.find { item -> item.type == HomeViewModel.COLLECTIONS }?.collections ?: arrayListOf()
    }

}