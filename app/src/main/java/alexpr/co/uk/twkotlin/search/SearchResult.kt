package alexpr.co.uk.twkotlin.search

import alexpr.co.uk.twkotlin.CustomLinearLayoutManager
import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.TwApplication
import alexpr.co.uk.twkotlin.models.MenuSection
import alexpr.co.uk.twkotlin.network.stubs.StubGenerator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.search_result_activity.*
import org.joda.time.DateTime
import org.joda.time.Days

class SearchResult : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    val LOCATION_REQ_CODE = 1234
    val DATE_TIME_REQ_CODE = 5678

    private lateinit var menuSection: MenuSection

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_result_activity)
        //TODO add the layout behaviour to the location and date filterLiveData

        viewModel = SearchViewModel(TwApplication.twService)

        val queryStr = intent.extras?.getString("search_query")
        menuSection = intent.extras?.getSerializable("section") as MenuSection

        viewModel.filterLiveData.observe(this, Observer { filter: FilterModel ->
            mergeTime(filter.dateStart, filter.dateEnd, filter.startHour, filter.endHour)
            search_location_field.text = filter.location
        })
        Log.e("alepx", "queryString: " + queryStr)

        recyclerView = findViewById(R.id.search_result_recycler);
        recyclerView?.layoutManager = CustomLinearLayoutManager(this);
        recyclerView?.adapter = SearchResultAdapter(StubGenerator.getMockPlaces(), this, { str: String -> handleItemClick(str) });
        initClickListeners()
    }

    private fun initClickListeners() {
        findViewById<View>(R.id.search_query_field).setOnClickListener {

            val query: String = findViewById<TextView>(R.id.search_query_field).text.toString()
            startActivity(
                    Intent(this, SearchQueryFilter::class.java)
                            .putExtra("search_query", query)
                            .putExtra("section", menuSection)
            )
        }

        findViewById<View>(R.id.search_location_field).setOnClickListener {
            val location = findViewById<AppCompatTextView>(R.id.search_location_field).text
            startActivityForResult(
                    Intent(this, LocationFilter::class.java).putExtra("search_query", location),
                    LOCATION_REQ_CODE
            )
        }

        findViewById<View>(R.id.search_date_time_field).setOnClickListener {
            val dateTime = findViewById<AppCompatTextView>(R.id.search_date_time_field).text
            startActivityForResult(
                    Intent(this, DateTimeFilter::class.java).putExtra("search_query", dateTime),
                    DATE_TIME_REQ_CODE
            )
        }

        val mBottomSheetBehavior = BottomSheetBehavior.from(findViewById<View>(R.id.bottom_sheet_filter))
        val mViewBg = findViewById<View>(R.id.bg)

        findViewById<View>(R.id.filter_price_rating).setOnClickListener {
            mBottomSheetBehavior.setState(
                    BottomSheetBehavior.STATE_EXPANDED
            );
        }
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
        findViewById<View>(R.id.bottom_sheet_filter).setOnTouchListener({ v, event -> true })

        mViewBg.setOnClickListener { mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED); }
    }

    fun handleItemClick(str: String) {
        Log.e("alexp", "item $str");
    }

    fun handleSectionClick(int: Int) {
        recyclerView?.smoothScrollToPosition(int)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == LOCATION_REQ_CODE && resultCode == Activity.RESULT_OK) {
            search_location_field.text = data?.getStringExtra("search_query")
        }

        if (requestCode == DATE_TIME_REQ_CODE && resultCode == Activity.RESULT_OK) {
            val startDate: DateTime? = data?.getSerializableExtra("day_start") as DateTime?
            val endDate: DateTime? = data?.getSerializableExtra("day_end") as DateTime?
            val hourStart: Int? = data?.getSerializableExtra("hour_start") as Int?
            val hourEnd: Int? = data?.getSerializableExtra("hour_end") as Int?

            viewModel.updateDateTimeFilter(hourStart, hourEnd, startDate, endDate)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun mergeTime(selDateStart: DateTime?, selDateEnd: DateTime?, hourStart: Int?, hourEnd: Int?) {

        var displayDay = resources.getString(R.string.date_filter_any_day)
        var selectedTime = resources.getString(R.string.date_filter_any_time)
        var mergedTime = ""

        if (selDateStart != null && selDateEnd != null) {
            displayDay = resources.getString(R.string.date_filter_next_three_days)
        } else if (selDateStart != null) {
            val dateNow = DateTime.now()
            val d = Days.daysBetween(dateNow.withTimeAtStartOfDay(), selDateStart.withTimeAtStartOfDay())

            when {
                d.days == 0 -> {
                    displayDay = resources.getString(R.string.date_filter_today)
                }
                d.days == 1 -> {
                    displayDay = resources.getString(R.string.date_filter_tomorrow)
                }
                d.days < 7 -> {
                    displayDay = resources.getStringArray(R.array.week_days_array_full)[selDateStart.dayOfWeek - 1]
                }
                d.days >= 7 -> {
                    displayDay = "${selDateStart.dayOfMonth().asText} ${selDateStart.monthOfYear().asText}"
                }
            }
        }

        selectedTime = if (hourStart == null || hourEnd == null) {
            resources.getString(R.string.date_filter_any_time)
        } else {
            "${String.format("%02d:00", hourStart)} to ${String.format("%02d:00", hourEnd)}"
        }

        if (displayDay.equals(resources.getString(R.string.date_filter_any_day))) {
            mergedTime = selectedTime
        } else if (selectedTime.equals(resources.getString(R.string.date_filter_any_time))) {
            mergedTime = displayDay
        } else {
            mergedTime = "$displayDay $selectedTime";
        }

        search_date_time_field.text = mergedTime
    }
}