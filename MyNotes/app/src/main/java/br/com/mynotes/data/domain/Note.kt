package br.com.mynotes.data.domain

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class Note(
    var description: String = "",
    var date: Calendar = Calendar.getInstance()) : BaseEntity(), Parcelable