package com.example.calculator.util.viewModel

import android.icu.math.BigDecimal
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
        input.get()?.let {
            if (it.length > 20 && !afterEqual) return
        }
        if (afterEqual) {
            if (isInteger(previous)) setInput(previous.toInt().toString())
            else setInput(previous.toString())
            afterEqual = false
            setOutput(null)
            if (!action) {
                setInput(value)
                return
            }
        }
        if (action) {
            if (input.get() == null) input.set("$value ")
            else setInput("${input.get()} $value ")
        } else {
            if (input.get() == null) input.set(value)
            else setInput(input.get() + value)
        }
    }
    fun changeSign() {
        if (input.get() == null) return
        try {
            val result = Exec.exec(input.get()!!) * -1
            if (isInteger(result)) setInput(result.toInt().toString())
            else setInput(result.toString())
            previous = result
            afterEqual = true
        } catch (e: RuntimeException) {
            setInput(null)
            setOutput("Неверная запись")
        } catch (e: NullPointerException) {
            setInput(null)
        } catch (e: ArithmeticException) {
            setInput(null)
            setOutput("${e.message}")
        } catch (e: IllegalArgumentException) {
            setInput(null)
            setOutput("ой")
        }
    }
    fun erase() {
        input.get()?.let {
            if (it.isNotEmpty()) setInput(it.substring(0, it.length - 1))
        }
    }
    fun clear() {
        afterEqual = false
        setInput(null)
        setOutput(null)
        previous = 0.0
    }
    fun result() {
        if (input.get() == null) return
        try {
            val result = Exec.exec(input.get()!!)
            if (isInteger(result)) setOutput(result.toInt().toString())
            else setOutput(result.toString())
            previous = result
            afterEqual = true
        } catch (e: RuntimeException) {
            setInput(null)
            setOutput("Неверная запись")
        } catch (e: NullPointerException) {
            setInput(null)
        } catch (e: ArithmeticException) {
            setInput(null)
            setOutput("${e.message}")
        } catch (e: IllegalArgumentException) {
            setInput(null)
            setOutput("ой")
        }
    }
    private fun isInteger(variable: Double): Boolean {
        return variable == floor(variable) &&
                !java.lang.Double.isInfinite(variable) &&
                !java.lang.Double.isNaN(variable) &&
                variable <= Int.MAX_VALUE &&
                variable >= Int.MIN_VALUE
    }
    private fun setInput(string: String?) {
        input.set(string)
    }
    private fun setOutput(string: String?) {
        output.set(string)
    }
}