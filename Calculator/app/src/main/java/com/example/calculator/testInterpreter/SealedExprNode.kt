package com.example.calculator.testInterpreter

import com.example.calculator.testInterpreter.Token

sealed class ExprNode

data class BinOpNode(var op: Token, var left: ExprNode, var right: ExprNode) : ExprNode()
data class NumberNode(var number: Token) : ExprNode()
data class VarNode(var id: Token) : ExprNode()
data class NegativeNumberNode(var number: ExprNode) : ExprNode()