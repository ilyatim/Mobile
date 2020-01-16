package com.example.calculator.util.viewModel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.calculator.util.parser.Exec
import kotlin.math.floor


class MainViewModel : ViewModel() {
    var input = ObservableField<String>()
    var output = ObservableField<String>()

    private var previous: Double = 0.0
    private var afterEqual: Boolean = false

    fun add(value: String, action: Boolean) {
        if (afterEqual) {
            if (isInteger(previous)) input.set(previous.toInt().toString())
            else input.set(previous.toString())
            afterEqual = false
            setOutput(null)
            if (!action) {
                input.set(value)
                return
            }
        }
        if (action) {
            if (input.get() == null) input.set(value)
            else input.set("${input.get()} $value ")
        } else {
            if (input.get() == null) input.set(value)
            else input.set(input.get() + value)
        }
    }
    fun changeSign() {

    }
    fun clear() {
        inputClear()
        setOutput(null)
        previous = 0.0
    }
    fun result() {
        try {
            val result = Exec.exec(input.get()!!)
            if (isInteger(result)) setOutput(result.toInt().toString())
            else setOutput(result.toString())
            previous = result
            afterEqual = true
        } catch (e: RuntimeException) {
            inputClear()
            setOutput("Неверная запись")
        } catch (e: NullPointerException) {
            inputClear()
        } catch (e: ArithmeticException) {
            inputClear()
            setOutput("${e.message}")
        } catch (e: IllegalArgumentException) {
            inputClear()
            setOutput("ой")
        }
    }
    private fun isInteger(variable: Double): Boolean {
        return variable == floor(variable) &&
                !java.lang.Double.isInfinite(variable) &&
                !java.lang.Double.isNaN(variable) && variable <= Int.MAX_VALUE && variable >= Int.MIN_VALUE
    }
    private fun inputClear() {
        input.set(null)
    }
    private fun setOutput(string: String?) {
        output.set(string)
    }
}