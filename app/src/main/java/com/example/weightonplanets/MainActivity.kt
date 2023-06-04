package com.example.weightonplanets

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import kotlinx.android.synthetic.main.activity_main.*

const val WEIGHT_TO_POUND = 2.2045
const val MARS = 0.38
const val JUPITER = 2.34
const val VENUS = 0.91
const val POUND_TO_WEIGHT = 0.45359237

class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkBox.setOnClickListener(this)
        checkBox2.setOnClickListener(this)
        checkBox3.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        v as CheckBox
        val isChecked = v.isChecked
        if (!TextUtils.isEmpty(editTextNumberDecimal.text.toString())){
            val weight = editTextNumberDecimal.text.toString().toDouble()
            val pound =weightToPound(weight)
            when(v.id){
                R.id.checkBox -> if(isChecked){
                    checkBox2.isChecked = false
                    checkBox3.isChecked = false
                    calculateWeight(pound, v)
                }
                R.id.checkBox2 -> if(isChecked){
                    checkBox.isChecked = false
                    checkBox3.isChecked = false
                    calculateWeight(pound, v)
                }
                R.id.checkBox3 -> if(isChecked){
                    checkBox2.isChecked = false
                    checkBox.isChecked = false
                    calculateWeight(pound, v)
                }
            }
        }
    }

    private fun calculateWeight(pound: Double, checkBox: CheckBox) {
        var result = 0.0
        when(checkBox.id){
            R.id.checkBox -> result = pound * JUPITER
            R.id.checkBox2 -> result = pound * VENUS
            R.id.checkBox3 -> result = pound * MARS
            else -> 0.0
        }
        val resultToWeight = poundToWeight(result)
        textView2.text = resultToWeight.lastDot(2).toString()
    }

    private fun poundToWeight(pound : Double): Double{
        return pound * POUND_TO_WEIGHT
    }

    private fun weightToPound(weight: Double): Double{
        return weight * WEIGHT_TO_POUND
    }

    private fun Double.lastDot(howManyNumber: Int) = java.lang.String.format("%.${howManyNumber}f", this)
}