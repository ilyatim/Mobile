package com.example.calculator.testInterpreter

import java.util.regex.Pattern

enum class TokenType(regExp: String)
{
    NEGNUMBER("(-)[0-9]+"),
    NUMBER("[0-9]+"),
    ID("[a-zA-z]"),
    ADD("\\+"),
    SUB("-"),
    MUL("\\*"),
    DIV("/"),
    LPAR("\\("),
    RPAR("\\)"),
    SPACE("[ \t\r\n]+");
    val pattern: Pattern = Pattern.compile(regExp)

}
