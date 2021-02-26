package com.mkpatir.showcase.ui.home.collections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkpatir.showcase.api.models.CollectionModel
import com.mkpatir.showcase.databinding.ItemCollectionBinding

class CollectionItemAdapter: RecyclerView.Adapter<CollectionItemAdapter.CollectionItemViewHolder>() {

    private var items: ArrayList<CollectionModel> = arrayListOf()
    private var enabledAnimation = false

    inner class CollectionItemViewHolder(private val dataBinding: ItemCollectionBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(item: CollectionModel){
            dataBinding.viewState = CollectionViewState(item)
            fromLeftToRightAnimation(dataBinding.root)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CollectionItemViewHolder(ItemCollectionBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: CollectionItemViewHolder, position: Int) {
        holder.bind(items[position])
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

    fun updateAdapter(items: ArrayList<CollectionModel>){
        this.items.clear()
        this.items.addAll(items)
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