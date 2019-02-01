package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso

class ImagePagerAdapter(val imageList: List<String>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as ImageView;
    }

    override fun getCount(): Int = imageList.size

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val itemView = (container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.pager_item, container, false)
        val imageView = itemView as ImageView
        Picasso.get()
                .load(imageList.get(position))
                .centerCrop()
                .fit()
                .into(imageView)
        container.addView(itemView)
        return itemView
    }
}