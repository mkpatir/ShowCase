package com.mkpatir.showcase.internal.extension
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imgUrl")
fun ImageView.loadImageFromUrl(url: String){
    Glide.with(this.context.applicationContext).load(url).into(this)
}

fun View.invisible(){
    visibility = View.INVISIBLE
}