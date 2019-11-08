package br.com.monisoftware.tasklist.util

import org.junit.Assert.assertEquals
import org.junit.Test

class StringUtilTest {
    @Test
    fun shouldContainLetterOrDigitOnly() {
        val str = "ajahfdak28344263"
        assertEquals(true, StringUtil.isLetterOrDigitOnly(str))
    }
}