package br.com.monisoftware.calcapp

import br.com.monisoftware.calcapp.model.CalculatorModel
import br.com.monisoftware.calcapp.util.StringUtil
import org.junit.Assert.*
import org.junit.Test


class CalculatorModelTest {
    private val calculator = CalculatorModel()
    private val mapReplace = mapOf("," to ".", "X" to "*", "÷" to "/")

    @Test
    fun calculateDivideOperation() {
        val result = calculator.doCalculate(StringUtil.formatExpression("10÷2", mapReplace))
        assertEquals(5.0, result.toDouble(), 0.0)
    }

    @Test
    fun calculateDivideByZeroOperation() {
        val result = calculator.doCalculate(StringUtil.formatExpression("10÷1", mapReplace))
        assertEquals(10.0, result.toDouble(), 0.0)
    }

    @Test
    fun calculateMultiplyOperation() {
        val result = calculator.doCalculate(StringUtil.formatExpression("10X10", mapReplace))
        assertEquals(100.0, result.toDouble(), 0.0)
    }

    @Test
    fun calculatePlusOperation() {
        val result = calculator.doCalculate(StringUtil.formatExpression("10+10", mapReplace))
        assertEquals(20.0, result.toDouble(), 0.0)
    }

    @Test
    fun calculateSubtractOperation() {
        val result = calculator.doCalculate(StringUtil.formatExpression("10-10", mapReplace))
        assertEquals(0.0, result.toDouble(), 0.0)
    }

    @Test
    fun calculateSquareRootOfNumber(){
        val result = calculator.doSquareRoot(StringUtil.formatExpression("64", mapReplace))
        assertEquals(8.0, result.toDouble(), 0.0)
    }

    @Test
    fun calculateSquareRootOfNegativeNumber(){
        val result = calculator.doSquareRoot(StringUtil.formatExpression("81", mapReplace))
        assertEquals(9.0, result.toDouble(), 0.0)
    }
}