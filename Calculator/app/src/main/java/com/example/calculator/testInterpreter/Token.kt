package com.example.calculator.testInterpreter

import ta_practice.util.TokenType

data class Token(val type: TokenType, val text: String, var index: Int)
