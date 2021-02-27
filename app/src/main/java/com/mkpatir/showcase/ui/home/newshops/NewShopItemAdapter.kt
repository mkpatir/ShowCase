package com.mkpatir.showcase.ui.home.newshops

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkpatir.showcase.api.models.ShopModel
import com.mkpatir.showcase.databinding.ItemNewShopBinding
import com.mkpatir.showcase.databinding.ItemNewShopFullWidthBinding

class NewShopItemAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ShopModel> = arrayListOf()
    private var enabledAnimation = false
    private var isFullWidthShop = false

    inner class NewShopViewHolder(private val dataBinding: ItemNewShopBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(item: ShopModel){
            dataBinding.viewState = NewShopViewState(dataBinding.root.context,item)
            fromLeftToRightAnimation(dataBinding.root)
        }
    }

    inner class FullWidthNewShopViewHolder(private val dataBinding: ItemNewShopFullWidthBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(item: ShopModel){
            dataBinding.viewState = NewShopViewState(dataBinding.root.context,item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (isFullWidthShop)
            FullWidthNewShopViewHolder(ItemNewShopFullWidthBinding.inflate(layoutInflater,parent,false))
        else
            NewShopViewHolder(ItemNewShopBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is NewShopViewHolder -> holder.bind(items[position])
            is FullWidthNewShopViewHolder -> holder.bind(items[position])
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

    fun updateAdapter(items: ArrayList<ShopModel>,isFullWidthShop: Boolean){
        this.items.clear()
        this.items.addAll(items)
        this.isFullWidthShop = isFullWidthShop
        notifyDataSetChanged()
    }
}