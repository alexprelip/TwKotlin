package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.models.ServiceModel
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.place_details_recycler_item3.view.*


class ItemView : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, service: ServiceModel, clickListener: (Int) -> Unit) : super(context) {
        init(service, clickListener)
    }

    private fun init(service: ServiceModel, clickListener: (Int) -> Unit) {
        View.inflate(context, R.layout.place_details_recycler_item3, this)
        sub_service_title.text = service.serviceName
        sub_service_duration.text = service.duration
        sub_service_price.text = service.price

        if (service.selected) {
            item_selected_indicator.setImageResource(R.drawable.ic_check_circle_black_24dp)
        } else {
            item_selected_indicator.setImageResource(R.drawable.ic_add_circle_outline_black_24dp)
        }

        item_view_root.setOnClickListener {
            service.selected = !service.selected
            if (service.selected) {
                item_selected_indicator.setImageResource(R.drawable.ic_check_circle_black_24dp)
            } else {
                item_selected_indicator.setImageResource(R.drawable.ic_add_circle_outline_black_24dp)
            }
            clickListener(service.serviceId)
        }


//        this.header = findViewById(R.id.header) as TextView
//        this.description = findViewById(R.id.description) as TextView
//        this.thumbnail = findViewById(R.id.thumbnail) as ImageView
//        this.icon = findViewById(R.id.icon) as ImageView
    }
}