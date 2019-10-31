package com.example.calculator.testInterpreter

class Parser(private val tokens: List<Token>)
{

    private var pos = 0

    object Result
    {
        fun eval(n: ExprNode): Int
        {
            return when(n)
            {
                is NumberNode -> Integer.parseInt(n.number.text)
                is VarNode -> Integer.parseInt(n.id.text)
                is NegativeNumberNode -> -1 * eval(n.number)
                is BinOpNode -> {
                    val l = eval(n.left)
                    val r = eval(n.right)
                    when(n.op.type)
                    {
                        TokenType.ADD -> l + r
                        TokenType.SUB -> l - r
                        TokenType.MUL -> l * r
                        TokenType.DIV -> {
                            if(r == 0)
                            {
                                throw ArithmeticException("Деление на ноль")
                            }
                            else
                            {
                                return l / r
                            }
                        }
                        else -> throw IllegalArgumentException("Неверный TokenType")
                    }
                }
            }
        }
    }

    private fun match(vararg types: TokenType): Token?
    {
        if(pos < tokens.size)
        {
            val token: Token = tokens[pos]
            if(types.contains(token.type))
            {
                pos++
                return token
            }
        }
        return null
    }

    private fun require(vararg types: TokenType): Token
    {
        return match(*types) ?: error("Ожидался ${types.contentToString()}")
    }

    private fun error(message: String): Nothing
    {
        throw RuntimeException("$message в $pos позиции")
    }

    fun parse(): ExprNode
    {
        var e1 = addend()
        var token: Token?
        while(true)
        {
            token = match(TokenType.ADD, TokenType.SUB)
            if(token != null)
            {
                val e2 = addend()
                e1 = BinOpNode(token, e1, e2)
                continue
            }
            break
        }
        return e1
    }
    private fun addend(): ExprNode
    {
        var e1 = multiplier()
        var token: Token?
        while(true)
        {
            token = match(TokenType.MUL, TokenType.DIV)
            if(token != null)
            {
                val e2 = multiplier()
                e1 = BinOpNode(token, e1, e2)
                continue
            }
            break
        }
        return e1
    }
    private fun elem(): ExprNode
    {
        val n = match(TokenType.NUMBER)
        if(n != null)
        {
            return NumberNode(n)
        }
        val negNum = match(TokenType.NEGNUMBER)
        if(negNum != null)
        {
            return NumberNode(negNum)
        }
        val neg = match(TokenType.SUB)
        if(neg != null)
        {
            val e1 = parse()
            return NegativeNumberNode(e1)
        }
        val id = match(TokenType.ID)
        if(id != null)
        {
            return VarNode(id)
        }
        error("Ожидалось число или id")
    }
    private fun multiplier(): ExprNode
    {
        return if(match(TokenType.LPAR) != null)
        {
            val expNode = parse()
            require(TokenType.RPAR)
            expNode
        }
        else
        {
            elem()
        }
    }
}
