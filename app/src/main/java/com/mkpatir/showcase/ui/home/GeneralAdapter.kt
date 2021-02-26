package com.mkpatir.showcase.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkpatir.showcase.api.models.CategoryModel
import com.mkpatir.showcase.api.models.ProductModel
import com.mkpatir.showcase.databinding.LayoutGeneralBinding
import com.mkpatir.showcase.internal.extension.invisible
import com.mkpatir.showcase.ui.home.categories.CategoryItemAdapter
import com.mkpatir.showcase.ui.home.products.ProductItemAdapter

class GeneralAdapter(
    private val discoverType: DiscoverTypes
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var productItems: ArrayList<ProductModel> = arrayListOf()
    private var categoryItems: ArrayList<CategoryModel> = arrayListOf()
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
                titleProducts.text = title
                btnAll.invisible()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (discoverType) {
            DiscoverTypes.NEW_PRODUCTS -> {
                ProductViewHolder(LayoutGeneralBinding.inflate(layoutInflater,parent,false))
            }
            DiscoverTypes.CATEGORIES -> {
                CategoriesViewHolder(LayoutGeneralBinding.inflate(layoutInflater,parent,false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ProductViewHolder -> holder.bind()
            is CategoriesViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = when(discoverType){
        DiscoverTypes.NEW_PRODUCTS -> if (productItems.isEmpty()) 0 else 1
        DiscoverTypes.CATEGORIES -> if (categoryItems.isEmpty()) 0 else 1
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

}