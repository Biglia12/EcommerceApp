package com.allapps.ecommerceapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.snackbar(view:View ,text: String) {
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
    //Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}