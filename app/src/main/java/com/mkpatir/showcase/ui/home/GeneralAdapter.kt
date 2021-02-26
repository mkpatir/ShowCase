package com.mkpatir.showcase.ui.home

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.mkpatir.showcase.R
import com.mkpatir.showcase.api.models.CategoryModel
import com.mkpatir.showcase.api.models.CollectionModel
import com.mkpatir.showcase.api.models.ProductModel
import com.mkpatir.showcase.api.models.ShopModel
import com.mkpatir.showcase.databinding.LayoutGeneralBinding
import com.mkpatir.showcase.internal.extension.invisible
import com.mkpatir.showcase.internal.extension.loadImageFromUrl
import com.mkpatir.showcase.internal.extension.visible
import com.mkpatir.showcase.ui.home.categories.CategoryItemAdapter
import com.mkpatir.showcase.ui.home.collections.CollectionItemAdapter
import com.mkpatir.showcase.ui.home.editorshops.EditorShopItemAdapter
import com.mkpatir.showcase.ui.home.newshops.NewShopItemAdapter
import com.mkpatir.showcase.ui.home.products.ProductItemAdapter

class GeneralAdapter(
    private val discoverType: DiscoverTypes
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var productItems: ArrayList<ProductModel> = arrayListOf()
    private var categoryItems: ArrayList<CategoryModel> = arrayListOf()
    private var collectionItems: ArrayList<CollectionModel> = arrayListOf()
    private var shopItems: ArrayList<ShopModel> = arrayListOf()
    private var title = ""
    internal var allClick:() -> Unit = {}

    inner class ProductViewHolder(private val dataBinding: LayoutGeneralBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(){
            dataBinding.apply {
                rvItems.adapter = ProductItemAdapter().apply {
                    updateAdapter(productItems)
                }
                titleProducts.text = title
                btnAll.setOnClickListener {
                    allClick()
                }
            }
        }
    }

    inner class CategoriesViewHolder(private val dataBinding: LayoutGeneralBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(){
            dataBinding.apply {
                rvItems.adapter = CategoryItemAdapter().apply {
                    updateAdapter(categoryItems)
                }
                imgGeneral.setImageDrawable(ColorDrawable(ContextCompat.getColor(imgGeneral.context, R.color.blue_grey_50)))
                titleProducts.text = title
                btnAll.invisible()
            }
        }
    }

    inner class CollectionsViewHolder(private val dataBinding: LayoutGeneralBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(){
            dataBinding.apply {
                rvItems.adapter = CollectionItemAdapter().apply {
                    updateAdapter(collectionItems)
                }
                titleProducts.text = title
                btnAll.setOnClickListener {
                    allClick()
                }
            }
        }
    }

    inner class EditorShopsViewHolder(private val dataBinding: LayoutGeneralBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(){
            dataBinding.apply {
                alphaView.visible()
                imgGeneral.loadImageFromUrl(shopItems[0].coverPhoto?.url.orEmpty())
                rvItems.adapter = EditorShopItemAdapter().apply {
                    updateAdapter(shopItems)
                }
                LinearSnapHelper().apply {
                    attachToRecyclerView(rvItems)
                }
                rvItems.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        if (newState == SCROLL_STATE_IDLE){
                            val currentPosition = (rvItems.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                            if (currentPosition >= 0 && currentPosition < shopItems.size){
                                imgGeneral.loadImageFromUrl(shopItems[currentPosition].coverPhoto?.url.orEmpty())
                            }
                        }
                        super.onScrollStateChanged(recyclerView, newState)
                    }
                })
                titleProducts.text = title
                btnAll.setOnClickListener {
                    allClick()
                }
            }
        }
    }

    inner class NewShopsViewHolder(private val dataBinding: LayoutGeneralBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(){
            dataBinding.apply {
                rvItems.adapter = NewShopItemAdapter().apply {
                    updateAdapter(shopItems)
                }
                titleProducts.text = title
                btnAll.setOnClickListener {
                    allClick()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutGeneralBinding.inflate(layoutInflater,parent,false)
        return when (discoverType) {
            DiscoverTypes.NEW_PRODUCTS -> {
                ProductViewHolder(binding)
            }
            DiscoverTypes.CATEGORIES -> {
                CategoriesViewHolder(binding)
            }
            DiscoverTypes.COLLECTIONS -> {
                CollectionsViewHolder(binding)
            }
            DiscoverTypes.EDITOR_SHOPS -> {
                EditorShopsViewHolder(binding)
            }
            DiscoverTypes.NEW_SHOPS -> {
                NewShopsViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ProductViewHolder -> holder.bind()
            is CategoriesViewHolder -> holder.bind()
            is CollectionsViewHolder -> holder.bind()
            is EditorShopsViewHolder -> holder.bind()
            is NewShopsViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = when(discoverType){
        DiscoverTypes.NEW_PRODUCTS -> if (productItems.isEmpty()) 0 else 1
        DiscoverTypes.CATEGORIES -> if (categoryItems.isEmpty()) 0 else 1
        DiscoverTypes.COLLECTIONS -> if (collectionItems.isEmpty()) 0 else 1
        DiscoverTypes.EDITOR_SHOPS -> if (shopItems.isEmpty()) 0 else 1
        DiscoverTypes.NEW_SHOPS -> if (shopItems.isEmpty()) 0 else 1
    }

    fun updateProductAdapter(items: ArrayList<ProductModel>,title: String){
        productItems.clear()
        productItems.addAll(items)
        this.title = title
        notifyDataSetChanged()
    }

    fun updateCategoryAdapter(items: ArrayList<CategoryModel>,title: String){
        categoryItems.clear()
        categoryItems.addAll(items)
        this.title = title
        notifyDataSetChanged()
    }

    fun updateCollectionAdapter(items: ArrayList<CollectionModel>,title: String){
        collectionItems.clear()
        collectionItems.addAll(items)
        this.title = title
        notifyDataSetChanged()
    }

    fun updateShopAdapter(items: ArrayList<ShopModel>,title: String){
        shopItems.clear()
        shopItems.addAll(items)
        this.title = title
        notifyDataSetChanged()
    }

}