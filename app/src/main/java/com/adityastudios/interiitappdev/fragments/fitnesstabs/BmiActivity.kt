package com.adityastudios.interiitappdev.fragments.fitnesstabs

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.adityastudios.interiitappdev.R
import com.adityastudios.interiitappdev.Utils
import com.adityastudios.interiitappdev.databinding.ActivityBmiBinding

class BmiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBmiBinding
    private var weightIdx: Int = -1
    private var heightIdx: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBmiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.toolbar.labelTV.text= "BMI Calculator"
        binding.toolbar.backBtn.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        val weightUnits = listOf("kg","pounds")
        handelDropDownOptions(binding.autoCompleteWeightTV,weightUnits,1)

        val heightUnits = listOf("cms","meter","feet")
        handelDropDownOptions(binding.autoCompleteHeightTV,heightUnits,2)

//        val genderList = listOf("Male", "Female")
//        val gender = handelDropDownOptions(binding.autoCompleteGenderTV,genderList)

        binding.calculateBmiBtn.setOnClickListener {
            var weight = binding.weightET.text.toString().trim().toDouble()
            var height = binding.heightET.text.toString().trim().toDouble()
            val weightUnit = weightUnits[weightIdx]
            val heightUnit = heightUnits[heightIdx]
            if(weightUnit=="" || heightUnit==""){
                Utils.toast(this,"Select the Units")
            }
            when (weightUnit) {
                "pounds" -> {
                    weight *= 0.45359237
                }
            }
            when (heightUnit) {
                "cms" -> {
                    height /= 100
                }

                "feet" -> {
                    height *= 0.3048
                }
            }
            var bmi = weight / (height * height)
            binding.bmiValTV.text = bmi.toString()
            changeBGColorBMITVandResult(String.format("%.1f", bmi).toDouble())
            Utils.toast(this, "$weightUnit $heightUnit $weight $height")
        }

    }

    private fun changeBGColorBMITVandResult(bmi: Double) {
        when(bmi){
            in 0.0..18.4 ->{
                binding.bmiValTV.setBackgroundColor(Color.YELLOW)
                binding.resultTV.text = "Your are UnderWeight"
                binding.resultTV.setBackgroundColor(Color.YELLOW)

            }
            in 18.5..24.9 ->{
                binding.bmiValTV.setBackgroundColor(Color.GREEN)
                binding.resultTV.text = "Your Weight is Normal"
                binding.resultTV.setBackgroundColor(Color.GREEN)
            }
            in 25.0..39.9 ->{
                binding.bmiValTV.setBackgroundColor(Color.rgb(255, 165, 0))
                binding.resultTV.text = "Your are OverWeight"
                binding.resultTV.setBackgroundColor(Color.rgb(255, 165, 0))
            }
            else ->{
                binding.bmiValTV.setBackgroundColor(Color.RED)
                binding.resultTV.text = "Your are Severely Obese"
                binding.resultTV.setBackgroundColor(Color.RED)
            }
        }
    }

    private fun handelDropDownOptions(autoCompleteTV: AutoCompleteTextView,unitList: List<String>,id:Int){
        val adapter = ArrayAdapter(this,R.layout.list_items,unitList)
        autoCompleteTV.setAdapter(adapter)

        autoCompleteTV.onItemClickListener = AdapterView.OnItemClickListener{
            adapterView, view, i, l ->
                when(id){
                    1 ->{
                        weightIdx = i
                    }
                    2 ->{
                        heightIdx = i
                    }
                }
        }
    }
}