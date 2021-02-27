package com.mkpatir.showcase.ui.showall

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkpatir.showcase.R
import com.mkpatir.showcase.api.models.DiscoverResponseModel
import com.mkpatir.showcase.databinding.ActivityShowAllBinding
import com.mkpatir.showcase.ui.base.BaseActivity
import com.mkpatir.showcase.ui.home.DiscoverTypes
import com.mkpatir.showcase.ui.home.collections.CollectionItemAdapter
import com.mkpatir.showcase.ui.home.products.ProductItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowAllActivity: BaseActivity<ActivityShowAllBinding,ShowAllViewModel>() {

    companion object {

        private const val DISCOVER_TYPE = "discover_type"
        private const val TITLE = "title"
        private const val DISCOVER_LIST = "discover_list"

        fun startActivity(context: Context,discoverType: DiscoverTypes,title: String,discoverList: ArrayList<DiscoverResponseModel>){
            val intent = Intent(context,ShowAllActivity::class.java).apply {
                putExtra(DISCOVER_TYPE,discoverType)
                putExtra(TITLE,title)
                putParcelableArrayListExtra(DISCOVER_LIST,discoverList)
            }
            context.startActivity(intent)
        }
    }

    override fun setLayout(): Int = R.layout.activity_show_all

    override fun setViewModel(): Lazy<ShowAllViewModel> = viewModel()

    override fun setupUI() {
        getDataBinding().apply {

            toolbar.setNavigationOnClickListener {
                finish()
            }

            intent?.apply {
                toolbar.title = getStringExtra(TITLE).orEmpty()
                getParcelableArrayListExtra<DiscoverResponseModel>(DISCOVER_LIST)?.let { getViewModel().discoverList.addAll(it) }

                when((getSerializableExtra(DISCOVER_TYPE) as DiscoverTypes)){
                    DiscoverTypes.NEW_PRODUCTS -> {
                        rvContent.layoutManager = GridLayoutManager(rvContent.context,2)
                        rvContent.adapter = ProductItemAdapter().apply {
                            updateAdapter(getViewModel().getNewProductsList(),true)
                        }
                    }
                    DiscoverTypes.COLLECTIONS -> {
                        rvContent.layoutManager = LinearLayoutManager(rvContent.context)
                        rvContent.adapter = CollectionItemAdapter().apply {
                            updateAdapter(getViewModel().getCollectionsList(),true)
                        }
                    }
                }

            }
        }
    }
}