package alexpr.co.uk.twkotlin.search

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.models.PlaceModel
import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.place_list_layout.view.*


class SearchResultAdapter(val items: List<PlaceModel>, val context: Context, val clickListener: (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context);
        val view = inflater.inflate(R.layout.place_list_layout, parent, false);
        return ViewHolder(view);
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items.get(position), clickListener);
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(place: PlaceModel, clickListener: (String) -> Unit) {

            Picasso.get().load(place.imageUrl)
                    .fit()
                    .centerCrop()
                    .into(view.place_image)

            view.place_name.text = place.name
            view.place_rating_number.text = place.rating.toString()
            view.place_rating_bar.rating = place.rating.toFloat()
            view.place_review_count.text = place.reviewCount.toString()
            view.place_address.text = place.address

            //although this way is shorter, it will break refactoring
            for (i in 0 until place.serviceHighlight.size) {
                val highlightTitleId = view.context.resources.getIdentifier("place_highlight_title_$i", "id", view.context.packageName)
                val highlightPriceId = view.context.resources.getIdentifier("place_highlight_price_$i", "id", view.context.packageName)
                val highlightDurationId = view.context.resources.getIdentifier("place_highlight_duration_$i", "id", view.context.packageName)
                val highlightExtraInfoId = view.context.resources.getIdentifier("place_highlight_extra_info_$i", "id", view.context.packageName)

                view.findViewById<TextView>(highlightTitleId).text = place.serviceHighlight.get(i).serviceName
                view.findViewById<TextView>(highlightPriceId).text = place.serviceHighlight.get(i).price
                view.findViewById<TextView>(highlightDurationId).text = place.serviceHighlight.get(i).duration
                styleText(place.serviceHighlight.get(i).discount, place.serviceHighlight.get(i).oldPrice, view.findViewById(highlightExtraInfoId))
            }
//            if (place.serviceHighlight.size>0) {
//                view.place_highlight_title_0.text = place.serviceHighlight.get(0).serviceName
//                view.place_highlight_price_0.text = place.serviceHighlight.get(0).price
//                view.place_highlight_duration_0.text = place.serviceHighlight.get(0).duration
//                styleText(place.serviceHighlight.get(0).discount, place.serviceHighlight.get(0).oldPrice, view.place_highlight_extra_info_0)
//            }
//
//            if (place.serviceHighlight.size>1) {
//                view.place_highlight_title_1.text = place.serviceHighlight.get(1).serviceName
//                view.place_highlight_price_1.text = place.serviceHighlight.get(1).price
//                view.place_highlight_duration_1.text = place.serviceHighlight.get(1).duration
//                styleText(place.serviceHighlight.get(1).discount, place.serviceHighlight.get(1).oldPrice, view.place_highlight_extra_info_1)
//            }
//
//            if (place.serviceHighlight.size>2) {
//                view.place_highlight_title_2.text = place.serviceHighlight.get(2).serviceName
//                view.place_highlight_price_2.text = place.serviceHighlight.get(2).price
//                view.place_highlight_duration_2.text = place.serviceHighlight.get(2).duration
//                styleText(place.serviceHighlight.get(2).discount, place.serviceHighlight.get(2).oldPrice, view.place_highlight_extra_info_2)
//            }

            view.setOnClickListener { clickListener(place.name) }
        }

        /**
         * Style and display a string differently, based on whether it is empty or not and with the priority determined by the parameter order
         */
        private fun styleText(str1: String, str2: String, tv: TextView) {
            var spannable = SpannableString("")
            if (str1.isNotEmpty()) {
                spannable = SpannableString(str1)
                spannable.setSpan(ForegroundColorSpan(Color.parseColor("#12FFCC")), 0, str1.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            } else if (str2.isNotEmpty()) {
                spannable = SpannableString(str2)
                spannable.setSpan(StrikethroughSpan(), 0, str2.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                spannable.setSpan(ForegroundColorSpan(Color.parseColor("#AAAAAA")), 0, str2.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            }
            tv.text = spannable
        }
    }

}