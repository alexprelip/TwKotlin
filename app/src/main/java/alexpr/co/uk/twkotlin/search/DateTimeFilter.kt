package alexpr.co.uk.twkotlin.search

import alexpr.co.uk.twkotlin.R
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatToggleButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.date_time_filter_layout.*
import java.util.*


class DateTimeFilter : AppCompatActivity() {
    val mergedTime: String = ""
    var selectedDay: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.date_time_filter_layout)
        val queryStr = intent.extras?.getString("search_query")

        findViewById<TextView>(R.id.date_time_query_field).setText(queryStr)

        findViewById<RecyclerView>(R.id.date_picker_recycler).adapter = CalendarAdapter(Calendar.getInstance(), 3, this)
        { day, month, year -> datePicked(day, month, year) }
        findViewById<RecyclerView>(R.id.date_picker_recycler).layoutManager = GridLayoutManager(this, 7, RecyclerView.VERTICAL, false)
        findViewById<RecyclerView>(R.id.date_picker_recycler).addItemDecoration(CalendarItemDecoration(this))


        findViewById<View>(R.id.time_section).setOnClickListener {
            clear_time.visibility = View.VISIBLE
            time_pickers.visibility = View.VISIBLE
        }

        findViewById<View>(R.id.clear_time).setOnClickListener {
            clear_time.visibility = View.GONE
            time_pickers.visibility = View.GONE
        }

        findViewById<NumberPicker>(R.id.hour_picker_from).minValue = 0;
        findViewById<NumberPicker>(R.id.hour_picker_from).maxValue = 23;

        findViewById<NumberPicker>(R.id.hour_picker_to).minValue = 0;
        findViewById<NumberPicker>(R.id.hour_picker_to).maxValue = 23;


        findViewById<View>(R.id.date_section).setOnClickListener {
            //            clear_date.visibility = View.VISIBLE
            date_pickers.visibility = View.VISIBLE
        }
        findViewById<View>(R.id.clear_date).setOnClickListener {
            clear_date.visibility = View.GONE
            date_pickers.visibility = View.GONE
        }

        findViewById<AppCompatToggleButton>(R.id.date_filter_today).setOnCheckedChangeListener({ view, isChecked ->
            handleToggleClick(view as AppCompatToggleButton, isChecked, 0, 0)
        })
        findViewById<AppCompatToggleButton>(R.id.date_filter_tomorrow).setOnCheckedChangeListener({ view, isChecked ->
            handleToggleClick(view as AppCompatToggleButton, isChecked, 0, 3600 * 24)
        })
        findViewById<AppCompatToggleButton>(R.id.date_filter_three_days).setOnCheckedChangeListener({ view, isChecked ->
            handleToggleClick(view as AppCompatToggleButton, isChecked, 0, 3600 * 24 * 3)
        })
        findViewById<AppCompatToggleButton>(R.id.date_filter_choose_day).setOnCheckedChangeListener({ view, isChecked ->
            handleToggleClick(view as AppCompatToggleButton, isChecked, 0, 0)
        })
    }

    fun datePicked(day: Int, month: Int, year: Int) {
        Log.e("alexp", "datePicked: $day, $month, $year")
    }

    private fun locationPicked(location: String) {
        Log.e("alexp", "pickedLocation: $location")
        setResult(Activity.RESULT_OK, intent.putExtra("search_query", location))
        finish()
    }

    private fun handleToggleClick(button: AppCompatToggleButton, checked: Boolean, dayOffsetStart: Int, dayOffsetEnd: Int) {
        if (!checked) {
            selectedDay = ""
            return
        }
        (date_filter_today as AppCompatToggleButton).isChecked = false
        (date_filter_tomorrow as AppCompatToggleButton).isChecked = false
        (date_filter_three_days as AppCompatToggleButton).isChecked = false
        (date_filter_choose_day as AppCompatToggleButton).isChecked = false

        button.isChecked = true
//        selectedDay = day
    }

    private fun mergeTime() {

    }
}