package com.mkpatir.showcase.ui.home.featured

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager.widget.PagerAdapter
import com.mkpatir.showcase.R
import com.mkpatir.showcase.api.models.FeaturedModel
import com.mkpatir.showcase.internal.extension.loadImageFromUrl

class FeaturedPagerAdapter(private val items: ArrayList<FeaturedModel>): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(container.context)
        val view = layoutInflater.inflate(R.layout.item_featured,container,false)
        val title = view.findViewById<AppCompatTextView>(R.id.title_featured)
        val subtitle = view.findViewById<AppCompatTextView>(R.id.subtitle_featured)
        val image = view.findViewById<ImageView>(R.id.img_featured)

        title.text = items[position].title.orEmpty()
        subtitle.text = items[position].subtitle.orEmpty()
        image.loadImageFromUrl(items[position].coverPhoto?.url.orEmpty())

        container.addView(view)
        return view
    }

    override fun getCount(): Int = items.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}