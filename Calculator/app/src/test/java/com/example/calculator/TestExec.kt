package com.example.calculator

import android.util.Log
import com.example.calculator.data.TokenType
import com.example.calculator.util.parser.Exec
import com.example.calculator.util.parser.Interpreter
import com.example.calculator.util.parser.Lexeme
import com.example.calculator.util.parser.Parser
import org.junit.Test
import kotlin.math.floor

class TestExec {
    @Test
    fun fullExec() {
        val value = "44.1 + 1"
        val tokens = Lexeme(value).getLex()
        tokens.removeAll { t -> t.type == TokenType.SPACE }
        println(Interpreter().eval(Parser(tokens).parse()).toString())
    }
    @Test
    fun checkDouble() {
        println(isInteger(12.000000000000))
    }
    @Test
    fun checkParser() {
        val value = "44.1 + 1"
        val tokens = Lexeme(value).getLex()
        tokens.removeAll { t -> t.type == TokenType.SPACE }
        println(Parser(tokens).parse().toString())
    }
    @Test
    fun largeExpression() {
        val value = "1 + 2 * 3 / 2 + 2 + (1 + 2)"
        val tokens = Lexeme(value).getLex()
        tokens.removeAll { t -> t.type == TokenType.SPACE }
        val finalValue = Interpreter().eval(Parser(tokens).parse())
        println(if (isInteger(finalValue)) finalValue.toInt() else finalValue)
    }
    @Test
    fun veryLargeExpression() {
        val value = "3 * 3 * 3 * 3 * 3 * 3 * 3 * 0"
        val tokens = Lexeme(value).getLex()
        tokens.removeAll { t -> t.type == TokenType.SPACE }
        val finalValue = Interpreter().eval(Parser(tokens).parse())
        println(if (isInteger(finalValue)) finalValue.toInt() else finalValue)
    }
    fun isInteger(variable: Double): Boolean {
        return variable == floor(variable) &&
                !java.lang.Double.isInfinite(variable) &&
                !java.lang.Double.isNaN(variable) &&
                variable <= Int.MAX_VALUE &&
                variable >= Int.MIN_VALUE
    }
}