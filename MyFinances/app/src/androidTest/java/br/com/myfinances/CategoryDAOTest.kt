package br.com.myfinances

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.myfinances.data.DatabaseApp
import br.com.myfinances.data.dao.CategoryDAO
import br.com.myfinances.data.entity.Category
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CategoryDAOTest {
    private lateinit var dao: CategoryDAO
    private lateinit var db: DatabaseApp

    @Before
    fun createDB(){
        val cxt = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(cxt, DatabaseApp::class.java).build()
        dao = db.categoryDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() = db.close()

    @Test
    @Throws(Exception::class)
    fun saveAll() = runBlocking {
        dao.save(Category(description = "Alimentação".toUpperCase()))
        dao.save(Category(description = "Transporte".toUpperCase()))
        dao.save(Category(description = "Estudos".toUpperCase()))

        Assert.assertEquals(3, dao.findAll().size)
    }

    @Test
    @Throws(Exception::class)
    fun writeCategoryAndReadInList() = runBlocking{
        val category = Category(description = "Alimentação".toUpperCase())
        dao.save(category)

        val list = dao.findAll()
        Assert.assertEquals(1, list.size)
    }
}