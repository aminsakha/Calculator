package com.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.floor

class MainActivity : AppCompatActivity() {

    var digit_on_screen = StringBuilder()
    var operation: Char = ' '
    var leftHandSide: Double = 0.0
    var rightHandSide: Double = 0.0

    private val listener = View.OnClickListener { view ->
        val btn = view as Button
        appendToDigitOnScreen(btn.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  result_id.text = "0"

        initializeButtons()
    }

    private fun initializeButtons() {
        functionalButtons()
        operationalButtons()
        numericalButtons()
    }

    private fun appendToDigitOnScreen(digit: String) {

        digit_on_screen.append(digit)
        result_id.text = digit_on_screen.toString()
    }

    private fun numericalButtons() {
        one_btn.setOnClickListener(listener)

        two_btn.setOnClickListener(listener)

        three_btn.setOnClickListener(listener)

        four_btn.setOnClickListener(listener)

        five_btn.setOnClickListener(listener)

        six_btn.setOnClickListener(listener)

        seven_btn.setOnClickListener(listener)

        eight_btn.setOnClickListener(listener)

        nine_btn.setOnClickListener(listener)

        zero_btn.setOnClickListener(listener)

        dot_btn.setOnClickListener(listener)
    }

    private fun selectOperation(c: Char) {
        operation = c
        if (digit_on_screen.isNotEmpty() && digit_on_screen.toString().toDouble() != floor(
                digit_on_screen.toString().toDouble()
            )
        )
            leftHandSide = digit_on_screen.toString().toDouble()
        else
            leftHandSide = digit_on_screen.toString().toLong().toDouble()

        result_id.append(c.toString())
        digit_on_screen.clear()
    }

    private fun operationalButtons() {

        addition_btn.setOnClickListener {
            selectOperation('+')
        }

        subtract_btn.setOnClickListener {
            selectOperation('-')
        }

        divide_btn.setOnClickListener {
            selectOperation('/')
        }

        multipy_btn.setOnClickListener {
            selectOperation('*')
        }

    }

    private fun functionalButtons() {

        clear_everything_btn.setOnClickListener {
            digit_on_screen.clear()
            result_id.text = result_id.hint
        }

        clear_btn.setOnClickListener {

            if (digit_on_screen.length <= 0) {
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
        if (digit_on_screen.isNotEmpty())
            rightHandSide = digit_on_screen.toString().toDouble()
        digit_on_screen.clear()


        when (operation) {

            '+' -> {
                val sum: Number
                if (checkIfWeHaveInt()) {
                    sum = addInt(leftHandSide.toLong(), rightHandSide.toLong())
                } else {
                    sum = add(leftHandSide, rightHandSide)
                }
                result_id.text = sum.toString()

                // digit_on_screen.append(sum)
            }
            '-' -> {
                val subtract = subtract(leftHandSide, rightHandSide)
                result_id.text = subtract.toString()
                // digit_on_screen.append(subtract)
            }
            '*' -> {
                val multiply = multiply(leftHandSide, rightHandSide)
                result_id.text = multiply.toString()
                // digit_on_screen.append(multiply)
            }
            '/' -> {
                val divide = divide(leftHandSide, rightHandSide)
                result_id.text = divide.toString()
                // digit_on_screen.append(divide)
            }

        }
        operation = ' '

    }

    private fun clearDigit() {

        val length = digit_on_screen.length

        digit_on_screen.deleteCharAt(length - 1)
        result_id.text = digit_on_screen.toString()
    }

    private fun checkIfWeHaveInt(): Boolean {
        if (rightHandSide.toString().toDouble() == floor(rightHandSide.toString().toDouble()) &&
            leftHandSide.toString().toDouble() == floor(leftHandSide.toString().toDouble())
        )
            return true
        return false
    }
}
