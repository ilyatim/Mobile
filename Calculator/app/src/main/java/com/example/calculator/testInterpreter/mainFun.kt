package com.example.calculator.testInterpreter

import android.os.Build
import android.support.annotation.RequiresApi

/*@RequiresApi(Build.VERSION_CODES.N)
fun main()
{
    val text: String = "-(1 + 2) * 2"
    val l: Lexeme = Lexeme(text)
    val tokens = l.getLex()
    tokens.removeIf { t -> t.type == TokenType.SPACE }
    val p = Parser(tokens)
    val numbers: ExprNode = p.parse()
    val result = Parser.Result.eval(numbers)
    println(result)
}*/
