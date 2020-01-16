package com.example.calculator.util.parser

import com.example.calculator.data.Token
import com.example.calculator.data.TokenType
import java.util.regex.Matcher
import java.lang.RuntimeException

class Lexeme(private val src: String) {
    private var pos = 0
    private val tokens: MutableList<Token> = ArrayList()

    fun getLex(): MutableList<Token> {
        while (nextToken()) {}
        return tokens
    }
    private fun nextToken(): Boolean {
        if (pos >= src.length) return false

        for (tt: TokenType in TokenType.values()){
            val m: Matcher = tt.pattern.matcher(src)
            m.region(pos, src.length)
            if (m.lookingAt()) {
                val t = Token(tt, m.group(), pos)
                tokens.add(t)
                pos = m.end()
                return true
            }
        }
        throw RuntimeException("unknown symbol")
    }
}