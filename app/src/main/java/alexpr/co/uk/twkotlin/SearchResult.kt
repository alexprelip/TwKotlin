package alexpr.co.uk.twkotlin

import alexpr.co.uk.twkotlin.models.MenuItem
import alexpr.co.uk.twkotlin.models.MenuSection
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class SearchResult : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_result_activity)
        //TODO add the layout behaviour to the location and date filters
        intent.extras?.getString("search_query")

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
}