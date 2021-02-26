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
        /*var i = i
        if (!on_attach) {
            i = -1
        }
        val not_first_item = i == -1
        i = i + 1*/
        /*val animatorSet = AnimatorSet()
        val animatorTranslateY: ObjectAnimator =
            ObjectAnimator.ofFloat(itemView, "translationX", -400f, 0)
        val animatorAlpha = ObjectAnimator.ofFloat(itemView, "alpha", 1f)
        ObjectAnimator.ofFloat(itemView, "alpha", 0f).start()
        animatorTranslateY.setStartDelay(if (not_first_item) DURATION else i * DURATION)
        animatorTranslateY.duration = (if (not_first_item) 2 else 1) * DURATION
        animatorSet.playTogether(animatorTranslateY, animatorAlpha)
        animatorSet.start()*/
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