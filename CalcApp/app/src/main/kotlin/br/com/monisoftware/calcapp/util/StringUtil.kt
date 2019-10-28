package br.com.monisoftware.calcapp.util

object StringUtil {
    fun isDigitOnly(value: String): Boolean{
        var hasDigit = false
        value.forEach { hasDigit = it.isDigit() }
        return hasDigit
    }

    fun formatExpression(exp: String, mapReplace: Map<String, String>): String{
        var expression = exp.replace(" ", "")

        for(map: Map.Entry<String, String> in mapReplace){
            expression = expression.replace(map.key, map.value)
        }

        return expression
    }
}