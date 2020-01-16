package com.example.calculator.data

import java.util.regex.Pattern

enum class TokenType(regExp: String)
{
    NUMBER("\\d+\\.?\\d?+"),
    //NUMBER("[0-9]+"),
    ADD("\\+"),
    SUB("-"),
    MUL("\\*"),
    DIV("/"),
    LPAR("\\("),
    RPAR("\\)"),
    SPACE("[ \t\r\n]+");
    val pattern: Pattern = Pattern.compile(regExp)

}
