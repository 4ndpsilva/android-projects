package br.com.monisoftware.calcapp.model

import br.com.monisoftware.calcapp.util.StringUtil
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigDecimal
import kotlin.math.sqrt

class CalculatorModel{
    private val mapReplace = mapOf("," to ".", "X" to "*", "÷" to "/")

    fun doCalculate(expression: String): Number{
        var result = 0.0

        try {
            val exp = ExpressionBuilder(StringUtil.formatExpression(expression, mapReplace)).build()
            val validate = exp.validate()

            if(validate.isValid) {
                result = exp.evaluate()
            }
        }
        catch (ex: ArithmeticException){
            throw ArithmeticException()
        }

        return BigDecimal(result)
    }

    fun doSquareRoot(value: String): Number{
        val strNumber = StringUtil.formatExpression(value, mapReplace)
        val number = strNumber.toDouble()
        return if(number >= 0.0) BigDecimal(sqrt(number)) else throw IllegalArgumentException()
    }
}