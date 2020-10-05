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

    private var digitOnScreen = StringBuilder()
    private var operation: Char = ' '
    private var leftHandSide: Double = 0.0
    private var rightHandSide: Double = 0.0
    private var onOpFinished: Boolean = false

     fun test(view: View){
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

        result_id.text = result_id.hint
        initializeButtons()
    }

    private fun initializeButtons() {
        functionalButtons()
        operationalButtons()
    }

    private fun appendToDigitOnScreen(digit: String) {
        result_id.text = tmpScreenString
        tmpScreenString = ""
        digitOnScreen.append(digit)
        result_id.append(digitOnScreen.toString())
    }


    private var tmpScreenString: String = ""

    private fun selectOperation(c: Char) {
        if (!onOpFinished) {
            operation = c
            leftHandSide = result_id.text.toString().toDouble()
            result_id.append(c.toString())
            tmpScreenString = result_id.text.toString()
            digitOnScreen.clear()
            onOpFinished = true
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
            digitOnScreen.clear()
            result_id.text = result_id.hint
            onOpFinished = false
        }

        clear_btn.setOnClickListener {

            if (digitOnScreen.length <= 0) {
                return@setOnClickListener
            } else {
                clearDigit()
            }
        }

        equal_btn.setOnClickListener {
            performMathOperation()
        }
    }

    private fun performMathOperation() {
        onOpFinished = false
        if (digitOnScreen.isNotEmpty())
            rightHandSide = digitOnScreen.toString().toDouble()
        digitOnScreen.clear()

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

    }

    private fun clearDigit() {

        val length = digitOnScreen.length
        digitOnScreen.deleteCharAt(length - 1)
        result_id.text = digitOnScreen.toString()
    }

    private fun checkIfWeHaveInt(): Boolean {
        if (rightHandSide.toString().toDouble() == floor(rightHandSide.toString().toDouble()) &&
            leftHandSide.toString().toDouble() == floor(leftHandSide.toString().toDouble())
        )
            return true
        return false
    }
}