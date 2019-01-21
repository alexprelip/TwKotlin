package alexpr.co.uk.twkotlin.search

import alexpr.co.uk.twkotlin.R
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LocationFilter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_filter_layout)
        val queryStr = intent.extras?.getString("search_query")

        findViewById<TextView>(R.id.location_query_field).setText(queryStr)

        val locationRecycler = findViewById<RecyclerView>(R.id.location_recycler)

        val locationList = listOf("SW1", "NW2", "AB1 2CD")
        locationRecycler.adapter = LocationAdapter(locationList, this, { str: String -> locationPicked(str) })
        locationRecycler.layoutManager = LinearLayoutManager(this)

    }

    fun locationPicked(location: String) {
        Log.e("alexp", "pickedLocation: $location")
        setResult(Activity.RESULT_OK, intent.putExtra("search_query", location))
        finish()
    }
}