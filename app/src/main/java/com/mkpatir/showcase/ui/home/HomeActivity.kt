package com.mkpatir.showcase.ui.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.ConcatAdapter
import com.mkpatir.showcase.R
import com.mkpatir.showcase.databinding.ActivityHomeBinding
import com.mkpatir.showcase.ui.base.BaseActivity
import com.mkpatir.showcase.ui.home.featured.FeaturedAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity: BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    private val featuredAdapter = FeaturedAdapter()
    private val productsAdapter = GeneralAdapter(DiscoverTypes.NEW_PRODUCTS)
    private val categoriesAdapter = GeneralAdapter(DiscoverTypes.CATEGORIES)
    private val contentAdapter = ConcatAdapter(
        featuredAdapter,
        productsAdapter,
        categoriesAdapter
    )

    override fun setLayout(): Int = R.layout.activity_home

    override fun setViewModel(): Lazy<HomeViewModel> = viewModel()

    override fun setupUI() {
        getDataBinding().apply {
            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

            rvContent.adapter = contentAdapter
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
            featuredLiveData.observe(this@HomeActivity){
                featuredAdapter.updateAdapter(it)
            }

            productsLiveData.observe(this@HomeActivity){
                productsAdapter.updateProductAdapter(it,getViewModel().productsTitle)
            }

            categoriesLiveData.observe(this@HomeActivity){
                categoriesAdapter.updateCategoryAdapter(it,getViewModel().categoryTitle)
            }
        }
    }

    private fun initListeners(){
        productsAdapter.allClick = {

        }
    }

}