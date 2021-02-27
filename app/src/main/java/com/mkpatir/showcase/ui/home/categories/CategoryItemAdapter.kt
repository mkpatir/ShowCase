package com.mkpatir.showcase.ui.home.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkpatir.showcase.api.models.CategoryModel
import com.mkpatir.showcase.databinding.ItemCategoryBinding

class CategoryItemAdapter: RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>() {

    private var items: ArrayList<CategoryModel> = arrayListOf()
    private var enabledAnimation = false

    inner class CategoryItemViewHolder(private val dataBinding: ItemCategoryBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(item: CategoryModel){
            fromLeftToRightAnimation(dataBinding.root)
            dataBinding.viewState = CategoryViewState(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoryItemViewHolder(ItemCategoryBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
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

    fun updateAdapter(items: ArrayList<CategoryModel>){
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