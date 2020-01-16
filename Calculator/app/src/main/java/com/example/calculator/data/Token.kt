package com.example.calculator.data


/**
 * @constructor
 * @param type - TokenType of current Token
 * @param text - value stored in Token
 * @param index - index in expression
 */
data class Token(val type: TokenType, val text: String, var index: Int)
