package com.mkpatir.showcase.ui.home.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkpatir.showcase.api.models.ProductModel
import com.mkpatir.showcase.databinding.ItemProductBinding
import com.mkpatir.showcase.databinding.ItemProductFullWidthBinding

class ProductItemAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ProductModel> = arrayListOf()
    private var enabledAnimation = false
    private var isFullWidthProduct = false

    inner class ProductItemViewHolder(private val dataBinding: ItemProductBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(item: ProductModel){
            fromLeftToRightAnimation(dataBinding.root)
            dataBinding.viewState = ProductViewState(item)
        }
    }

    inner class FullWidthProductItemViewHolder(private val dataBinding: ItemProductFullWidthBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(item: ProductModel){
            dataBinding.viewState = ProductViewState(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (isFullWidthProduct){
            FullWidthProductItemViewHolder(ItemProductFullWidthBinding.inflate(layoutInflater,parent,false))
        }
        else {
            ProductItemViewHolder(ItemProductBinding.inflate(layoutInflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ProductItemViewHolder -> holder.bind(items[position])
            is FullWidthProductItemViewHolder -> holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                enabledAnimation = true
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        super.onAttachedToRecyclerView(recyclerView)
    }

    fun updateAdapter(items: ArrayList<ProductModel>,isFullWidthProduct: Boolean){
        this.items.clear()
        this.items.addAll(items)
        this.isFullWidthProduct = isFullWidthProduct
        notifyDataSetChanged()
    }

    private fun fromLeftToRightAnimation(itemView: View) {
        if (enabledAnimation){
            itemView.translationX = 500f
            itemView.animate().apply {
                translationX(0f)
                duration = 250L
                start()
            }
        }
    }

}