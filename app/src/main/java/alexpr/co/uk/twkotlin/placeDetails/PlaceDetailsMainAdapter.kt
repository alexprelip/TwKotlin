package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.models.Section
import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.place_details_recycler_item.view.*

class PlaceDetailsMainAdapter(val context: Context, val sectionList: List<Section>, val clickListener: (Int, Int, Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context);
        val view = inflater.inflate(R.layout.place_details_recycler_item, parent, false);
        return ViewHolder(view);
    }

    override fun getItemCount(): Int = sectionList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(sectionList.get(position))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            (holder as ViewHolder).bind(sectionList.get(position))
        } else {
            (holder as ViewHolder).bindWithPayload(sectionList.get(position), payloads[0] as PlaceDetailsActivity.ItemChangedPayload)
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindWithPayload(section: Section, payload: PlaceDetailsActivity.ItemChangedPayload) {
            if (view.place_details_second_recycler.adapter == null) {
                Log.e("alexp", "null adapter!!!")
            } else {
                view.place_details_second_recycler.adapter?.notifyItemChanged(payload.service, payload)
                if (section.showOpen) {
                    view.place_details_second_recycler.visibility = View.VISIBLE
                    val objAnimator = ObjectAnimator.ofFloat(view.services_collapse, "rotation", 0f).setDuration(400)
                    objAnimator.setAutoCancel(true)
                    objAnimator.start()
                } else {
                    val objAnimator = ObjectAnimator.ofFloat(view.services_collapse, "rotation", -90f).setDuration(400)
                    objAnimator.setAutoCancel(true)
                    objAnimator.start()
                    view.place_details_second_recycler.visibility = View.GONE
                }

                if (countSelected(section) > 0) {
                    view.selected_service_count.visibility = View.VISIBLE
                    view.selected_service_count.text = "${countSelected(section)} added"
                } else {
                    view.selected_service_count.visibility = View.GONE
                }

            }
        }

        fun bind(section: Section) {
            view.section_title.text = section.name
            if (view.place_details_second_recycler.adapter == null) {
                view.place_details_second_recycler.isNestedScrollingEnabled = false
                view.place_details_second_recycler.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean {
                        return false
                    }
                }
                view.place_details_second_recycler.adapter = PlaceDetailsServiceAdapter(context, section.serviceItem, adapterPosition, clickListener)
                view.place_details_second_recycler.itemAnimator = null
            }

            if (section.showOpen) {
                view.place_details_second_recycler.visibility = View.VISIBLE
                view.services_collapse.rotation = 0f
            } else {
                view.place_details_second_recycler.visibility = View.GONE
                view.services_collapse.rotation = -90f
            }

            view.asdf.setOnClickListener { clickListener(adapterPosition, -1, -1) }
        }
    }

    private fun countSelected(section: Section): Int {
        var selected = 0
        for (service in section.serviceItem) {
            for (subService in service.serviceSubItem) {
                if (subService.selected) {
                    selected++
                }
            }
        }
        return selected
    }
}