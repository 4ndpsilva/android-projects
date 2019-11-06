package br.com.monisoftware.taskmanager.data.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.monisoftware.taskmanager.data.DatabaseApp
import br.com.monisoftware.taskmanager.data.entity.Task
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskDAOTest {
    private lateinit var cxt: Context
    private lateinit var database: DatabaseApp
    private lateinit var dao: TaskDAO

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        cxt = InstrumentationRegistry.getInstrumentation().context
        database = DatabaseApp.inMemoryDatabase(cxt)
        dao = database.getDAO()

        dao.save(Task(description = "Lavar o carro", done = false))
        dao.save(Task(description = "Estudar para prova", done = false))
        dao.save(Task(description = "Pintar a sala", done = false))
    }

    @After
    fun closeDB() = database.close()

    @Test
    fun saveAndGetTask(){
        val task = Task(description = "Limpar a casa", done = false)
        task.id = dao.save(task)

        val taskFind = dao.findById(task.id)
        Assert.assertNotNull(taskFind)
    }

    @Test
    fun deleteAndFetchDeletedTask(){
        val task = Task(id = 1)
        dao.delete(task)
        Assert.assertNull(dao.findById(1))
    }

    @Test
    fun updateAndGetTask(){
        val description = "Lavar o carro"
        val task = Task(1, description, true)
        dao.update(task)
        val taskFind = dao.findById(task.id)

        Assert.assertEquals(description, taskFind?.description)
    }

    @Test
    fun fetchTaskById(){
        val task = dao.findById(3)
        Assert.assertNotNull(task)
    }

    @Test
    fun fetchAllTasks(){
        /*dao.findAll().observeOnce { tasks ->
            Assert.assertEquals(true, tasks.isNotEmpty())
        }*/

        Assert.assertEquals(true, dao.findAll().isNotEmpty())
    }

    @Test
    fun fetchQuantityPendingTasks(){
        val quantity = dao.getRemainingTasks(false)
        Assert.assertEquals(3, quantity)
    }

    @Test
    fun fetchQuantityDoneTasks(){
        dao.update(Task(id = 1, done = true))
        val quantity = dao.getRemainingTasks(true)
        Assert.assertEquals(1, quantity)
    }

    private fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit){
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }
}