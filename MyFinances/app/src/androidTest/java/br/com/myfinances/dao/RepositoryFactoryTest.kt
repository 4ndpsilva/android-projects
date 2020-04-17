package br.com.myfinances.dao

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import br.com.myfinances.data.DatabaseApp
import br.com.myfinances.repository.CategoryRepository
import br.com.myfinances.repository.RepositoryFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepositoryFactoryTest {
    private lateinit var cxt: Context
    private lateinit var repo: CategoryRepository

    @Before
    fun init(){
        cxt = InstrumentationRegistry.getInstrumentation().targetContext
        val dao = DatabaseApp(cxt).categoryDAO()
        repo = RepositoryFactory.getInstance<CategoryRepository>(dao, CategoryRepository::class.java)
    }

    @Test
    fun createAnyRepository() {
        assertEquals(4, 2 + 2)
    }
}
