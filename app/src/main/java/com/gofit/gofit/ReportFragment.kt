package com.gofit.gofit

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.Timestamp
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

    private lateinit var tvEdit: TextView
    private lateinit var tvBMIValue: TextView
    private lateinit var tvBMIResult: TextView

    private lateinit var rgFitnessGoal: RadioGroup
    private lateinit var rbWeightLoss: RadioButton
    private lateinit var rbMuscleGain: RadioButton
    private lateinit var rbOverall: RadioButton

    private lateinit var rgFitnessLevel: RadioGroup
    private lateinit var rbBeginner: RadioButton
    private lateinit var rbInter: RadioButton
    private lateinit var rbAdvanced: RadioButton

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

        tvEdit = rootView.findViewById(R.id.tvEdit)
        tvBMIValue = rootView.findViewById(R.id.tvBMIValue)
        tvBMIResult = rootView.findViewById(R.id.tvBMIResult)

        rgFitnessGoal = rootView.findViewById(R.id.rgFitnessGoal)
        rbWeightLoss = rootView.findViewById(R.id.rbWeightLoss)
        rbMuscleGain = rootView.findViewById(R.id.rbMuscleGain)
        rbOverall = rootView.findViewById(R.id.rbOverall)

        rgFitnessLevel = rootView.findViewById(R.id.rgFitnessLevel)
        rbBeginner = rootView.findViewById(R.id.rbBeginner)
        rbInter = rootView.findViewById(R.id.rbInter)
        rbAdvanced = rootView.findViewById(R.id.rbAdvanced)

        ivCalendar.setOnClickListener {

        }

        tvEdit.setOnClickListener {

        }



        return rootView
    }

    fun BMIValue() {
//        BMI calculator, returns BMI value
    }

    fun BMIInterpetation(BMIValue: Double) {
//        BMI calculator, returns BMI value
    }
}
