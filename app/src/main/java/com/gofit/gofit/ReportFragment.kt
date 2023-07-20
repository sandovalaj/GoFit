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
    private lateinit var rbSun: RadioButton
    private lateinit var rbMon: RadioButton
    private lateinit var rbTue: RadioButton
    private lateinit var rbWed: RadioButton
    private lateinit var rbThu: RadioButton
    private lateinit var rbFri: RadioButton
    private lateinit var rbSat: RadioButton

    private lateinit var tvBMIValue: TextView
    private lateinit var tvBMIResult: TextView

    private lateinit var tvGoalValue: TextView
    private lateinit var tvLevelValue: TextView

    private lateinit var cvCalendar: CalendarView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_report, container, false)

        tvWorkoutsValue = rootView.findViewById(R.id.tvWorkoutsValue)
        tvCaloriesValue = rootView.findViewById(R.id.tvCaloriesValue)
        tvMinutesValue = rootView.findViewById(R.id.tvMinutesValue)

        ivCalendar = rootView.findViewById(R.id.ivCalendar)
        rbSun  = rootView.findViewById(R.id.rbSun)
        rbMon = rootView.findViewById(R.id.rbMon)
        rbTue = rootView.findViewById(R.id.rbTue)
        rbWed = rootView.findViewById(R.id.rbWed)
        rbThu = rootView.findViewById(R.id.rbThu)
        rbFri = rootView.findViewById(R.id.rbFri)
        rbSat = rootView.findViewById(R.id.rbSat)

        tvBMIValue = rootView.findViewById(R.id.tvBMIValue)
        tvBMIResult = rootView.findViewById(R.id.tvBMIResult)

        tvGoalValue = rootView.findViewById(R.id.tvGoalValue)
        tvLevelValue = rootView.findViewById(R.id.tvLevelValue)

        // Setting up Report
        // Setting up History

        ivCalendar.setOnClickListener {
            var calendarDialog = layoutInflater.inflate(R.layout.dialogue_calendar_view, null)
            cvCalendar = calendarDialog.findViewById<CalendarView>(R.id.cvCalendar)

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
                highlightedDates.add(EventDay(temp, R.drawable.calendar_icon))
            }

            cvCalendar.setEvents(highlightedDates)

            val alertDialogBuilder = AlertDialog.Builder(requireContext())
                .setTitle("Workout History")
                .setView(calendarDialog)
                .setNegativeButton("Close") { dialog, _ ->
                    dialog.dismiss()
                }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

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

        // Setting up Goal and Level
        when (DataManager.goal) {
            1 -> tvGoalValue.text = "Weight Loss"
            2 -> tvGoalValue.text = "Muscle Gain"
            3 -> tvGoalValue.text = "Overall"
        }

        when (DataManager.level) {
            1 -> tvLevelValue.text = "Beginner"
            2 -> tvLevelValue.text = "Intermmediate"
            3 -> tvLevelValue.text = "Advanced"
        }

        return rootView
    }


}
