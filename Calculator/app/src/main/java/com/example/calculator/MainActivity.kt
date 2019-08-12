package com.example.calculator

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.calc_buttons.*
import kotlinx.android.synthetic.main.calc_output.*

class MainActivity : AppCompatActivity(), View.OnClickListener
{
    var action: String = ""
    var calcOutput: Double = 0.0
    var firstNumber: String = ""
    var secondNumber: String = ""
    var turnFirst = true
    var afterAction = false


    fun plus(firstNum: Double, secondNum: Double) = firstNum + secondNum  //сложение чисел
    fun minus(firstNum: Double, secondNum: Double) = firstNum - secondNum //разность чисел
    fun multiply(firstNum: Double, secondNum: Double) = firstNum * secondNum  //умножение чисел
    fun share(firstNum: Double, secondNum: Double) = firstNum / secondNum     //деление чисел
    fun percent(firstNum: Double, secondNum: Double): Double    //нахождение процентов от числа
    {
        return firstNum / 100 * secondNum
    }
    fun changeTheSign(num: Double): Double    //смена знака числа
    {
        return -1 * num
    }

    private fun clear()
    {
        action = ""
        calcOutput = 0.0
        firstNumber = ""
        secondNumber = ""
        outputTextView.text = ""
        userInputTextView.text = ""
        turnFirst = true
        afterAction = false
    }
    private fun addToNumber(num: Int)
    {
        if(turnFirst)
        {
            if(afterAction)
            {
                firstNumber = num.toString()
                afterAction = false
            }
            else
            {
                firstNumber += num
            }
        }
        else
        {
            secondNumber += num
        }
        outputTextView.text = ""
    }
    private fun addToNumber(num: String)
    {
        if(turnFirst)
        {
            if(afterAction)
            {
                firstNumber = num
                afterAction = false
            }
            else
            {
                firstNumber += num
            }
        }
        else
        {
            secondNumber += num
        }
        outputTextView.text = ""
    }
    @SuppressLint("SetTextI18n")
    private fun showUserInput()
    {
        userInputTextView.text = firstNumber + action + secondNumber
    }
    private fun showOutputText()
    {
        userInputTextView.text = ""
        outputTextView.text = calcOutput.toString()
    }
    fun function()  //вывод информации в textview и дальшейнее ее запоминание
    {
        showOutputText()
        firstNumber = calcOutput.toString()
        action = ""
        secondNumber = ""
        afterAction = true
    }
    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?)
    {
        when(v?.id)
        {
            //Functional buttons
            R.id.button_clear -> {
                clear()
            }
            R.id.button_opposite -> {
                try
                {
                    calcOutput = changeTheSign(firstNumber.toDouble())
                    function()
                }
                catch (e: NumberFormatException)
                {
                    outputTextView.text = "Wrong number"
                }
            }
            R.id.button_percent -> {
                action = "%"
                turnFirst = false
                showUserInput()
            }
            R.id.button_share -> {
                action = "/"
                turnFirst = false
                showUserInput()
            }
            R.id.button_multiply -> {
                action = "*"
                turnFirst = false
                showUserInput()
            }
            R.id.button_minus -> {
                action = "-"
                turnFirst = false
                showUserInput()
            }
            R.id.button_plus -> {
                action = "+"
                turnFirst = false
                showUserInput()
            }
            R.id.button_equally ->{
                try
                {
                    when(action)
                    {

                        "%" -> {
                            calcOutput = percent(firstNumber.toDouble(), secondNumber.toDouble())
                        }
                        "/" -> {
                            calcOutput = share(firstNumber.toDouble(), secondNumber.toDouble())
                        }
                        "*" -> {
                            calcOutput = multiply(firstNumber.toDouble(), secondNumber.toDouble())
                        }
                        "-" -> {
                            calcOutput = minus(firstNumber.toDouble(), secondNumber.toDouble())
                        }
                        "+" -> {
                            calcOutput = plus(firstNumber.toDouble(), secondNumber.toDouble())
                        }
                        "" -> {
                            calcOutput = firstNumber.toDouble()
                        }
                    }
                    function()
                    turnFirst = true
                }
                catch (e: java.lang.NumberFormatException)
                {
                    outputTextView.text = "Wrong number"
                }
            }
            //Numbers
            R.id.button_one -> {
                addToNumber(1)
                showUserInput()
            }
            R.id.button_two -> {
                addToNumber(2)
                showUserInput()
            }
            R.id.button_three -> {
                addToNumber(3)
                showUserInput()
            }
            R.id.button_four -> {
                addToNumber(4)
                showUserInput()
            }
            R.id.button_five -> {
                addToNumber(5)
                showUserInput()
            }
            R.id.button_six -> {
                addToNumber(6)
                showUserInput()
            }
            R.id.button_seven -> {
                addToNumber(7)
                showUserInput()
            }
            R.id.button_eight -> {
                addToNumber(8)
                showUserInput()
            }
            R.id.button_nine -> {
                addToNumber(9)
                showUserInput()
            }
            R.id.button_zero -> {
                addToNumber(0)
                showUserInput()
            }
            R.id.button_point -> {
                addToNumber(".")
                showUserInput()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnClickListenerForButtons()
    }

    private fun setOnClickListenerForButtons()
    {
        //Functional buttons
        button_clear.setOnClickListener(this)
        button_opposite.setOnClickListener(this)
        button_percent.setOnClickListener(this)
        button_share.setOnClickListener(this)
        button_multiply.setOnClickListener(this)
        button_minus.setOnClickListener(this)
        button_plus.setOnClickListener(this)
        button_equally.setOnClickListener(this)
        button_point.setOnClickListener(this)
        //Numbers
        button_one.setOnClickListener(this)
        button_two.setOnClickListener(this)
        button_three.setOnClickListener(this)
        button_four.setOnClickListener(this)
        button_five.setOnClickListener(this)
        button_six.setOnClickListener(this)
        button_seven.setOnClickListener(this)
        button_eight.setOnClickListener(this)
        button_nine.setOnClickListener(this)
        button_zero.setOnClickListener(this)

    }
}
