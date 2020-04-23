package br.com.tasklist.util

import android.content.Context
import android.widget.Toast

fun Context.toast(cxt: Context, msg: Int) = Toast.makeText(cxt, cxt.getString(msg), Toast.LENGTH_LONG).show()
fun Context.toast(cxt: Context, msg: String) = Toast.makeText(cxt, msg, Toast.LENGTH_LONG).show()