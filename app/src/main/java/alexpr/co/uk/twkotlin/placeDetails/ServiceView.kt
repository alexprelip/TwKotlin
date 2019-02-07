package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.models.ServiceItem
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.place_details_recycler_item2.view.*


class ServiceView : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, service: ServiceItem) : super(context) {
        init(service)
    }

    private fun init(service:ServiceItem) {
        View.inflate(context, R.layout.place_details_recycler_item2, this)
        service_title.text = service.serviceModel.serviceName
        service_duration.text = service.serviceModel.duration
        service_price.text = service.serviceModel.price

        


//        this.header = findViewById(R.id.header) as TextView
//        this.description = findViewById(R.id.description) as TextView
//        this.thumbnail = findViewById(R.id.thumbnail) as ImageView
//        this.icon = findViewById(R.id.icon) as ImageView
    }
}