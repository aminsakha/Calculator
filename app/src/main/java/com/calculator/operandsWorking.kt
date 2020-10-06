package com.calculator

import java.text.NumberFormat
import javax.xml.transform.dom.DOMLocator

fun add(left_hand_side: Double, right_hand_side: Double): Double {
    return left_hand_side+right_hand_side
}
fun addInt(left_hand_side: Long, right_hand_side: Long): Long {
    return left_hand_side+right_hand_side
}

fun subtract(left_hand_side: Double, right_hand_side: Double): Double {
    return left_hand_side - right_hand_side
}
fun subtractInt(left_hand_side: Long, right_hand_side: Long): Long {
    return left_hand_side - right_hand_side
}

fun divide(left_hand_side: Double, right_hand_side: Double): Double {

    return try {
        left_hand_side / right_hand_side
    } catch (e: ArithmeticException) {
        0.0
    }
}
fun multiply(left_hand_side: Double, right_hand_side: Double): Double {
    return left_hand_side * right_hand_side
}
fun multiplyInt(left_hand_side: Long, right_hand_side: Long): Long {
    return left_hand_side * right_hand_side
}