package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.models.Section
import android.animation.*
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import androidx.transition.ChangeTransform
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import kotlinx.android.synthetic.main.place_details_recycler_item.view.*




class SectionView : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, section: Section) : super(context) {
        init(section)
    }

    private var childHeight: Int = 0

    private fun init(section: Section) {
        View.inflate(context, R.layout.place_details_recycler_item, this)
        section_title.text = section.name
        setBackgroundColor(Color.WHITE)
        item_parent_view.pivotY = 0f
//        item_parent_view.layoutTransition = getDefaultTransition()
        childHeight = services_container.measuredHeight

        var animShow: ValueAnimator?  =null
//        val animShow = ValueAnimator.ofInt( services_container.height, 400)
//        animShow.addUpdateListener { valueAnimator ->
//            val `val` = valueAnimator.animatedValue as Int
//            val layoutParams = services_container.getLayoutParams()
//            layoutParams.height = `val`
//            services_container.setLayoutParams(layoutParams)
//        }
//        animShow.duration = 3000
        var animHide:ValueAnimator? = null
//        val animHide = ValueAnimator.ofInt( services_container.height, 0)
//        animHide.addUpdateListener { valueAnimator ->
//            val `val` = valueAnimator.animatedValue as Int
//            val layoutParams = services_container.getLayoutParams()
//            layoutParams.height = `val`
//            services_container.setLayoutParams(layoutParams)
//        }
//        animHide.duration = 3000




        val makeVis = ObjectAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f))
        makeVis.duration = 1000
        makeVis.setTarget(services_container)
        makeVis.reverse()


        val animSet = AnimatorSet()
        animSet.duration = 600
        animSet.setTarget(services_container)
//        animSet.playSequentially(animShow, makeVis)
        animSet.interpolator = AccelerateDecelerateInterpolator()
//        anim.start()


//        val layoutTransition = item_parent_view.layoutTransition
////        layoutTransition.enableTransitionType(LayoutTransition.APPEARING);
////        layoutTransition.enableTransitionType(LayoutTransition.DISAPPEARING);
////        layoutTransition.enableTransitionType(LayoutTransition.CHANGE_APPEARING);
////        layoutTransition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
//        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
//        layoutTransition.setAnimator(LayoutTransition.CHANGING, AnimatorInflater.loadAnimator(context, R.animator.animatorr))
//        layoutTransition.setDuration(1000)

        var showHide = false
        services_collapse.setOnClickListener {
            //            if (services_container.childCount == 0) {
//                for (item in section.serviceItem) {
//                    TransitionManager.beginDelayedTransition(item_parent_view);
//                    services_container.addView(ServiceView(context, item))
//                }
//            } else {
//                services_container.removeAllViews()
//            }
//            startViewTransition(services_collapse)

//            TransitionManager.beginDelayedTransition(item_parent_view);
//            findViewById<View>(R.id.services_container).visibility =
//                    if (findViewById<View>(R.id.services_container).visibility == View.VISIBLE) View.GONE else View.VISIBLE

            val mStaggeredTransition = TransitionSet()
            val first = Fade()
//            first.addTarget(services_container)
//            first.duration = 3000

            mStaggeredTransition.addTransition(ChangeTransform()).addTransition(first)
            mStaggeredTransition.duration = 1000
            TransitionManager.beginDelayedTransition(item_parent_view, mStaggeredTransition);
            val layoutParam = services_container.layoutParams
            if (showHide) {

                findViewById<View>(R.id.services_container).visibility = View.GONE

//                layoutParam.height = 0;
//                services_container.layoutParams = layoutParam

//                findViewById<View>(R.id.services_container).alpha = 1f
//                animSet.cancel()
//                animSet.playSequentially(anim, makeVis)
//                animSet.start()
//                if (animShow.isRunning) {
//                    animShow.pause()
//                    animShow.resume()
//                } else{

//                animShow.start()
//                animShow.currentPlayTime = animHide.currentPlayTime
//                animHide.cancel()
//                }


//
//                animHide?.cancel()
//                animShow = ValueAnimator.ofInt( services_container.height, childHeight)
//                animShow!!.addUpdateListener { valueAnimator ->
//                    val `val` = valueAnimator.animatedValue as Int
//                    val layoutParams = services_container.getLayoutParams()
//                    layoutParams.height = `val`
//                    services_container.setLayoutParams(layoutParams)
//                }
//                animShow!!.duration = 3000
//                animShow!!.start()
            } else {
//                animSet.cancel()
//                animSet.reverse()
//                animHide.start()
//                animHide.currentPlayTime = animShow.currentPlayTime
//                animShow.cancel()

//
//                animShow?.cancel()
//                animHide = ValueAnimator.ofInt( services_container.height, 0)
//                animHide!!.addUpdateListener { valueAnimator ->
//                    val `val` = valueAnimator.animatedValue as Int
//                    val layoutParams = services_container.getLayoutParams()
//                    layoutParams.height = `val`
//                    services_container.setLayoutParams(layoutParams)
//                }
//                animHide!!.duration = 3000
//                animHide!!.start()



//                val layoutParams = services_container.getLayoutParams()
//                layoutParams.height = 0
//                services_container.setLayoutParams(layoutParams)
                findViewById<View>(R.id.services_container).visibility = View.VISIBLE
//                findViewById<View>(R.id.services_container).alpha = 0f

//                layoutParam.height = 600
//                services_container.layoutParams = layoutParam
            }

            showHide = !showHide
        }

        for (item in section.serviceItem) {

            services_container.addView(ServiceView(context, item))
        }


//        this.header = findViewById(R.id.header) as TextView
//        this.description = findViewById(R.id.description) as TextView
//        this.thumbnail = findViewById(R.id.thumbnail) as ImageView
//        this.icon = findViewById(R.id.icon) as ImageView
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (childHeight == 0) childHeight = services_container.height
    }

    private fun getDefaultTransition(): LayoutTransition {
        val itemLayoutTransition = LayoutTransition()
        itemLayoutTransition.setStartDelay(LayoutTransition.APPEARING, 0)
        itemLayoutTransition.setStartDelay(LayoutTransition.DISAPPEARING, 0)
        itemLayoutTransition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 0)
        itemLayoutTransition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0)
//        itemLayoutTransition.setStartDelay(LayoutTransition.CHANGING, 0)
        itemLayoutTransition.setDuration(1000)
//        itemLayoutTransition.setInterpolator(LayoutTransition.CHANGING, OvershootInterpolator(2f))
        val scaleUp = ObjectAnimator.ofPropertyValuesHolder(null as Any?, PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 1f), PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f))
        val makeVis = ObjectAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f))
        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(scaleUp.setDuration(1500), makeVis.setDuration(1500))

        scaleUp.duration = 1000
        scaleUp.startDelay = 50
        val scaleDown = ObjectAnimator.ofPropertyValuesHolder(null as Any?, PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 1f), PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 0f))
        scaleDown.duration = 1000
        itemLayoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, scaleUp)
        itemLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, scaleDown)
//        itemLayoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        itemLayoutTransition.setDuration(1000)
        itemLayoutTransition.addTransitionListener(object : LayoutTransition.TransitionListener {
            override fun startTransition(transition: LayoutTransition?, container: ViewGroup?, view: View?, transitionType: Int) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.e("alexp", "startTransition: ${transition}, type: $transitionType")
                Log.e("alexp", "container: ${container?.findViewById<TextView>(R.id.service_title)?.text}")
            }

            override fun endTransition(transition: LayoutTransition?, container: ViewGroup?, view: View?, transitionType: Int) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                endViewTransition(services_container)
            }

        })
        return itemLayoutTransition
    }
}