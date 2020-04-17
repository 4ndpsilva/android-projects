package br.com.mynotes.data.domain

import androidx.room.PrimaryKey

open class BaseEntity(@PrimaryKey(autoGenerate = true) var id: Long = 0)