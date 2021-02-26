package com.mkpatir.showcase.ui.home.featured

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkpatir.showcase.api.models.FeaturedModel
import com.mkpatir.showcase.databinding.LayoutFeaturedBinding
import com.mkpatir.showcase.internal.utils.DepthPageTransformer

class FeaturedAdapter: RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder>() {

    private var items: ArrayList<FeaturedModel> = arrayListOf()

    inner class FeaturedViewHolder(private val dataBinding: LayoutFeaturedBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(){
            val featuredPagerAdapter = FeaturedPagerAdapter(items)
            dataBinding.apply {
                featuredViewPager.adapter = featuredPagerAdapter
                featuredViewPager.setPageTransformer(true,DepthPageTransformer())
                tabDots.setupWithViewPager(featuredViewPager,true)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FeaturedViewHolder(LayoutFeaturedBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = if (items.isEmpty()) 0 else 1

    fun updateAdapter(items: ArrayList<FeaturedModel>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}