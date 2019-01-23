package alexpr.co.uk.twkotlin.search

import alexpr.co.uk.twkotlin.R
import alexpr.co.uk.twkotlin.search.CalendarAdapter.ViewHolder
import android.content.Context
import android.graphics.Rect
import android.graphics.Typeface
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
    var lastSelectedPosition: Int = -1

    init {
        weekDays = context.resources.getStringArray(R.array.week_days_array)
        viewCount = monthsToDisplay * 49
        dayList = Array(viewCount) { DayItem(0, 0, 0, false, false, false, "", false) }
        val startDateCopy: Calendar = startDate.clone() as Calendar
        val endDate: Calendar = startDate.clone() as Calendar
        endDate.set(Calendar.MONTH, endDate.get(Calendar.MONTH) + monthsToDisplay - 1)
        endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH))

        for (i in 0..viewCount - 1) {
            if (i % 49 < 7) {
                val pos: Int = i % 49
                dayList[i] = DayItem(0, 0, 0, false, true, false, weekDays.get(pos), false)
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
                            startDateCopy.get(Calendar.DAY_OF_MONTH).toString(),
                            false
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

    override fun getItemCount(): Int = dayList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, dateClicked);
    }

    fun getItemAtPosition(pos: Int): DayItem {
        return dayList[pos]
    }

    fun setSelectedDay(date: Calendar) {
        var position = 0
        while (position <= dayList.size) {
            if (date.get(Calendar.DAY_OF_MONTH) == dayList[position].day
                    && date.get(Calendar.MONTH) == dayList[position].month
                    && date.get(Calendar.YEAR) == dayList[position].year) {
                break
            } else {
                position++
            }
        }
        if (lastSelectedPosition > -1) {
            dayList[lastSelectedPosition].isSelected = false
            notifyItemChanged(lastSelectedPosition, Any())
        }
        dayList[position].isSelected = true
        notifyItemChanged(position, Any())
        lastSelectedPosition = position
        dateClicked(dayList[position].day, dayList[position].month, dayList[position].year)
        mRecycler.post({
            val rect = Rect()
            mRecycler.findViewHolderForAdapterPosition(position)?.itemView?.getGlobalVisibleRect(rect)
            rect.top = 200
            rect.bottom = 200
            mRecycler.findViewHolderForAdapterPosition(position)?.itemView?.requestRectangleOnScreen(rect)
        })
    }

    private lateinit var mRecycler: RecyclerView

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        mRecycler = recyclerView
        super.onAttachedToRecyclerView(recyclerView)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int, dateClicked: (Int, Int, Int) -> Unit) {
            val dayItem = dayList[position]
            val tv: TextView = view.date_item_view

            // hides the unused views but maintains the cell count
            if (!dayItem.isActive && dayItem.displayText.isEmpty()) {
                val lp = view.layoutParams
                lp.height = 0;
                view.layoutParams = lp
            }

            tv.text = dayItem.displayText
            tv.isEnabled = dayItem.isActive

            if (dayItem.isDayName) {
                tv.setTextColor(context.resources.getColor(R.color.calendarEnabledDay))
            } else if (dayItem.isSelected && dayItem.isToday) {
                tv.setTypeface(null, Typeface.BOLD);
                tv.setTextColor(context.resources.getColor(R.color.calendarSelectedDay))
                tv.background = context.getDrawable(R.drawable.date_selected_circle)
            } else if (dayItem.isToday) {
                tv.setTypeface(null, Typeface.BOLD);
                tv.setTextColor(context.resources.getColor(R.color.calendarToday))
                tv.background = null
            } else if (dayItem.isSelected) {
                tv.setTextColor(context.resources.getColor(R.color.calendarSelectedDay))
                tv.background = context.getDrawable(R.drawable.date_selected_circle)
            } else if (dayItem.isActive) {
                tv.background = null
                tv.setTextColor(context.resources.getColor(R.color.calendarEnabledDay))
            } else {
                tv.background = null
                tv.setTextColor(context.resources.getColor(R.color.calendarDisabledDay))
            }

            if (dayItem.isActive) {
                view.setOnClickListener {
                    if (lastSelectedPosition > -1) {
                        dayList[lastSelectedPosition].isSelected = false
                        notifyItemChanged(lastSelectedPosition, Any())

                    }
                    dayList[adapterPosition].isSelected = true
                    lastSelectedPosition = adapterPosition
                    notifyItemChanged(adapterPosition, Any())
                    dateClicked(dayItem.day, dayItem.month, dayItem.year)
                }
            }
        }
    }
}