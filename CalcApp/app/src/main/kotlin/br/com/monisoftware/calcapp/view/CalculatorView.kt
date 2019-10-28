package br.com.monisoftware.calcapp.view

interface CalculatorView{
    fun setTextValue(value: String, typeField: Int)
    fun getTextValue(typeField: Int): String
    fun append(value: String, typeField: Int)
    fun showMessage(typeMSg: Int)
}