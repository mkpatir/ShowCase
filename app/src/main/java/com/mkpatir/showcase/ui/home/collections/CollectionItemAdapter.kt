package com.mkpatir.showcase.ui.home.collections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkpatir.showcase.api.models.CollectionModel
import com.mkpatir.showcase.databinding.ItemCollectionBinding
import com.mkpatir.showcase.databinding.ItemCollectionFullWidthBinding

class CollectionItemAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<CollectionModel> = arrayListOf()
    private var enabledAnimation = false
    private var isFullWidthCollection = false

    inner class CollectionItemViewHolder(private val dataBinding: ItemCollectionBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(item: CollectionModel){
            dataBinding.viewState = CollectionViewState(item)
            fromLeftToRightAnimation(dataBinding.root)
        }
    }

    inner class FullWidthCollectionItemViewHolder(private val dataBinding: ItemCollectionFullWidthBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(item: CollectionModel){
            dataBinding.viewState = CollectionViewState(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return if (isFullWidthCollection)
            FullWidthCollectionItemViewHolder(ItemCollectionFullWidthBinding.inflate(layoutInflater,parent,false))
        else
            CollectionItemViewHolder(ItemCollectionBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CollectionItemViewHolder -> holder.bind(items[position])
            is FullWidthCollectionItemViewHolder -> holder.bind(items[position])
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

    fun updateAdapter(items: ArrayList<CollectionModel>,isFullWidthCollection: Boolean){
        this.items.clear()
        this.items.addAll(items)
        this.isFullWidthCollection = isFullWidthCollection
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