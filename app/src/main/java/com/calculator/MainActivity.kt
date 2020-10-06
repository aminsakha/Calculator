package com.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.floor

class MainActivity : AppCompatActivity() {

    private var operation: Char = ' '
    private var leftHandSide: Double = 0.0
    private var rightHandSide: Double = 0.0
    private var oneOpFinished: Boolean = false
    private var tmpScreenString: String = ""

    fun test(view: View) {
        val btn = view as Button
        appendToDigitOnScreen(btn.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCenter.start(
            application, "7acab5be-2cf9-487d-8d18-e2e73eaa946a",
            Analytics::class.java, Crashes::class.java
        )

        functionalButtons()
        operationalButtons()
    }


    private fun appendToDigitOnScreen(digit: String) {
        if (tmpScreenString != "")
            result_id.text = tmpScreenString
        tmpScreenString = ""
        result_id.append(digit)
    }

    private fun selectOperation(c: Char) {
        if (!oneOpFinished) {
            operation = c
            leftHandSide = result_id.text.toString().toDouble()
            result_id.append(c.toString())
            tmpScreenString = result_id.text.toString()
            oneOpFinished = true
        } else
            Toast.makeText(this, "please click on { = }", Toast.LENGTH_SHORT).show()
    }

    private fun operationalButtons() {
        addition_btn.setOnClickListener {
            selectOperation('+')
        }

        subtract_btn.setOnClickListener {
            selectOperation('-')
        }

        divide_btn.setOnClickListener {
            selectOperation('÷')
        }

        multipy_btn.setOnClickListener {
            selectOperation('×')
        }
    }

    private fun functionalButtons() {

        clear_everything_btn.setOnClickListener {
            result_id.text = ""
            oneOpFinished = false
        }

        clear_btn.setOnClickListener {
            clearDigit()
        }

        equal_btn.setOnClickListener {
            performMathOperation()
        }
    }

    private fun performMathOperation() {
        oneOpFinished = false
        if (result_id.text.isNotEmpty())
            rightHandSide =
                result_id.text.substring(result_id.text.indexOf(operation) + 1).toDouble()
        if (rightHandSide.toString().length <= 8 && leftHandSide.toString().length <= 8) {

            when (operation) {

                '+' -> {
                    val sum: Number = if (checkIfWeHaveInt()) {
                        addInt(leftHandSide.toLong(), rightHandSide.toLong())
                    } else {
                        add(leftHandSide, rightHandSide)
                    }
                    result_id.text = sum.toString()
                }
                '-' -> {
                    val subtract: Number = if (checkIfWeHaveInt()) {
                        subtractInt(leftHandSide.toLong(), rightHandSide.toLong())
                    } else {
                        subtract(leftHandSide, rightHandSide)
                    }
                    result_id.text = subtract.toString()
                }
                '×' -> {
                    val multiply: Number = if (checkIfWeHaveInt()) {
                        multiplyInt(leftHandSide.toLong(), rightHandSide.toLong())
                    } else {
                        multiply(leftHandSide, rightHandSide)
                    }
                    result_id.text = multiply.toString()
                }
                '÷' -> {
                    result_id.text = divide(leftHandSide, rightHandSide).toString()
                }
            }
            operation = ' '
        } else
            Toast.makeText(this, "only up to 8 digits", Toast.LENGTH_SHORT).show()

    }

    private fun clearDigit() {
        val tmp: String = result_id.text.substring(result_id.text.indexOf(operation) + 1)
        if (tmp.isEmpty()) {
            operation = ' '
            oneOpFinished = false
            result_id.text = result_id.text.toString().substring(0, result_id.text.length - 1)
        } else
            result_id.text = result_id.text.substring(0, result_id.text.length - 1)
    }

    private fun checkIfWeHaveInt(): Boolean {
        if (rightHandSide.toString().toDouble() == floor(rightHandSide.toString().toDouble()) &&
            leftHandSide.toString().toDouble() == floor(leftHandSide.toString().toDouble())
        )
            return true
        return false
    }
}