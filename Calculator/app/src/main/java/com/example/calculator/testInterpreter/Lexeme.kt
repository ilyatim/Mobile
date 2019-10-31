package com.example.calculator.testInterpreter

import com.example.calculator.testInterpreter.Token
import java.util.regex.Matcher
import java.lang.RuntimeException

class Lexeme(private val src: String)
{
    var pos = 0
    private val tokens: MutableList<Token> = ArrayList()

    private fun nextToken(): Boolean
    {
        if(pos >= src.length)
        {
            return false
        }
        for(tt: TokenType in TokenType.values())
        {
            val m: Matcher = tt.pattern.matcher(src)
            m.region(pos, src.length)
            if(m.lookingAt())
            {
                val t = Token(tt, m.group(), pos)
                tokens.add(t)
                pos = m.end()
                return true
            }
        }
        throw RuntimeException("unknown symbol")
    }
    fun getLex(): MutableList<Token>
    {
        while(nextToken()) {}
        return tokens
    }

}