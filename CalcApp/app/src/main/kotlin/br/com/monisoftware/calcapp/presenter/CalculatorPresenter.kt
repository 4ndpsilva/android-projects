package br.com.monisoftware.calcapp.presenter

import androidx.core.text.isDigitsOnly
import br.com.monisoftware.calcapp.util.Constants
import br.com.monisoftware.calcapp.util.Fields
import br.com.monisoftware.calcapp.model.CalculatorModel
import br.com.monisoftware.calcapp.util.StringUtil
import br.com.monisoftware.calcapp.view.CalculatorView

class CalculatorPresenter(private var view: CalculatorView) {
    private val calculator     = CalculatorModel()
    private var lastIsNumber   = true
    private var lastIsOperator = false
    private var lastIsDot      = false
    private var calculated     = false
    private var equalClicked   = false
    private var operator       = ""
    private var op1            = ""
    private var op2            = ""

    fun doEqual(){
        if(op1.isEmpty()) {
            op1 = "0"
        }
        else if(op2.isEmpty() || containsOperator(view.getTextValue(Fields.EXPRESSION))){
            op2 = view.getTextValue(Fields.VALUE)
        }

        if(canCalculate()) {
            doCalculate()
            view.setTextValue("", Fields.EXPRESSION)
            equalClicked = true
        }
    }

    private fun doCalculate(){
        try{
            val expression = op1 + operator + op2
            val result = calculator.doCalculate(expression)
            view.setTextValue(result.toString().replace(".", ","), Fields.VALUE)
            op1 = view.getTextValue(Fields.VALUE)
            calculated = true
            lastIsNumber = true
        }
        catch (ex: ArithmeticException) {
            view.showMessage(Constants.MSG_DIVIDE_BY_ZERO)
            clear()
        }
    }

    fun doSqrt(value: String){
        try {
            if (value.isNotEmpty()) {
                val result = calculator.doSquareRoot(value)
                view.setTextValue(formatResult(result.toString()), Fields.VALUE)
                lastIsNumber = true
                lastIsDot = view.getTextValue(Fields.VALUE).contains(",")
                op1 = view.getTextValue(Fields.VALUE)
                op2 = op1
            }
        }
        catch(ex: IllegalArgumentException){
            view.showMessage(Constants.MSG_INVALID_OPERATION)
            op1 = ""
            op2 = ""
        }

        calculated = true
    }

    fun doDigit(value: String){
        val v = view.getTextValue(Fields.VALUE)

       if(v == "0" || calculated){
            if(calculated){
                op2 = ""
                operator = ""
            }
            view.setTextValue("", Fields.VALUE)
            calculated = false
            lastIsDot = false
        }

        if(lastIsOperator){
            if(op2.isEmpty()){
                view.setTextValue("", Fields.VALUE)
            }
            view.append(value, Fields.VALUE)
            op2 = view.getTextValue(Fields.VALUE)
        }
        else{
            view.append(value, Fields.VALUE)
            op1 = view.getTextValue(Fields.VALUE)
            lastIsOperator = false
        }

        lastIsNumber = true
    }

    fun doDecimalPoint(value: String){
        if(lastIsNumber && !lastIsDot) {
            lastIsDot      = true
            lastIsNumber   = false
            lastIsOperator = false
            view.append(value, Fields.VALUE)
        }
        else if(lastIsOperator){
            op2 = view.getTextValue(Fields.VALUE)
        }
    }

    fun doOperator(operator: String){
        if(lastIsNumber) {
            lastIsOperator = true
            lastIsDot      = false
            lastIsNumber   = false
            view.append(view.getTextValue(Fields.VALUE), Fields.EXPRESSION)
            view.append(operator, Fields.EXPRESSION)

            if(equalClicked){
                op2 = view.getTextValue(Fields.VALUE)
            }
        }
        else if(lastIsOperator){
            changeOperator(operator)
            op2 = ""
        }

        if(!equalClicked && canCalculate()){
            doCalculate()
            equalClicked = false
            this.operator = operator
        }
        else{
            this.operator = operator
        }
    }

    fun doBack(){
        if(canClear()) {
            var value = view.getTextValue(Fields.VALUE)

            if (calculated) {
                clear()
            }
            else if (value.isNotEmpty()) {
                value = value.substring(0, value.length - 1)
                view.setTextValue(value, Fields.VALUE)

                when(lastIsOperator){
                    true -> op2 = value
                    else -> op1 = value
                }

                if (value.isNotEmpty()) {
                    value = value[value.lastIndex].toString()
                    lastIsNumber = value.isDigitsOnly()
                    lastIsDot = !StringUtil.isDigitOnly(value)
                }

                if (value.isEmpty()) {
                    clear()
                }
            }
        }
    }

    fun clear(){
        view.setTextValue("0", Fields.VALUE)
        lastIsNumber   = true
        lastIsDot      = false
        lastIsOperator = false
        calculated     = false
        equalClicked   = false
        operator       = ""
        op1            = ""
        op2            = ""
    }

    private fun canCalculate() = op1.isNotEmpty() && op2.isNotEmpty() && operator.isNotEmpty()

    private fun canClear(): Boolean{
        return view.getTextValue(Fields.EXPRESSION).isEmpty() ||
                !lastIsOperator ||
                !calculated
    }

    private fun changeOperator(operator: String){
        var str = view.getTextValue(Fields.EXPRESSION)
        str = str.substring(0, str.length - 1)
        view.setTextValue(str, Fields.EXPRESSION)
        view.append(operator, Fields.EXPRESSION)
        this.operator = operator
    }

    private fun formatResult(value: String): String {
        if(value.length > 13) {
            return value.substring(0, 13).replace(".", ",")
        }

        return value.replace(".", ",")
    }

    private fun containsOperator(str: String): Boolean{
        var status = false
        if(str.isNotEmpty()){
           status = !StringUtil.isDigitOnly(str[str.lastIndex].toString())
        }

        return status
    }
}