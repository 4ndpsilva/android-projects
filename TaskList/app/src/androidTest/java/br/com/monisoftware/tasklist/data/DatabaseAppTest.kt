package br.com.monisoftware.tasklist.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseAppTest {

    @Test
    fun shouldConnect(){
        val database = DatabaseApp(InstrumentationRegistry.getInstrumentation().context)
        Assert.assertNotNull(database)
    }

    @Test
    fun shouldConnectAndGetAnyDAO() {
        val database = DatabaseApp(InstrumentationRegistry.getInstrumentation().context)
        Assert.assertNotNull(database.getDAO())
    }
}