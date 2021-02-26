package com.mkpatir.showcase.ui.home

import androidx.lifecycle.MutableLiveData
import com.mkpatir.showcase.api.AppRepository
import com.mkpatir.showcase.api.models.CategoryModel
import com.mkpatir.showcase.api.models.CollectionModel
import com.mkpatir.showcase.api.models.FeaturedModel
import com.mkpatir.showcase.api.models.ProductModel
import com.mkpatir.showcase.ui.base.BaseViewModel

class HomeViewModel(
        private val appRepository: AppRepository
): BaseViewModel() {

    val featuredLiveData = MutableLiveData<ArrayList<FeaturedModel>>()
    val productsLiveData = MutableLiveData<ArrayList<ProductModel>>()
    val categoriesLiveData = MutableLiveData<ArrayList<CategoryModel>>()
    val collectionsLiveData = MutableLiveData<ArrayList<CollectionModel>>()

    var productsTitle = ""
    var categoryTitle = ""
    var collectionTitle = ""

    fun discover(){
        callService(appRepository.discover()) { list ->
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
        }
    }

    companion object {
        private const val FEATURED = "featured"
        private const val PRODUCTS = "new_products"
        private const val CATEGORIES = "categories"
        private const val COLLECTIONS = "collections"
    }

}