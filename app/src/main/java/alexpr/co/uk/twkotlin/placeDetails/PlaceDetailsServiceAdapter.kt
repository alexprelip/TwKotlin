package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.models.ServiceItem
import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.place_details_recycler_item2.view.*

class PlaceDetailsServiceAdapter(
        val context: Context,
        val serviceList: List<ServiceItem>,
        val section: Int,
        val clickListener: (Int, Int, Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context);
        val view = inflater.inflate(R.layout.place_details_recycler_item2, parent, false);
        return ViewHolder(view);
    }

    override fun getItemCount(): Int = serviceList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(serviceList.get(position))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            (holder as ViewHolder).bind(serviceList.get(position))
        } else {
            (holder as ViewHolder).bindWithPayload(serviceList.get(position), payloads[0] as PlaceDetailsActivity.ItemChangedPayload)
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindWithPayload(serviceItem: ServiceItem, payload: PlaceDetailsActivity.ItemChangedPayload) {
            if (view.place_details_third_recycler.adapter == null) {
                Log.e("alexp", "null adapter!!!")
            } else {

                if (serviceItem.isSingleOption) {
                    view.place_details_third_recycler.adapter?.notifyItemRangeChanged(0, serviceItem.serviceSubItem.size, Any())
                } else {
                    view.place_details_third_recycler.adapter?.notifyItemChanged(payload.subService, Any())
                }

                if (serviceItem.showOpen) {
                    view.place_details_third_recycler.visibility = View.VISIBLE
                    val objAnimator = ObjectAnimator.ofFloat(view.arrow_plus, "rotation", 0f).setDuration(400)
                    objAnimator.setAutoCancel(true)
                    objAnimator.start()
                } else {
                    view.place_details_third_recycler.visibility = View.GONE
                    val objAnimator = ObjectAnimator.ofFloat(view.arrow_plus, "rotation", -90f).setDuration(400)
                    objAnimator.setAutoCancel(true)
                    objAnimator.start()
                }

                view.sub_section_title.isClickable = isCollapsible(serviceItem)
                if (isCollapsible(serviceItem)) {
                    view.arrow_plus.visibility = View.VISIBLE
                } else {
                    view.arrow_plus.visibility = View.INVISIBLE
                }
            }
        }

        fun bind(service: ServiceItem) {
            view.service_title.text = service.serviceModel.serviceName

            if (service.showOpen) {
                view.place_details_third_recycler.visibility = View.VISIBLE
                view.arrow_plus.rotation = 0f
            } else {
                view.place_details_third_recycler.visibility = View.GONE
                view.arrow_plus.rotation = -90f
            }

            view.place_details_third_recycler.isNestedScrollingEnabled = false
            view.place_details_third_recycler.layoutManager = object : LinearLayoutManager(context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            view.place_details_third_recycler.adapter = PlaceDetailsServiceItemAdapter(context, service.serviceSubItem, section, adapterPosition, clickListener)
            view.sub_section_title.setOnClickListener { clickListener(section, adapterPosition, -1) }

            view.sub_section_title.isClickable = isCollapsible(service)
            if (isCollapsible(service)) {
                view.arrow_plus.visibility = View.VISIBLE
            } else {
                view.arrow_plus.visibility = View.INVISIBLE
            }
        }
    }

    private fun isCollapsible(serviceItem: ServiceItem): Boolean {
        for (subService in serviceItem.serviceSubItem) {
            if (subService.selected) {
                return false
            }
        }
        return true
    }
}