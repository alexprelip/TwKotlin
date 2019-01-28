package alexpr.co.uk.twkotlin.search

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.models.MenuSection
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SearchQueryFilter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_query_filter_layout)
        val queryStr = intent.extras?.getString("search_query")

        //TODO populate the recycler view with the items from the section
        val menuSection: MenuSection = intent.extras?.getSerializable("section") as MenuSection

        findViewById<TextView>(R.id.search_query_field).text = queryStr


    }
}