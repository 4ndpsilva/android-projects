package br.com.mynotes.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var description: String = "",
    var date: Calendar = Calendar.getInstance()) : Parcelable