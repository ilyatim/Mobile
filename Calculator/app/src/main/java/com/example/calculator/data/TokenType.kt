package com.example.calculator.data

import java.util.regex.Pattern

enum class TokenType(regExp: String) {
    NUMBER("\\d+\\.?\\w?+"),
    //TODO change NUMBER to read numbers with E
    //NUMBER("[0-9]+"),
    ADD("\\+"),
    SUB("-"),
    MUL("\\*"),
    DIV("/"),
    PERSENT("%"),
    LPAR("\\("),
    RPAR("\\)"),
    SPACE("[ \t\r\n]+");
    val pattern: Pattern = Pattern.compile(regExp)
}
