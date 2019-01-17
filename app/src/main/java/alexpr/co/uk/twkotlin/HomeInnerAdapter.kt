package alexpr.co.uk.twkotlin

import alexpr.co.uk.twkotlin.models.MenuSection
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView

class HomeInnerAdapter(val items: List<MenuSection>, val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun getItemCount(): Int {
        return items.size;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var imageButton = view.findViewById<ImageButton>(R.id.home_image_button);

    }

}