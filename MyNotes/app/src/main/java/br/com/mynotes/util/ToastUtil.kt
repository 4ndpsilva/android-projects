package br.com.mynotes.util

import android.content.Context
import android.widget.Toast

fun Context.toast(msg: Int) = Toast.makeText(this, this.getString(msg), Toast.LENGTH_LONG).show()

fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()