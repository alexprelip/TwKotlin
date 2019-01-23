package alexpr.co.uk.twkotlin.search

import alexpr.co.uk.twkotlin.CustomLinearLayoutManager
import alexpr.co.uk.twkotlin.HomeAdapter
import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.models.MenuItem
import alexpr.co.uk.twkotlin.models.MenuSection
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_result_activity.*

class SearchResult : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    val LOCATION_REQ_CODE = 1234
    val DATE_TIME_REQ_CODE = 5678

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_result_activity)
        //TODO add the layout behaviour to the location and date filters

        val queryStr = intent.extras?.getString("search_query")
        Log.e("alepx", "queryString: " + queryStr)
        findViewById<View>(R.id.search_query_field).setOnClickListener {
            startActivity(Intent(this, SearchQueryFilter::class.java).putExtra("search_query", queryStr)) }
        findViewById<View>(R.id.search_location_field).setOnClickListener {
            val location = findViewById<AppCompatTextView>(R.id.search_location_field).text
            startActivityForResult(Intent(this, LocationFilter::class.java).putExtra("search_query", location), LOCATION_REQ_CODE) }

        findViewById<View>(R.id.search_date_time_field).setOnClickListener {
            val location = findViewById<AppCompatTextView>(R.id.search_date_time_field).text
            startActivityForResult(Intent(this, DateTimeFilter::class.java).putExtra("search_query", location), DATE_TIME_REQ_CODE) }

        recyclerView = findViewById<RecyclerView>(R.id.search_result_recycler);
        recyclerView?.layoutManager = CustomLinearLayoutManager(this);
        val list = java.util.ArrayList<MenuSection>(2);

        val menuSection = MenuSection(Uri.parse("https://upload.wikimedia.org/wikipedia/commons/a/a1/Khajuraho-landscape.jpg"), "Hair Removal", ArrayList<MenuItem>(0))
        menuSection.menuItems.add(MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"))
        menuSection.menuItems.add(MenuItem("Bikini Waxing", "Bikini Waxing QUERY"))
        menuSection.menuItems.add(MenuItem("Hollywood Waxing", "Hollywood Waxing QUERY"))
        menuSection.menuItems.add(MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"))
        menuSection.menuItems.add(MenuItem("Bikini Waxing", "Bikini Waxing QUERY"))
        menuSection.menuItems.add(MenuItem("Hollywood Waxing", "Hollywood Waxing QUERY"))
        list.add(menuSection)

        list.add(MenuSection(Uri.parse("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg"), "Nails", ArrayList<MenuItem>(0)))
        list.get(list.size - 1).menuItems.add(MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"))
        list.get(list.size - 1).menuItems.add(MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"))
        list.add(MenuSection(Uri.parse("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg"), "Nails", ArrayList<MenuItem>(0)))
        list.get(list.size - 1).menuItems.add(MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"))
        list.get(list.size - 1).menuItems.add(MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"))
        list.add(MenuSection(Uri.parse("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg"), "Nails", ArrayList<MenuItem>(0)))
        list.get(list.size - 1).menuItems.add(MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"))
        list.get(list.size - 1).menuItems.add(MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"))
        list.add(MenuSection(Uri.parse("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg"), "Nails", ArrayList<MenuItem>(0)))
        list.get(list.size - 1).menuItems.add(MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"))
        list.get(list.size - 1).menuItems.add(MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"))
        list.add(MenuSection(Uri.parse("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg"), "Nails", ArrayList<MenuItem>(0)))
        list.add(MenuSection(Uri.parse("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg"), "Nails", ArrayList<MenuItem>(0)))
        recyclerView?.adapter = HomeAdapter(list, this, { str: String -> handleItemClick(str) }, { int: Int -> handleSectionClick(int) });

    }

    fun handleItemClick(str: String) {
        Log.e("alexp", "sub section clicked $str");
        startActivity(Intent(this, SearchResult::class.java).putExtra("search_query", str))
    }

    fun handleSectionClick(int: Int) {
        recyclerView?.smoothScrollToPosition(int)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == LOCATION_REQ_CODE && resultCode == Activity.RESULT_OK) search_location_field.setText(data?.getStringExtra("search_query"))
        super.onActivityResult(requestCode, resultCode, data)
    }
}