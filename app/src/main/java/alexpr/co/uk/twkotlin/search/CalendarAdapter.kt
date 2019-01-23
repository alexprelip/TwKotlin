package alexpr.co.uk.twkotlin.search

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.search.CalendarAdapter.ViewHolder
import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.date_recycler_item.view.*
import java.util.*

class CalendarAdapter(
        startDate: Calendar,
        monthsToDisplay: Int,
        val context: Context,
        val dateClicked: (Int, Int, Int) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    var viewCount = 0
    val dayList: Array<DayItem>
    private var weekDays: Array<String>
    var lastFocusedView: TextView? = null

    init {

        weekDays = context.resources.getStringArray(R.array.week_days_array)
        viewCount = monthsToDisplay * 49
        dayList = Array(viewCount) { DayItem(0, 0, 0, false, false, false, "") }
        val startDateCopy: Calendar = startDate.clone() as Calendar
        val endDate: Calendar = startDate.clone() as Calendar
        endDate.set(Calendar.MONTH, endDate.get(Calendar.MONTH) + monthsToDisplay - 1)
        endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH))
        Log.e("alexp", "max for month: " + endDate.getActualMaximum(Calendar.DAY_OF_MONTH))

        for (i in 0..viewCount - 1) {
            if (i % 49 < 7) {
                val pos: Int = i % 49
                dayList[i] = DayItem(0, 0, 0, false, true, false, weekDays.get(pos))
            }
        }

        var position = 0
        var processedMonths = 0;
        startDateCopy.set(Calendar.DAY_OF_MONTH, 1)
        while (startDateCopy.compareTo(endDate) <= 0) {
            if (startDateCopy.get(Calendar.DAY_OF_MONTH) == 1) {
                position = processedMonths * 49
                dayList[position].month = startDateCopy.get(Calendar.MONTH)
                position += if (startDateCopy.get(Calendar.DAY_OF_WEEK) == 1) 6 + 7 else ((startDateCopy.get(Calendar.DAY_OF_WEEK) - 1)) + 6
                processedMonths++
            }
            dayList[position] =
                    DayItem(
                            startDateCopy.get(Calendar.DAY_OF_MONTH),
                            startDateCopy.get(Calendar.MONTH),
                            startDateCopy.get(Calendar.YEAR),
                            startDateCopy.compareTo(startDate) >= 0,
                            false,
                            startDateCopy.compareTo(startDate) == 0,
                            startDateCopy.get(Calendar.DAY_OF_MONTH).toString()
                    )
            position++
            startDateCopy.add(Calendar.DAY_OF_MONTH, 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context);
        val view = inflater.inflate(R.layout.date_recycler_item, parent, false);
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, dateClicked);
    }

    fun getItemAtPosition(pos: Int): DayItem {
        return dayList[pos]
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int, dateClicked: (Int, Int, Int) -> Unit) {
            val dayItem = dayList[position]
            val tv: TextView = view.date_item_view

            // hides the unused views but maintains the
            if (!dayItem.isActive && dayItem.displayText.isEmpty()) {
                val lp = view.layoutParams
                lp.height = 0;
                view.layoutParams = lp
            }
            tv.text = dayItem.displayText
            tv.isEnabled = dayItem.isActive

            if (dayItem.isToday) {
                tv.setTypeface(null, Typeface.BOLD);
            }

            if (dayItem.isActive) {
                if (dayItem.isToday) {
                    tv.setTextColor(context.resources.getColor(R.color.calendarToday))
                } else {
                    tv.setTextColor(context.resources.getColor(R.color.calendarEnabledDay))
                }

                view.setOnClickListener {
                    if (lastFocusedView != null) {
                        if (lastFocusedView?.typeface?.isBold!!)
                            lastFocusedView?.setTextColor(context.resources.getColor(R.color.calendarToday))
                        else lastFocusedView?.setTextColor(context.resources.getColor(R.color.calendarEnabledDay))
                        lastFocusedView?.background = null
                    }


                    tv.setTextColor(context.resources.getColor(R.color.calendarSelectedDay))
                    tv.background = context.getDrawable(R.drawable.date_selected_circle)
                    lastFocusedView = tv
                    dateClicked(dayItem.day, dayItem.month, dayItem.year)
                }
            } else if (dayItem.isDayName) {
                tv.setTextColor(context.resources.getColor(R.color.calendarEnabledDay))
            } else {
                tv.setTextColor(context.resources.getColor(R.color.calendarDisabledDay))
            }
        }
    }
}