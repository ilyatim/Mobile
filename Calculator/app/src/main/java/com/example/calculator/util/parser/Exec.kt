package com.example.calculator.util.parser

import com.example.calculator.data.TokenType

object Exec {
    fun exec(value: String): Double {
        val tokens = Lexeme(value).getLex()
        tokens.removeAll { t -> t.type == TokenType.SPACE }
        return Interpreter().eval(Parser(tokens).parse())
    }
}