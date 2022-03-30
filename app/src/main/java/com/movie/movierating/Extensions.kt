package com.movie.movierating

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog

fun Activity.alert(context: Context, title: Int, message: Int, button: Int) {
    val alert1 = AlertDialog.Builder(context)
    alert1.setTitle(title)
    alert1.setMessage(message)
    alert1.setNeutralButton(button, null)
    alert1.show()
}