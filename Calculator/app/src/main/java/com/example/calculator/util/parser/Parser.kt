package com.example.calculator.util.parser

import com.example.calculator.data.*
import com.example.calculator.data.node.BinOpNode
import com.example.calculator.data.node.ExprNode
import com.example.calculator.data.node.NegativeNumberNode
import com.example.calculator.data.node.NumberNode

class Parser(private val tokens: List<Token>) {
    private var pos = 0

    fun parse(): ExprNode = expression()

    private fun expression(): ExprNode {
        var e1 = addend()
        var token: Token?
        while (true) {
            token = match(TokenType.ADD, TokenType.SUB)
            if (token != null) {
                val e2 = addend()
                e1 = BinOpNode(token, e1, e2)
                continue
            }
            break
        }
        return e1
    }
    private fun addend() : ExprNode {
        var e1 = multiplier()
        var token: Token?
        while (true) {
            token = match(TokenType.MUL, TokenType.DIV)
            if (token != null) {
                val e2 = multiplier()
                e1 = BinOpNode(token, e1, e2)
                continue
            }
            break
        }
        return e1
    }
    private fun multiplier(): ExprNode
    {
        return if (match(TokenType.LPAR) != null) {
            val expNode = parse()
            require(TokenType.RPAR)
            expNode
        } else {
            elem()
        }
    }
    private fun elem(): ExprNode
    {
        val token = match(TokenType.NUMBER, TokenType.SUB)
        token?.let {
            when (token.type) {
                TokenType.NUMBER -> {
                    return NumberNode(it)
                }
                TokenType.SUB    -> {
                    val e1 = multiplier()
                    return NegativeNumberNode(e1)
                }
                else             -> {
                    return@let
                }
            }
        }
        error("Ожидалось число или id")
    }
    private fun match(vararg types: TokenType): Token? {
        if (pos < tokens.size) {
            val token: Token = tokens[pos]
            if (types.contains(token.type)) {
                pos++
                return token
            }
        }
        return null
    }
    private fun require(vararg types: TokenType): Token = match(*types) ?: error("Ожидался ${types.contentToString()}")
    private fun error(message: String): Nothing = throw RuntimeException("$message в $pos позиции")
}
