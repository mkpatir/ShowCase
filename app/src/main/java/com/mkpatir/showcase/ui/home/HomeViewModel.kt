package com.mkpatir.showcase.ui.home

import androidx.lifecycle.MutableLiveData
import com.mkpatir.showcase.api.AppRepository
import com.mkpatir.showcase.api.models.*
import com.mkpatir.showcase.ui.base.BaseViewModel

class HomeViewModel(
        private val appRepository: AppRepository
): BaseViewModel() {

    val discoverList: ArrayList<DiscoverResponseModel> = arrayListOf()

    val featuredLiveData = MutableLiveData<ArrayList<FeaturedModel>>()
    val productsLiveData = MutableLiveData<ArrayList<ProductModel>>()
    val categoriesLiveData = MutableLiveData<ArrayList<CategoryModel>>()
    val collectionsLiveData = MutableLiveData<ArrayList<CollectionModel>>()
    val editorShopsLiveData = MutableLiveData<ArrayList<ShopModel>>()
    val newShopsLiveData = MutableLiveData<ArrayList<ShopModel>>()

    var productsTitle = ""
    var categoryTitle = ""
    var collectionTitle = ""
    var editorShopTitle = ""
    var newShopTitle = ""

    fun discover(){
        callService(appRepository.discover()) { list ->
            discoverList.clear()
            discoverList.addAll(list)
            list.find { item -> item.type == FEATURED }?.let { featuredLiveData.postValue(it.featured) }
            list.find { item -> item.type == PRODUCTS }?.let {
                productsTitle = it.title.orEmpty()
                productsLiveData.postValue(it.products)
            }
            list.find { item -> item.type == CATEGORIES }?.let {
                categoryTitle = it.title.orEmpty()
                categoriesLiveData.postValue(it.categories)
            }
            list.find { item -> item.type == COLLECTIONS }?.let {
                collectionTitle = it.title.orEmpty()
                collectionsLiveData.postValue(it.collections)
            }
            list.find { item -> item.type == EDITOR_SHOPS }?.let {
                editorShopTitle = it.title.orEmpty()
                editorShopsLiveData.postValue(it.shops)
            }
            list.find { item -> item.type == NEW_SHOPS }?.let {
                newShopTitle = it.title.orEmpty()
                newShopsLiveData.postValue(it.shops)
            }
        }
    }

    companion object {
        private const val FEATURED = "featured"
        const val PRODUCTS = "new_products"
        private const val CATEGORIES = "categories"
        const val COLLECTIONS = "collections"
        const val EDITOR_SHOPS = "editor_shops"
        const val NEW_SHOPS = "new_shops"
    }

}