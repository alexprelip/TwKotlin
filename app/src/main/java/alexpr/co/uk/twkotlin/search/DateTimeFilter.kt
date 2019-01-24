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
    private val pickerHours = Array(25) { "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.date_time_filter_layout)

        for (i in 0..24) {
            pickerHours[i] = "$i:00"
        }

        weekDays = resources.getStringArray(R.array.week_days_array_full)
        monthsArray = resources.getStringArray(R.array.months_array)
        selectedDay = resources.getString(R.string.date_filter_any_day)
        selectedTime = resources.getString(R.string.date_filter_any_time)

        val queryStr = intent.extras?.getString("search_query")

        findViewById<TextView>(R.id.date_time_query_field).setText(queryStr)

        setupCalendarRecycler()
        setupNumberPickers()

        findViewById<View>(R.id.time_section).setOnClickListener {
            clear_time.visibility = View.VISIBLE
            time_pickers.visibility = View.VISIBLE
        }

        findViewById<View>(R.id.clear_time).setOnClickListener {
            clear_time.visibility = View.GONE
            time_pickers.visibility = View.GONE
            selectedTime = resources.getString(R.string.date_filter_any_time)
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
            selectedDay = resources.getString(R.string.date_filter_any_day)
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
        pickerTo.displayedValues = pickerHours.copyOfRange(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1, pickerHours.size)
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
            selectedTime = "${pickerHours[pickerFrom.value]} to ${pickerHours[pickerTo.value]}"
            mergeTime()
        }

        pickerTo.setOnValueChangedListener { picker, oldVal, newVal ->
            selectedTime = "${pickerHours[pickerFrom.value]} to ${pickerHours[pickerTo.value]}"
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
        val date = Calendar.getInstance()

        if (date.get(Calendar.DAY_OF_MONTH) == day
                && date.get(Calendar.MONTH) == month
                && date.get(Calendar.YEAR) == year) {
            selectedDay = resources.getString(R.string.date_filter_today)
        } else if (date.get(Calendar.DAY_OF_MONTH) + 1 == day
                && date.get(Calendar.MONTH) == month
                && date.get(Calendar.YEAR) == year) {
            selectedDay = resources.getString(R.string.date_filter_tomorrow)
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
            selectedDay = resources.getString(R.string.date_filter_day_month, day.toString(), monthsArray[month])
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
            selectedDay = resources.getString(R.string.date_filter_any_day)
            date_picker_recycler.visibility = View.GONE
            mergeTime()
            return
        }
        unToggleButtons()

        button.isChecked = true
        findViewById<View>(R.id.clear_date).visibility = View.VISIBLE

        when {
            dayMode == DAY_MODE.TODAY -> {
                selectedDay = resources.getString(R.string.date_filter_today)
            }
            dayMode == DAY_MODE.TOMORROW -> {
                selectedDay = resources.getString(R.string.date_filter_tomorrow)
            }
            dayMode == DAY_MODE.THREE_DAYS -> {
                selectedDay = resources.getString(R.string.date_filter_next_three_days)
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

        if (selectedDay.equals(resources.getString(R.string.date_filter_any_day))) {
            mergedTime = selectedTime
        } else if (selectedTime.equals(resources.getString(R.string.date_filter_any_time))) {
            mergedTime = selectedDay
        } else {
            mergedTime = "$selectedDay $selectedTime";
        }
        date_time_query_field.text = mergedTime
        filter_day_section_label.text = selectedDay
        filter_hour_section_label.text = selectedTime
    }
}