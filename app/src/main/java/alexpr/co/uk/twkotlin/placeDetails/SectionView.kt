package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.models.Section
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.place_details_recycler_item.view.*




class SectionView : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, section: Section) : super(context) {
        init(section)
    }

    private fun init(section: Section) {
        View.inflate(context, R.layout.place_details_recycler_item, this)
        section_title.text = section.name

        item_parent_view.layoutTransition = getDefaultTransition()


//        val layoutTransition = item_parent_view.layoutTransition
////        layoutTransition.enableTransitionType(LayoutTransition.APPEARING);
////        layoutTransition.enableTransitionType(LayoutTransition.DISAPPEARING);
////        layoutTransition.enableTransitionType(LayoutTransition.CHANGE_APPEARING);
////        layoutTransition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
//        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
//        layoutTransition.setAnimator(LayoutTransition.CHANGING, AnimatorInflater.loadAnimator(context, R.animator.animatorr))
//        layoutTransition.setDuration(1000)



        services_collapse.setOnClickListener {
            findViewById<View>(R.id.services_container).visibility =
                    if (findViewById<View>(R.id.services_container).visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        for (item in section.serviceItem) {
//            TransitionManager.beginDelayedTransition(item_parent_view);
            services_container.addView(ServiceView(context, item))
        }


//        this.header = findViewById(R.id.header) as TextView
//        this.description = findViewById(R.id.description) as TextView
//        this.thumbnail = findViewById(R.id.thumbnail) as ImageView
//        this.icon = findViewById(R.id.icon) as ImageView
    }

    private fun getDefaultTransition(): LayoutTransition {
        val itemLayoutTransition = LayoutTransition()
        itemLayoutTransition.setStartDelay(LayoutTransition.APPEARING, 0)
        itemLayoutTransition.setStartDelay(LayoutTransition.DISAPPEARING, 0)
        itemLayoutTransition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 500)
        itemLayoutTransition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0)
//        itemLayoutTransition.setStartDelay(LayoutTransition.CHANGING, 0)
        itemLayoutTransition.setDuration(1000)
//        itemLayoutTransition.setInterpolator(LayoutTransition.CHANGING, OvershootInterpolator(2f))
        val scaleUp = ObjectAnimator.ofPropertyValuesHolder(null as Any?, PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 1f), PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f))
        scaleUp.duration = 1000
        scaleUp.startDelay = 50
        val scaleDown = ObjectAnimator.ofPropertyValuesHolder(null as Any?, PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 1f), PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 0f))
        scaleDown.duration = 1000
        itemLayoutTransition.setAnimator(LayoutTransition.APPEARING, scaleUp)
        itemLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, scaleDown)
//        itemLayoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        itemLayoutTransition.setDuration(1000)
        itemLayoutTransition.addTransitionListener(object:LayoutTransition.TransitionListener{
            override fun startTransition(transition: LayoutTransition?, container: ViewGroup?, view: View?, transitionType: Int) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.e("alexp", "startTransition: ${transition}, type: $transitionType")
                Log.e("alexp", "container: ${container?.findViewById<TextView>(R.id.service_title)?.text}")
            }

            override fun endTransition(transition: LayoutTransition?, container: ViewGroup?, view: View?, transitionType: Int) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        return itemLayoutTransition
    }
}