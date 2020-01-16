package com.example.calculator.util.viewModel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.calculator.util.parser.Exec
import java.lang.ArithmeticException
import java.lang.Exception
import java.lang.NullPointerException
import java.lang.RuntimeException

class MainViewModel : ViewModel() {
    var input = ObservableField<String>()
    var output = ObservableField<String>()

    private var previous: Double? = null

    fun add(value: String) {
        if (input.get() == null) input.set(value)
        else input.set("${input.get()} $value")
    }
    fun changeSign() {

    }
    fun clear() {
        inputClear()
        setOutput("")
    }
    fun result() {
        try {
            output.set(Exec.exec(input.get()!!).toString())
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
    private fun inputClear() {
        input.set("")
    }
    private fun setOutput(string: String) {
        output.set(string)
    }
}