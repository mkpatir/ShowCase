package com.mkpatir.showcase.ui.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.ConcatAdapter
import com.mkpatir.showcase.R
import com.mkpatir.showcase.databinding.ActivityHomeBinding
import com.mkpatir.showcase.ui.base.BaseActivity
import com.mkpatir.showcase.ui.home.featured.FeaturedAdapter
import com.mkpatir.showcase.ui.showall.ShowAllActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity: BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    private var featuredAdapter = FeaturedAdapter()
    private var productsAdapter = GeneralAdapter(DiscoverTypes.NEW_PRODUCTS)
    private var categoriesAdapter = GeneralAdapter(DiscoverTypes.CATEGORIES)
    private var collectionsAdapter = GeneralAdapter(DiscoverTypes.COLLECTIONS)
    private var editorShopsAdapter = GeneralAdapter(DiscoverTypes.EDITOR_SHOPS)
    private var newShopsAdapter = GeneralAdapter(DiscoverTypes.NEW_SHOPS)
    private var contentAdapter = ConcatAdapter(
        featuredAdapter,
        productsAdapter,
        categoriesAdapter,
        collectionsAdapter,
        editorShopsAdapter,
        newShopsAdapter
    )

    override fun setLayout(): Int = R.layout.activity_home

    override fun setViewModel(): Lazy<HomeViewModel> = viewModel()

    override fun setupUI() {
        getDataBinding().apply {
            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        getViewModel().discover()
        initObservers()
        initListeners()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            getDataBinding().searchView.setQuery(query, false)
        }
    }

    private fun initObservers(){
        getViewModel().apply {

            progressLiveData.observe(this@HomeActivity){
                if (it.not()){
                    getDataBinding().rvContent.adapter = contentAdapter
                }
            }

            featuredLiveData.observe(this@HomeActivity){
                featuredAdapter.updateAdapter(it)
            }

            productsLiveData.observe(this@HomeActivity){
                productsAdapter.updateProductAdapter(it,getViewModel().productsTitle)
            }

            categoriesLiveData.observe(this@HomeActivity){
                categoriesAdapter.updateCategoryAdapter(it,getViewModel().categoryTitle)
            }

            collectionsLiveData.observe(this@HomeActivity){
                collectionsAdapter.updateCollectionAdapter(it,getViewModel().collectionTitle)
            }

            editorShopsLiveData.observe(this@HomeActivity){
                editorShopsAdapter.updateShopAdapter(it,getViewModel().editorShopTitle)
            }

            newShopsLiveData.observe(this@HomeActivity){
                newShopsAdapter.updateShopAdapter(it,getViewModel().newShopTitle)
            }
        }
    }

    private fun initListeners(){
        productsAdapter.allClick = {
            ShowAllActivity.startActivity(this,DiscoverTypes.NEW_PRODUCTS,getViewModel().productsTitle,getViewModel().discoverList)
        }

        collectionsAdapter.allClick = {
            ShowAllActivity.startActivity(this,DiscoverTypes.COLLECTIONS,getViewModel().collectionTitle,getViewModel().discoverList)
        }

        editorShopsAdapter.allClick = {
            ShowAllActivity.startActivity(this,DiscoverTypes.EDITOR_SHOPS,getViewModel().editorShopTitle,getViewModel().discoverList)
        }

        newShopsAdapter.allClick = {
            ShowAllActivity.startActivity(this,DiscoverTypes.NEW_SHOPS,getViewModel().newShopTitle,getViewModel().discoverList)
        }

        getDataBinding().swipeRefresh.setOnRefreshListener {
            getDataBinding().swipeRefresh.isRefreshing = false
            getDataBinding().rvContent.adapter = null
            getViewModel().discover()
        }
    }

}