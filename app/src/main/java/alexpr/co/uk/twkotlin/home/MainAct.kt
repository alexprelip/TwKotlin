package alexpr.co.uk.twkotlin.home

import alexpr.co.uk.twkotlin.CustomLinearLayoutManager
import alexpr.co.uk.twkotlin.HomeAdapter
import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.TwApplication
import alexpr.co.uk.twkotlin.models.MainMenu
import alexpr.co.uk.twkotlin.models.MenuSection
import alexpr.co.uk.twkotlin.search.SearchResult
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

class MainAct : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null

    @SuppressLint("CheckResult")
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.main_act)

        recyclerView = findViewById(R.id.home_recycler);
        recyclerView?.layoutManager = CustomLinearLayoutManager(this);

        val viewModel = MainActViewModel(TwApplication.twService)
        viewModel.getHomeData().observe(this, Observer { response: MainMenu ->
            recyclerView?.adapter = HomeAdapter(
                    response.menuList,
                    this,
                    { str: String, section: MenuSection -> handleItemClick(str, section) },
                    { int: Int -> handleSectionClick(int) })
        })
    }

    fun handleItemClick(str: String, section: MenuSection) {
        Log.d("alexp", "sub section clicked $str");
        startActivity(Intent(this, SearchResult::class.java)
                .putExtra("search_query", str)
                .putExtra("section", section))
    }

    fun handleSectionClick(int: Int) {
        recyclerView?.smoothScrollToPosition(int)
    }
}
