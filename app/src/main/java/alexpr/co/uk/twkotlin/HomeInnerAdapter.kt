package alexpr.co.uk.twkotlin

import alexpr.co.uk.twkotlin.models.MenuItem
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_inner_recycler_item.view.*

class HomeInnerAdapter(val items: MutableList<MenuItem>, val context: Context, val clickListener: (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context);
        val view = inflater.inflate(R.layout.home_inner_recycler_item, parent, false);
        return HomeInnerAdapter.ViewHolder(view);
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items.get(position), clickListener);
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(menuItem: MenuItem, clickListener: (String) -> Unit) {
            view.home_inner_item.text = menuItem.displayName;
            view.setOnClickListener { clickListener(menuItem.query) }
        }
    }

}