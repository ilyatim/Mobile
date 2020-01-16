package com.example.calculator.util

import com.example.calculator.data.*
import com.example.calculator.data.node.BinOpNode
import com.example.calculator.data.node.ExprNode
import com.example.calculator.data.node.NegativeNumberNode
import com.example.calculator.data.node.NumberNode

class Interpretor {
    fun eval(n: ExprNode): Int {
        return when (n) {
            is NumberNode -> Integer.parseInt(n.number.text)
            is NegativeNumberNode -> -1 * eval(n.number)
            is BinOpNode -> {
                val l = eval(n.left)
                val r = eval(n.right)
                when (n.op.type) {
                    TokenType.ADD -> l + r
                    TokenType.SUB -> l - r
                    TokenType.MUL -> l * r
                    TokenType.DIV -> {
                        if (r == 0) throw ArithmeticException("Деление на ноль")
                        else return l / r
                    }
                    else          -> throw IllegalArgumentException("Неверный TokenType")
                }
            }
        }
    }
}