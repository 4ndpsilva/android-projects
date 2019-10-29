package br.com.monisoftware.taskmanager.util

object StringUtil{
    fun isLetterOrDigitOnly(value: String): Boolean{
        return value.contains(Constants.REGEX_LETTER_DIGIT.toRegex())
    }
}