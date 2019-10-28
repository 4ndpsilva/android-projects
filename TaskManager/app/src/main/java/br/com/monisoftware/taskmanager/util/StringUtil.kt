package br.com.monisoftware.taskmanager.util

object StringUtil{
    fun isLetterOrDigitOnly(value: String): Boolean{
        return value.contains("^[a-zA-Z0-9]+$".toRegex())
    }
}