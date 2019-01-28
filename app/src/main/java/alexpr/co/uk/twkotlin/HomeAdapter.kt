package alexpr.co.uk.twkotlin

import alexpr.co.uk.twkotlin.models.MenuSection
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_recycler_item.view.*


open class HomeAdapter(val items: List<MenuSection>, val context: Context, val clickListener: (String, MenuSection) -> Unit, val sectionClickListener: (Int) -> Unit) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal enum class Payload {
        FAVORITE_CHANGE
    }

    private var mExpandedPosition: Int = -1
    private var previousExpandedPosition: Int = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context);
        val view = inflater.inflate(R.layout.home_recycler_item, parent, false);
        return ViewHolder(view);
    }

    override fun getItemCount() = items.size;

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {

        if (!payloads.isEmpty()) {
            when (payloads[0]) {
                Payload.FAVORITE_CHANGE -> {
                }
            }
        }
        (holder as ViewHolder).bind(items.get(position), clickListener, sectionClickListener);
        super.onBindViewHolder(holder, position, payloads)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(menuSection: MenuSection, clickListener: (String, MenuSection) -> Unit, sectionClickListener: (Int) -> Unit) {
            Picasso.get().load(menuSection.imageUri)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .fit()
                    .centerCrop()
                    .into(view.home_image_button)
            view.home_section_title.text = menuSection.title;

            val isExpanded = adapterPosition == mExpandedPosition
            view.home_inner_recycler.setVisibility(if (isExpanded) View.VISIBLE else View.GONE)

            if (isExpanded) previousExpandedPosition = adapterPosition

            view.home_image_button.setOnClickListener {
                mExpandedPosition = if (isExpanded) -1 else adapterPosition
                notifyItemChanged(previousExpandedPosition, Payload.FAVORITE_CHANGE)
                notifyItemChanged(mExpandedPosition, Payload.FAVORITE_CHANGE)
                sectionClickListener(adapterPosition)
            }

            view.home_inner_recycler.layoutManager = object : LinearLayoutManager(view.context) {
                override fun canScrollVertically() = false
            }
            view.home_inner_recycler.adapter = HomeInnerAdapter(menuSection, view.context, clickListener)
        }
    }
}


