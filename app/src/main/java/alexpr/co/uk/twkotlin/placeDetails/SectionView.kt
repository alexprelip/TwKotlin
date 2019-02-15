package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.models.Section
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.transition.Fade
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.place_details_recycler_item.view.*


class SectionView : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, section: Section, clickListener: (Int) -> Unit) : super(context) {
        init(section, clickListener)
    }

    private fun init(section: Section, clickListener: (Int) -> Unit) {
        View.inflate(context, R.layout.place_details_recycler_item, this)
        section_title.text = section.name

        services_container.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        services_container.visibility = if (section.showOpen) View.VISIBLE else View.GONE
        services_collapse.rotation = if (section.showOpen) 0f else -90f

        asdf.setOnClickListener {
            section.showOpen = !section.showOpen
            if (section.showOpen) {
                //ChangeTransform causes a weird scale down on rotation
                val objAnimator = ObjectAnimator.ofFloat(services_collapse, "rotation", 0f).setDuration(400)
                objAnimator.setAutoCancel(true)
                objAnimator.start()

                (parent as ViewGroup).layoutTransition.setStartDelay(LayoutTransition.CHANGING, 0)

                TransitionManager.beginDelayedTransition(item_parent_view, Fade(Fade.MODE_IN).addTarget(services_container).setStartDelay(300));
                services_container.visibility = View.VISIBLE
            } else {
                val objAnimator = ObjectAnimator.ofFloat(services_collapse, "rotation", -90f).setDuration(400)
                objAnimator.setAutoCancel(true)
                objAnimator.start()

                (parent as ViewGroup).layoutTransition.setStartDelay(LayoutTransition.CHANGING, 300)
                TransitionManager.beginDelayedTransition(item_parent_view, Fade(Fade.MODE_OUT).addTarget(services_container));
                services_container.visibility = View.GONE
            }
        }

        for (item in section.serviceItem) {
            services_container.addView(ServiceView(context, item, clickListener))
        }


//        this.header = findViewById(R.id.header) as TextView
//        this.description = findViewById(R.id.description) as TextView
//        this.thumbnail = findViewById(R.id.thumbnail) as ImageView
//        this.icon = findViewById(R.id.icon) as ImageView
    }
}