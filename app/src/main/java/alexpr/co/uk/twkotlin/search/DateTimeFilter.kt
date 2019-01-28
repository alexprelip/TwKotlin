package alexpr.co.uk.twkotlin.search

import alexpr.co.uk.twkotlin.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatToggleButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.date_time_filter_layout.*
import org.joda.time.DateTime
import org.joda.time.Days
import java.util.*


class DateTimeFilter : AppCompatActivity() {
    var hourStart: Int = -1
    var hourEnd: Int = -1

    var selDateStart: DateTime? = null
    var selDateEnd: DateTime? = null

    enum class DAY_MODE { TODAY, TOMORROW, THREE_DAYS, CHOOSE_DAY }

    private lateinit var weekDays: Array<String>
    private lateinit var monthsArray: Array<String>
    private val pickerHours = Array(25) { "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.date_time_filter_layout)

        for (i in 0..24) {
            pickerHours[i] = "$i:00"
        }

        weekDays = resources.getStringArray(R.array.week_days_array_full)
        monthsArray = resources.getStringArray(R.array.months_array)

        //TODO update the time and date with values comping from the prev activity
        val queryStr = intent.extras?.getString("search_query")

        findViewById<TextView>(R.id.date_time_query_field).setText(queryStr)

        setupCalendarRecycler()
        setupNumberPickers()
        mergeTime()

        findViewById<View>(R.id.time_section).setOnClickListener {
            clear_time.visibility = View.VISIBLE
            time_pickers.visibility = View.VISIBLE
            hourStart = findViewById<NumberPicker>(R.id.hour_picker_from).value
            hourEnd = findViewById<NumberPicker>(R.id.hour_picker_to).value
            mergeTime()
        }

        findViewById<View>(R.id.clear_time).setOnClickListener {
            clear_time.visibility = View.GONE
            time_pickers.visibility = View.GONE
            hourStart = -1
            hourEnd = -1
            mergeTime()
        }

        findViewById<View>(R.id.date_section).setOnClickListener {
            date_pickers.visibility = View.VISIBLE
        }

        findViewById<View>(R.id.clear_date).setOnClickListener {
            clear_date.visibility = View.GONE
            date_pickers.visibility = View.GONE
            date_picker_recycler.visibility = View.GONE
            unToggleButtons()
            filter_day_section_label.text = resources.getString(R.string.date_filter_any_day)
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

        findViewById<Button>(R.id.apply_date_filter).setOnClickListener {
            val resultIntent = Intent()
            if (hourStart != -1 && hourEnd != -1) {
                resultIntent.putExtra("hour_start", hourStart)
                    .putExtra("hour_end", hourEnd)
            }
            if (selDateStart != null) {
                resultIntent.putExtra("day_start", selDateStart)
            }

            if (selDateEnd != null) {
                resultIntent.putExtra("day_end", selDateEnd)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun setupNumberPickers() {
        val pickerFrom = findViewById<NumberPicker>(R.id.hour_picker_from)
        pickerFrom.displayedValues = pickerHours
        pickerFrom.minValue = 0;
        pickerFrom.maxValue = 23;
        pickerFrom.setValue(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
        pickerFrom.wrapSelectorWheel = false
        pickerFrom.invalidate()

        val pickerTo = findViewById<NumberPicker>(R.id.hour_picker_to)
        pickerTo.displayedValues =
                pickerHours.copyOfRange(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1, pickerHours.size)
        pickerTo.minValue = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1
        pickerTo.maxValue = 24
        pickerTo.value = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 2
        pickerTo.wrapSelectorWheel = false
        pickerTo.invalidate()

        pickerFrom.setOnValueChangedListener { picker, oldVal, newVal ->
            if (pickerTo.value == newVal) {
                setPickerMin(findViewById(R.id.hour_picker_to), newVal + 2)
            } else {
                setPickerMin(findViewById(R.id.hour_picker_to), newVal + 1)
            }
            hourStart = pickerFrom.value
            hourEnd = pickerTo.value
            mergeTime()
        }

        pickerTo.setOnValueChangedListener { picker, oldVal, newVal ->
            hourStart = pickerFrom.value
            hourEnd = pickerTo.value
            mergeTime()
        }

    }

    private fun setupCalendarRecycler() {
        val calendarRecycler = findViewById<RecyclerView>(R.id.date_picker_recycler);
        calendarRecycler.adapter = CalendarAdapter(Calendar.getInstance(), 3, this)
        { day, month, year -> datePicked(day, month, year) }
        calendarRecycler.layoutManager = GridLayoutManager(this, 7, RecyclerView.VERTICAL, false)
        calendarRecycler.addItemDecoration(CalendarItemDecoration(this))
    }

    private fun setPickerMin(numberPicker: NumberPicker, min: Int) {
        if (min <= numberPicker.maxValue) {
            if (min < numberPicker.minValue) {
                numberPicker.displayedValues = pickerHours.copyOfRange(min, pickerHours.size)
                numberPicker.minValue = min
            } else {
                numberPicker.minValue = min
                numberPicker.displayedValues = pickerHours.copyOfRange(min, pickerHours.size)
            }
        }
    }

    private fun datePicked(day: Int, month: Int, year: Int) {
        Log.d("alexp", "datePicked: $day, $month, $year")
        clear_date.visibility = View.VISIBLE
        selDateStart = DateTime(year, month + 1, day, 0, 0, 0)

        mergeTime()
    }

    private fun handleToggleClick(button: AppCompatToggleButton, checked: Boolean, dayMode: DAY_MODE) {
        if (!checked) {
            selDateStart = null
            date_picker_recycler.visibility = View.GONE
            mergeTime()
            return
        }
        unToggleButtons()

        button.isChecked = true
        findViewById<View>(R.id.clear_date).visibility = View.VISIBLE

        when {
            dayMode == DAY_MODE.TODAY -> {
                selDateStart = DateTime.now()
                selDateEnd = null
            }
            dayMode == DAY_MODE.TOMORROW -> {
                selDateStart = DateTime.now().plusDays(1)
                selDateEnd = null
            }
            dayMode == DAY_MODE.THREE_DAYS -> {
                selDateStart = DateTime.now()
                selDateEnd = DateTime.now().plusDays(3)
            }
            dayMode == DAY_MODE.CHOOSE_DAY -> {
                date_picker_recycler.visibility = View.VISIBLE
                (date_picker_recycler.adapter as CalendarAdapter).setSelectedDay(Calendar.getInstance())
                selDateEnd = null
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

        var displayDay = resources.getString(R.string.date_filter_any_day)
        var selectedTime = resources.getString(R.string.date_filter_any_time)
        var mergedTime = ""

        if (selDateStart != null && selDateEnd != null) {
            displayDay = resources.getString(R.string.date_filter_next_three_days)
        } else if (selDateStart != null) {
            val dateNow = DateTime.now()
            val d = Days.daysBetween(dateNow.withTimeAtStartOfDay(), selDateStart!!.withTimeAtStartOfDay())

            when {
                d.days == 0 -> {
                    displayDay = resources.getString(R.string.date_filter_today)
                }
                d.days == 1 -> {
                    displayDay = resources.getString(R.string.date_filter_tomorrow)
                }
                d.days < 7 -> {
                    displayDay = weekDays[selDateStart!!.dayOfWeek - 1]
                }
                d.days >= 7 -> {
                    displayDay = "${selDateStart!!.dayOfMonth().asText} ${selDateStart!!.monthOfYear().asText}"
                }
            }
        }

        if (hourStart == -1 || hourEnd == -1) {
            selectedTime = resources.getString(R.string.date_filter_any_time)
        } else {
            selectedTime = "${pickerHours[hourStart]} to ${pickerHours[hourEnd]}"
        }

        if (displayDay.equals(resources.getString(R.string.date_filter_any_day))) {
            mergedTime = selectedTime
        } else if (selectedTime.equals(resources.getString(R.string.date_filter_any_time))) {
            mergedTime = displayDay
        } else {
            mergedTime = "$displayDay $selectedTime";
        }

        date_time_query_field.text = mergedTime
        filter_day_section_label.text = displayDay
        filter_hour_section_label.text = selectedTime
    }
}