package com.mkpatir.showcase.ui.home.editorshops

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkpatir.showcase.api.models.ShopModel
import com.mkpatir.showcase.databinding.ItemEditorShopBinding

class EditorShopItemAdapter: RecyclerView.Adapter<EditorShopItemAdapter.EditorShopItemViewHolder>() {

    private var items: ArrayList<ShopModel> = arrayListOf()

    inner class EditorShopItemViewHolder(private val dataBinding: ItemEditorShopBinding): RecyclerView.ViewHolder(dataBinding.root){

        fun bind(item: ShopModel){
            dataBinding.viewState = EditorShopViewState(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditorShopItemViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        return EditorShopItemViewHolder(ItemEditorShopBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: EditorShopItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateAdapter(items: ArrayList<ShopModel>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}