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
    var mergedTime: String = ""
    var selectedDay: String = ""
    var selectedTime: String = ""

    enum class DAY_MODE { TODAY, TOMORROW, THREE_DAYS, CHOOSE_DAY }

    private lateinit var weekDays: Array<String>
    private lateinit var monthsArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.date_time_filter_layout)

        weekDays = resources.getStringArray(R.array.week_days_array_full)
        monthsArray = resources.getStringArray(R.array.months_array)
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
            date_pickers.visibility = View.VISIBLE
        }

        findViewById<View>(R.id.clear_date).setOnClickListener {
            clear_date.visibility = View.GONE
            date_pickers.visibility = View.GONE
            date_picker_recycler.visibility = View.GONE
            unToggleButtons()
            selectedDay = "Any day"
            mergeTime()
        }

        findViewById<AppCompatToggleButton>(R.id.date_filter_today).setOnCheckedChangeListener { view, isChecked ->
            handleToggleClick(view as AppCompatToggleButton, isChecked, DAY_MODE.TODAY)
        }
        findViewById<AppCompatToggleButton>(R.id.date_filter_tomorrow).setOnCheckedChangeListener { view, isChecked ->
            handleToggleClick(view as AppCompatToggleButton, isChecked, DAY_MODE.TOMORROW)
        }
        findViewById<AppCompatToggleButton>(R.id.date_filter_three_days).setOnCheckedChangeListener { view, isChecked ->
            handleToggleClick(view as AppCompatToggleButton, isChecked, DAY_MODE.THREE_DAYS)
        }
        findViewById<AppCompatToggleButton>(R.id.date_filter_choose_day).setOnCheckedChangeListener { view, isChecked ->
            handleToggleClick(view as AppCompatToggleButton, isChecked, DAY_MODE.CHOOSE_DAY)
        }
    }

    fun datePicked(day: Int, month: Int, year: Int) {
        Log.d("alexp", "datePicked: $day, $month, $year")
        clear_date.visibility = View.VISIBLE
        val date = Calendar.getInstance()

        if (date.get(Calendar.DAY_OF_MONTH) == day
                && date.get(Calendar.MONTH) == month
                && date.get(Calendar.YEAR) == year) {
            selectedDay = "Today"
        } else if (date.get(Calendar.DAY_OF_MONTH) + 1 == day
                && date.get(Calendar.MONTH) == month
                && date.get(Calendar.YEAR) == year) {
            selectedDay = "Tomorrow"
        } else if (day - date.get(Calendar.DAY_OF_MONTH) < 7
                && date.get(Calendar.MONTH) == month
                && date.get(Calendar.YEAR) == year) {

            date.set(Calendar.DAY_OF_MONTH, day)

            var dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
            /** The default first day of week is {@link java.util.Calendar#SUNDAY}.
             * It can be changed, but it's more likely to introduce a bug if someone forgets to do so */
            dayOfWeek = if (dayOfWeek == 1) 6 else dayOfWeek - 2
            selectedDay = weekDays[dayOfWeek]
        } else {
            selectedDay = day.toString() + " " + monthsArray[month]
        }
        mergeTime()
    }

    private fun locationPicked(location: String) {
        Log.e("alexp", "pickedLocation: $location")
        setResult(Activity.RESULT_OK, intent.putExtra("search_query", location))
        finish()
    }

    private fun handleToggleClick(button: AppCompatToggleButton, checked: Boolean, dayMode: DAY_MODE) {
        if (!checked) {
            selectedDay = "Any time"
            date_picker_recycler.visibility = View.GONE
            mergeTime()
            return
        }
        unToggleButtons()

        button.isChecked = true
        findViewById<View>(R.id.clear_date).visibility = View.VISIBLE

        when {
            dayMode == DAY_MODE.TODAY -> {
                selectedDay = "Today "
            }
            dayMode == DAY_MODE.TOMORROW -> {
                selectedDay = "Tomorrow "
            }
            dayMode == DAY_MODE.THREE_DAYS -> {
                selectedDay = "Next 3 days "
            }
            dayMode == DAY_MODE.CHOOSE_DAY -> {
                date_picker_recycler.visibility = View.VISIBLE
                (date_picker_recycler.adapter as CalendarAdapter).setSelectedDay(Calendar.getInstance())
            }
        }
        mergeTime();
    }

    private fun unToggleButtons() {
        (date_filter_today as AppCompatToggleButton).isChecked = false
        (date_filter_tomorrow as AppCompatToggleButton).isChecked = false
        (date_filter_three_days as AppCompatToggleButton).isChecked = false
        (date_filter_choose_day as AppCompatToggleButton).isChecked = false
        date_picker_recycler.visibility = View.GONE
    }

    private fun mergeTime() {
        mergedTime = if (selectedDay.equals("any time")) "" else selectedDay + selectedTime;
        date_time_query_field.text = mergedTime
    }
}