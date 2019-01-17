package alexpr.co.uk.twkotlin

import alexpr.co.uk.twkotlin.models.MenuItem
import alexpr.co.uk.twkotlin.models.MenuSection
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainAct : AppCompatActivity() {

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.main_act)

        val recyclerView = findViewById<RecyclerView>(R.id.home_recycler);
        recyclerView.layoutManager = LinearLayoutManager(this);
        val list = java.util.ArrayList<MenuSection>(2);
        list.add(MenuSection(Uri.parse("https://upload.wikimedia.org/wikipedia/commons/a/a1/Khajuraho-landscape.jpg"), "Hair Removal", ArrayList<MenuItem>(0)))
        list.add(MenuSection(Uri.parse("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg"), "Nails", ArrayList<MenuItem>(0)))
        recyclerView.adapter = HomeAdapter(list, this, { str: String -> handleClick(str) });

    }

    fun handleClick(str: String) {
        Log.e("alexp", "sub section clicked");
    }
}
