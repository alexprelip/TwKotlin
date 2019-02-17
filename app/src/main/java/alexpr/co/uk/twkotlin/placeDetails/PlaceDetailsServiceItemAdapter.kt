package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.models.ServiceModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.place_details_recycler_item3.view.*

class PlaceDetailsServiceItemAdapter(val context: Context, val serviceModelList: MutableList<ServiceModel>, val section: Int, val service: Int, val clickListener: (Int, Int, Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context);
        val view = inflater.inflate(R.layout.place_details_recycler_item3, parent, false);
        return ViewHolder(view);
    }

    override fun getItemCount(): Int = serviceModelList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(serviceModelList.get(position))
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(serviceModel: ServiceModel) {
            view.sub_service_title.text = serviceModel.serviceName
            view.sub_service_duration.text = serviceModel.duration
            view.sub_service_price.text = serviceModel.price

            if (serviceModel.selected) {
                view.item_selected_indicator.setImageResource(R.drawable.ic_check_circle_black_24dp)
            } else {
                view.item_selected_indicator.setImageResource(R.drawable.ic_add_circle_outline_black_24dp)
            }

            view.item_view_root.setOnClickListener {
                clickListener(section, service, adapterPosition)
            }
        }
    }
}