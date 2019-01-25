package alexpr.co.uk.twkotlin.search

import alexpr.co.uk.twkotlin.CustomLinearLayoutManager
import alexpr.co.uk.twkotlin.HomeAdapter
import alexpr.co.uk.twkotlin.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
            startActivity(Intent(this, SearchQueryFilter::class.java).putExtra("search_query", queryStr))
        }
        findViewById<View>(R.id.search_location_field).setOnClickListener {
            val location = findViewById<AppCompatTextView>(R.id.search_location_field).text
            startActivityForResult(Intent(this, LocationFilter::class.java).putExtra("search_query", location), LOCATION_REQ_CODE)
        }

        findViewById<View>(R.id.search_date_time_field).setOnClickListener {
            val location = findViewById<AppCompatTextView>(R.id.search_date_time_field).text
            startActivityForResult(Intent(this, DateTimeFilter::class.java).putExtra("search_query", location), DATE_TIME_REQ_CODE)
        }

        val mBottomSheetBehavior = BottomSheetBehavior.from(findViewById<View>(R.id.bottom_sheet_filter))
        val mViewBg = findViewById<View>(R.id.bg)

        findViewById<View>(R.id.filter_price_rating).setOnClickListener { mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED); }
        mBottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED)
                    mViewBg.setVisibility(View.GONE)
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                mViewBg.setVisibility(View.VISIBLE)
                mViewBg.setAlpha(slideOffset)
            }
        })

        //disable clicking because it triggers the background below
        findViewById<View>(R.id.bottom_sheet_filter).setOnTouchListener(OnTouchListener { v, event -> true })

        mViewBg.setOnClickListener { mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED); }

        recyclerView = findViewById<RecyclerView>(R.id.search_result_recycler);
        recyclerView?.layoutManager = CustomLinearLayoutManager(this);
        recyclerView?.adapter = HomeAdapter(ArrayList(), this, { str: String -> handleItemClick(str) }, { int: Int -> handleSectionClick(int) });

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