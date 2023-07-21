package com.gofit.gofit

import android.app.AlertDialog
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import java.util.Calendar


class ReportFragment : Fragment() {
    private lateinit var tvWorkoutsValue: TextView
    private lateinit var tvCaloriesValue: TextView
    private lateinit var tvMinutesValue: TextView

    private lateinit var ivCalendar: ImageView

    private lateinit var tvBMIValue: TextView
    private lateinit var tvBMIResult: TextView

    private lateinit var cvCalendar: CalendarView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_report, container, false)

        tvWorkoutsValue = rootView.findViewById(R.id.tvWorkoutsValue)
        tvCaloriesValue = rootView.findViewById(R.id.tvCaloriesValue)
        tvMinutesValue = rootView.findViewById(R.id.tvMinutesValue)

        ivCalendar = rootView.findViewById(R.id.ivCalendar)
        cvCalendar = rootView.findViewById(R.id.cvCalendar)

        tvBMIValue = rootView.findViewById(R.id.tvBMIValue)
        tvBMIResult = rootView.findViewById(R.id.tvBMIResult)

        // Setting up Report
        tvWorkoutsValue.text = DataManager.workoutsAccomplished.toString()
        tvCaloriesValue.text = DataManager.calories.toString()
        tvMinutesValue.text = DataManager.minutes.toString()

        // Setting up History
        var calendar: Calendar = Calendar.getInstance()
        var currentYear: Int = calendar.get(Calendar.YEAR)
        var currentMonth: Int = calendar.get(Calendar.MONTH)
        var currentDayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)

        calendar.set(currentYear, currentMonth, currentDayOfMonth)

        cvCalendar.setDate(calendar)

        calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -5)
        var min = calendar

        calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, 5)
        val max = calendar

        cvCalendar.setMinimumDate(min)
        cvCalendar.setMaximumDate(max)

        var highlightedDates: MutableList<EventDay> = mutableListOf()

        for (timestamp in DataManager.workoutDates) {
            var timestampToDate = timestamp.toDate()
            var temp = Calendar.getInstance()
            temp.time = timestampToDate
            highlightedDates.add(EventDay(temp, R.drawable.icon_dumbell))
        }

        cvCalendar.setEvents(highlightedDates)

        // Setting up BMI
        var bmi = (DataManager.weight.toDouble() / (DataManager.height.toDouble() * DataManager.height.toDouble())) * 703
        val bmiResult = when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Healthy Weight"
            bmi < 29.9 -> "Overweight"
            else -> "Obese"
        }

        tvBMIValue.text = String.format("%.2f", bmi)
        tvBMIResult.text = bmiResult


        return rootView
    }


}
