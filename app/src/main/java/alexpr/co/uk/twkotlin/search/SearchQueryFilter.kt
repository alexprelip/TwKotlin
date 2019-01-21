package alexpr.co.uk.twkotlin.search

import alexpr.co.uk.twkotlin.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SearchQueryFilter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_query_filter_layout)
        val queryStr = intent.extras?.getString("search_query")

        findViewById<TextView>(R.id.search_query_field).text = queryStr


    }
}