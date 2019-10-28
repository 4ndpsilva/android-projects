package br.com.monisoftware.calcapp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.monisoftware.calcapp.R
import br.com.monisoftware.calcapp.util.Constants
import br.com.monisoftware.calcapp.util.Fields
import br.com.monisoftware.calcapp.presenter.CalculatorPresenter
import br.com.monisoftware.calcapp.util.DialogUtil
import br.com.monisoftware.calcapp.view.CalculatorView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CalculatorView {
    private val presenter = CalculatorPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.clear()
        initActions()
    }

    private fun initActions(){
        btn0.setOnClickListener{ presenter.doDigit(btn0.text.toString()) }
        btn1.setOnClickListener{ presenter.doDigit(btn1.text.toString()) }
        btn2.setOnClickListener{ presenter.doDigit(btn2.text.toString()) }
        btn3.setOnClickListener{ presenter.doDigit(btn3.text.toString()) }
        btn4.setOnClickListener{ presenter.doDigit(btn4.text.toString()) }
        btn5.setOnClickListener{ presenter.doDigit(btn5.text.toString()) }
        btn6.setOnClickListener{ presenter.doDigit(btn6.text.toString()) }
        btn7.setOnClickListener{ presenter.doDigit(btn7.text.toString()) }
        btn8.setOnClickListener{ presenter.doDigit(btn8.text.toString()) }
        btn9.setOnClickListener{ presenter.doDigit(btn9.text.toString()) }
        btnDiv.setOnClickListener{ presenter.doOperator("\u00F7") }
        btnMultip.setOnClickListener{ presenter.doOperator("X") }
        btnSub.setOnClickListener{ presenter.doOperator("-") }
        btnAdd.setOnClickListener{ presenter.doOperator("+") }
        btnEqual.setOnClickListener{ presenter.doEqual() }
        btnSqrt.setOnClickListener{ presenter.doSqrt(tvValue.text.toString()) }
        btnDecimal.setOnClickListener{ presenter.doDecimalPoint(btnDecimal.text.toString()) }
        btnC.setOnClickListener{ presenter.clear(); tvExpression.text = "" }
        btnBack.setOnClickListener{ presenter.doBack() }
        btnClose.setOnClickListener{ onExit() }
    }

    override fun showMessage(typeMSg: Int) {
        var msg = 0

        when(typeMSg){
            Constants.MSG_DIVIDE_BY_ZERO -> msg = R.string.errorDivisionByZero
            Constants.MSG_INVALID_OPERATION -> msg = R.string.invalidOperation
        }

        DialogUtil.showMessage(this, msg)
    }

    override fun getTextValue(typeField: Int): String{
        return when(typeField){
            Fields.VALUE -> tvValue.text.toString()
            else -> tvExpression.text.toString()
        }
    }

    override fun setTextValue(value: String, typeField: Int) {
        when(typeField){
            Fields.VALUE -> tvValue.text = value
            else -> tvExpression.text = value
        }
    }

    override fun append(value: String, typeField: Int) {
        when(typeField){
            Fields.VALUE -> tvValue.append(value)
            else -> tvExpression.append(value)
        }
    }

    override fun onBackPressed() {
        if("0" != tvValue.text.toString() && tvValue.text.isNotEmpty()){
            DialogUtil.showConfirm(this, R.string.confirmExit)
        }
        else{
            super.onBackPressed()
        }
    }

    private fun onExit(){
        if("0" != tvValue.text.toString() && tvValue.text.isNotEmpty()){
            DialogUtil.showConfirm(this, R.string.confirmExit)
        }
        else{
            finish()
        }
    }
}