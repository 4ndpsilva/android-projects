package br.com.mynotes.dao

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.mynotes.data.DatabaseApp
import br.com.mynotes.data.dao.NoteDAO
import br.com.mynotes.data.domain.Note
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class NoteDAOTest {
    private lateinit var dao: NoteDAO
    private lateinit var cxt: Context

    @Before
    fun setUp(){
        cxt = InstrumentationRegistry.getInstrumentation().targetContext
        val database = Room.inMemoryDatabaseBuilder(cxt, DatabaseApp::class.java).build()
        dao = database.noteDAO()
    }

    @Test
    fun shouldSaveNotes(){
        val note1 = Note(
            description = "nota 1",
            date = Calendar.getInstance()
        )
        val note2 = Note(
            description = "nota 2",
            date = Calendar.getInstance()
        )
        val notes = listOf(note1, note2)

        val ids = dao.saveAll(notes)

        assertEquals(2, ids.size)
    }
}