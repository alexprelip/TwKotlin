package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.R
import android.animation.ArgbEvaluator
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
    }
}