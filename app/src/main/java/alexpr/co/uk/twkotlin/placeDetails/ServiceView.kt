package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.models.ServiceItem
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.transition.Fade
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.place_details_recycler_item2.view.*


class ServiceView : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, service: ServiceItem) : super(context) {
        init(service)
    }

    private fun init(service: ServiceItem) {
        View.inflate(context, R.layout.place_details_recycler_item2, this)
        service_title.text = service.serviceModel.serviceName
        service_duration.text = service.serviceModel.duration
        service_price.text = service.serviceModel.price

        for (serviceSubItem in service.serviceSubItem) {
            sub_services_container.addView(ItemView(context, serviceSubItem))
        }

        sub_item_parent_view.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        sub_services_container.visibility = if (service.showOpen) View.VISIBLE else View.GONE
        arrow_plus.rotation = if (service.showOpen) 0f else -90f

        sub_section_title.setOnClickListener {
            service.showOpen = !service.showOpen
            if (service.showOpen) {

                //ChangeTransform() causes a weird scale down on rotation
                val objAnimator = ObjectAnimator.ofFloat(arrow_plus, "rotation", 0f).setDuration(400)
                objAnimator.setAutoCancel(true)
                objAnimator.start()

                TransitionManager
                        .beginDelayedTransition(sub_item_parent_view, Fade(Fade.MODE_IN).addTarget(sub_services_container).setStartDelay(300));
                sub_services_container.visibility = View.VISIBLE
            } else {
                val objAnimator = ObjectAnimator.ofFloat(arrow_plus, "rotation", -90f).setDuration(400)
                objAnimator.setAutoCancel(true)
                objAnimator.start()

                TransitionManager.beginDelayedTransition(sub_services_container,
                        Fade(Fade.MODE_OUT)
                                .addTarget(sub_services_container)
                                .setDuration(300));
                sub_services_container.visibility = View.GONE
            }
        }


//        this.header = findViewById(R.id.header) as TextView
//        this.description = findViewById(R.id.description) as TextView
//        this.thumbnail = findViewById(R.id.thumbnail) as ImageView
//        this.icon = findViewById(R.id.icon) as ImageView
    }
}