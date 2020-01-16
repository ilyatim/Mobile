package com.example.calculator

import android.util.Log
import com.example.calculator.data.TokenType
import com.example.calculator.util.parser.Exec
import com.example.calculator.util.parser.Interpreter
import com.example.calculator.util.parser.Lexeme
import com.example.calculator.util.parser.Parser
import org.junit.Test

class TestExec {
    @Test
    fun exec() {
        val value = "44.1"
        val tokens = Lexeme(value).getLex()
        tokens.removeAll { t -> t.type == TokenType.SPACE }
        println(Interpreter().eval(Parser(tokens).parse()).toString())
    }
}