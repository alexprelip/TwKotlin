package alexpr.co.uk.twkotlin

import alexpr.co.uk.twkotlin.models.MenuSection
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_recycler_item.view.*

class HomeAdapter(val items: List<MenuSection>, val context: Context, val clickListener: (String) -> Unit) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context);
        val view = inflater.inflate(R.layout.home_recycler_item, parent, false);
        return ViewHolder(view);
    }

    override fun getItemCount() = items.size;

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items.get(position), clickListener);
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(menuSection: MenuSection, clickListener: (String) -> Unit) {
            Picasso.get().load(menuSection.imageUri)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .fit()
                    .centerCrop()
                    .into(view.home_image_button)
            view.home_section_title.text = menuSection.title;
        }
    }

}