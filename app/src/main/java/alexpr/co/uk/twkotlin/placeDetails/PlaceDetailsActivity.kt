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
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.place_details_activity.*


class PlaceDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.place_details_activity)

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
        val sections = ArrayList<Section>()
        var section = Section("January Beauty Sale", ArrayList(), false)
        var serviceItem = ServiceItem(ArrayList(), ServiceModel("Service1January", "20 min", "£30", "-25%", "25", false), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1January", "21 min", "£31", "-5%", "15", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2January", "22 min", "£32", "-15%", "25", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3January", "23 min", "£33", "-35%", "35", false))
        section.serviceItem.add(serviceItem)

        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service2January", "20 min", "£30", "-25%", "25", false), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1January", "21 min", "£31", "-5%", "15", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2January", "22 min", "£32", "-15%", "25", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3January", "23 min", "£33", "-35%", "35", false))
        section.serviceItem.add(serviceItem)
        sections.add(section)



        section = Section("February Beauty Sale", ArrayList(), false)
        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service1February", "20 min", "£30", "-25%", "25", false), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1February", "21 min", "£31", "-5%", "15", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2February", "22 min", "£32", "-15%", "25", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3February", "23 min", "£33", "-35%", "35", false))
        section.serviceItem.add(serviceItem)

        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service2February", "20 min", "£30", "-25%", "25", false), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1January", "21 min", "£31", "-5%", "15", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2January", "22 min", "£32", "-15%", "25", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3January", "23 min", "£33", "-35%", "35", false))
        section.serviceItem.add(serviceItem)
        sections.add(section)



        section = Section("March Beauty Sale", ArrayList(), false)
        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service1March", "20 min", "£30", "-25%", "25", false), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1March", "21 min", "£31", "-5%", "15", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2March", "22 min", "£32", "-15%", "25", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3March", "23 min", "£33", "-35%", "35", false))
        section.serviceItem.add(serviceItem)

        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service2March", "20 min", "£30", "-25%", "25", false), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1March", "21 min", "£31", "-5%", "15", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2March", "22 min", "£32", "-15%", "25", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3March", "23 min", "£33", "-35%", "35", false))
        section.serviceItem.add(serviceItem)
        sections.add(section)



        section = Section("April Beauty Sale", ArrayList(), false)
        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service1April", "20 min", "£30", "-25%", "25", false), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1April", "21 min", "£31", "-5%", "15", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2April", "22 min", "£32", "-15%", "25", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3April", "23 min", "£33", "-35%", "35", false))
        section.serviceItem.add(serviceItem)

        serviceItem = ServiceItem(ArrayList(), ServiceModel("Service2April", "20 min", "£30", "-25%", "25", false), "details?", "Nails", false)
        serviceItem.serviceSubItem.add(ServiceModel("SubService1April", "21 min", "£31", "-5%", "15, false", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService2April", "22 min", "£32", "-15%", "25", false))
        serviceItem.serviceSubItem.add(ServiceModel("SubService3April", "23 min", "£33", "-35%", "35", false))
        section.serviceItem.add(serviceItem)
        sections.add(section)

        generateList(sections)

    }

    private fun generateList(list: List<Section>) {
        main_container.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        for (section in list) {
            main_container.addView(SectionView(this, section))
        }
    }
}