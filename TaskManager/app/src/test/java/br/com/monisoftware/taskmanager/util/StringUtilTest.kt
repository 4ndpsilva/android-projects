package br.com.monisoftware.taskmanager.util

import org.junit.Assert.assertEquals
import org.junit.Test

class StringUtilTest {
    @Test
    fun stringShouldContainLetterOrDigitOnly() {
        val str = "ajahfdak28344263"
        assertEquals(true, StringUtil.isLetterOrDigitOnly(str))
    }
}
