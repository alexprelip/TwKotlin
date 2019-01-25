package alexpr.co.uk.twkotlin

import alexpr.co.uk.twkotlin.models.MainMenu
import alexpr.co.uk.twkotlin.search.SearchResult
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainAct : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null

    @SuppressLint("CheckResult")
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.main_act)

        recyclerView = findViewById(R.id.home_recycler);
        recyclerView?.layoutManager = CustomLinearLayoutManager(this);

        TwApplication.twService.getHomeMenu().observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response: MainMenu ->
                    Log.e("alexp", "request successful: " + response)
                    recyclerView?.adapter = HomeAdapter(
                        response.menuList,
                        this,
                        { str: String -> handleItemClick(str) },
                        { int: Int -> handleSectionClick(int) });
                },
                { t: Throwable -> Log.e("alexp", "request failed: " + t.message) })
    }

    fun handleItemClick(str: String) {
        Log.e("alexp", "sub section clicked $str");
        startActivity(Intent(this, SearchResult::class.java).putExtra("search_query", str))
    }

    fun handleSectionClick(int: Int) {
        recyclerView?.smoothScrollToPosition(int)
    }
}
