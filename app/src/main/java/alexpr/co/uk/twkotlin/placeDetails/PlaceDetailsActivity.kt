package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.models.Section
import alexpr.co.uk.twkotlin.models.ServiceItem
import alexpr.co.uk.twkotlin.models.ServiceModel
import android.animation.ArgbEvaluator
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.place_details_activity.*


class PlaceDetailsActivity : AppCompatActivity() {
    private lateinit var sections: ArrayList<Section>

    private var mBottomSheetBehavior: BottomSheetBehavior<View>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.place_details_activity)

        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById<View>(R.id.bottom_bar))
        bottom_bar_layout.setOnClickListener {  }
        val viewPager: ViewPager = findViewById(R.id.place_details_viepager)
        viewPager.adapter = ImagePagerAdapter(listOf("https://upload.wikimedia.org/wikipedia/commons/a/a1/Khajuraho-landscape.jpg", "https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg"))

        val anim = ObjectAnimator.ofObject(findViewById<ImageView>(R.id.toolbar_back), "colorFilter", ArgbEvaluator(), Color.parseColor("#FFFFFFFF"), Color.parseColor("#FFFF0000"))
        anim.duration = 1000


        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { p0, p1 ->
            val alpha: Float = ((-p1 * 100 / p0.totalScrollRange / 100f))
            getWindow().statusBarColor = Color.argb((alpha * 100).toInt(), 0, 0, 0)
            anim.currentPlayTime = (alpha * 1000).toLong()
            fake_scrim_effect.alpha = alpha
            if (alpha > 0.6) {
                search_query_field.setAlpha(alpha / 0.4f - 1.5f)
            } else {
                search_query_field.setAlpha(.0f)
            }
        })
        sections = ArrayList<Section>()
        var section = Section("January Beauty Sale", ArrayList(), false)
        var serviceItem = ServiceItem(ArrayList(), ServiceModel("Service1January", "20 min", "£30", "-25%", "25", false, 111111), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1January", "21 min", "£31", "-5%", "15", false, 111112))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2January", "22 min", "£32", "-15%", "25", false, 111113))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3January", "23 min", "£33", "-35%", "35", false, 111114))
        section.serviceItem.add(serviceItem)

        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service2January", "20 min", "£30", "-25%", "25", false, 111115), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1January", "21 min", "£31", "-5%", "15", false, 111116))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2January", "22 min", "£32", "-15%", "25", false, 111117))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3January", "23 min", "£33", "-35%", "35", false, 111118))
        section.serviceItem.add(serviceItem)
        sections.add(section)



        section = Section("February Beauty Sale", ArrayList(), false)
        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service1February", "20 min", "£30", "-25%", "25", false, 111119), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1February", "21 min", "£31", "-5%", "15", false, 111120))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2February", "22 min", "£32", "-15%", "25", false, 111121))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3February", "23 min", "£33", "-35%", "35", false, 111122))
        section.serviceItem.add(serviceItem)

        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service2February", "20 min", "£30", "-25%", "25", false, 111123), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1January", "21 min", "£31", "-5%", "15", false, 111124))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2January", "22 min", "£32", "-15%", "25", false, 111125))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3January", "23 min", "£33", "-35%", "35", false, 111126))
        section.serviceItem.add(serviceItem)
        sections.add(section)



        section = Section("March Beauty Sale", ArrayList(), false)
        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service1March", "20 min", "£30", "-25%", "25", false, 111127), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1March", "21 min", "£31", "-5%", "15", false, 111128))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2March", "22 min", "£32", "-15%", "25", false, 111129))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3March", "23 min", "£33", "-35%", "35", false, 111130))
        section.serviceItem.add(serviceItem)

        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service2March", "20 min", "£30", "-25%", "25", false, 111131), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1March", "21 min", "£31", "-5%", "15", false, 111132))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2March", "22 min", "£32", "-15%", "25", false, 111133))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3March", "23 min", "£33", "-35%", "35", false, 111134))
        section.serviceItem.add(serviceItem)
        sections.add(section)



        section = Section("April Beauty Sale", ArrayList(), false)
        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service1April", "20 min", "£30", "-25%", "25", false, 111135), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1April", "21 min", "£31", "-5%", "15", false, 111136))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2April", "22 min", "£32", "-15%", "25", false, 111137))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3April", "23 min", "£33", "-35%", "35", false, 111138))
        section.serviceItem.add(serviceItem)

        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service2April", "20 min", "£30", "-25%", "25", false, 111139), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1April", "21 min", "£31", "-5%", "15, false", false, 111140))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2April", "22 min", "£32", "-15%", "25", false, 111141))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3April", "23 min", "£33", "-35%", "35", false, 111142))
        section.serviceItem.add(serviceItem)
        sections.add(section)

        generateList(sections)

    }

    private fun generateList(list: List<Section>) {
        main_container.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        for (section in list) {
            main_container.addView(SectionView(this, section, { serviceId: Int -> handleItemClick(serviceId) }))
        }
    }

    private fun handleItemClick(serviceId: Int) {
        var count = 0
        for (section in sections) {
            for (service in section.serviceItem) {
                for (subService in service.serviceSubItem) {
                    if (subService.selected) {count++}
                }
            }
        }

        if (count>0) {
            mBottomSheetBehavior?.setState(BottomSheetBehavior.STATE_EXPANDED)
        } else {
            mBottomSheetBehavior?.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }
        selected_item_count.text = count.toString()
    }
}