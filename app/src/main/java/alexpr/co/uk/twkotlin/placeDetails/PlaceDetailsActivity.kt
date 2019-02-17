package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.TwApplication
import alexpr.co.uk.twkotlin.models.Section
import android.animation.ArgbEvaluator
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.place_details_activity.*


class PlaceDetailsActivity : AppCompatActivity() {
    private lateinit var sections: List<Section>

    private var mBottomSheetBehavior: BottomSheetBehavior<View>? = null
    private val viewModel: PlaceDetailsViewModel = PlaceDetailsViewModel(TwApplication.twService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.place_details_activity)

        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById<View>(R.id.bottom_bar))
        bottom_bar_layout.setOnClickListener { }
        val viewPager: ViewPager = findViewById(R.id.place_details_viepager)
        viewPager.adapter = ImagePagerAdapter(listOf("https://upload.wikimedia.org/wikipedia/commons/a/a1/Khajuraho-landscape.jpg", "https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg"))

        val anim = ObjectAnimator.ofObject(findViewById<ImageView>(R.id.toolbar_back), "colorFilter", ArgbEvaluator(), Color.parseColor("#FFFFFFFF"), Color.parseColor("#FFFF0000"))
        anim.duration = 1000

        viewModel.placeDetails.observe(this, Observer { placeDetails ->
            place_name.text = placeDetails.name
            place_address.text = placeDetails.address
            place_rating_number.text = placeDetails.rating.toString()
            place_rating_bar.rating = placeDetails.rating.toFloat()
            place_review_count.text = getString(R.string.review_count_label, placeDetails.reviewCount)
        })

        viewModel.placeServices.observe(this, Observer { sections ->
            this.sections = sections
            place_details_main_recycler.adapter = PlaceDetailsMainAdapter(this, this.sections, { section: Int, service: Int, subService: Int -> itemClicked(section, service, subService) })
        })

        viewModel.getPlaceDetails(intent.getStringExtra("place_name"))
        viewModel.getPlaceServices(intent.getStringExtra("place_name"))

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

        nested_scroll_view.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        nested_scroll_view.layoutTransition.setStartDelay(LayoutTransition.CHANGING, 600)

        place_details_main_recycler.isNestedScrollingEnabled = false
        place_details_main_recycler.itemAnimator = null
        place_details_main_recycler.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        place_details_main_recycler.adapter = PlaceDetailsMainAdapter(this, ArrayList(), { section: Int, service: Int, subService: Int -> itemClicked(section, service, subService) })
    }

    private fun itemClicked(section: Int, service: Int, subService: Int) {
        if (subService > -1) {
            val subServiceItem = sections.get(section).serviceItem.get(service).serviceSubItem[subService]
            val serviceItem = sections.get(section).serviceItem.get(service)

            val itemInitialState = subServiceItem.selected
            if (serviceItem.isSingleOption) {
                for (srv in serviceItem.serviceSubItem) {
                    srv.selected = false
                }
            }
            subServiceItem.selected = !itemInitialState
        } else if (service > -1) {
            TransitionManager.beginDelayedTransition(nested_scroll_view)
            sections.get(section).serviceItem.get(service).showOpen = !sections.get(section).serviceItem.get(service).showOpen
        } else {
            TransitionManager.beginDelayedTransition(nested_scroll_view)
            sections.get(section).showOpen = !sections.get(section).showOpen
        }

        place_details_main_recycler.adapter?.notifyItemChanged(section, ItemChangedPayload(section, service, subService))
        handleItemClick(-1)
    }

    private fun handleItemClick(serviceId: Int) {
        var count = 0
        for (section in sections) {
            for (service in section.serviceItem) {
                for (subService in service.serviceSubItem) {
                    if (subService.selected) {
                        count++
                    }
                }
            }
        }

        if (count > 0) {
            mBottomSheetBehavior?.setState(BottomSheetBehavior.STATE_EXPANDED)
        } else {
            mBottomSheetBehavior?.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }
        selected_item_count.text = count.toString()
    }

    data class ItemChangedPayload(val section: Int, val service: Int, val subService: Int)
}