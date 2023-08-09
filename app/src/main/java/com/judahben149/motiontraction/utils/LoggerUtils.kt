package com.judahben149.motiontraction.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

fun String.logThis() {
    Timber.d(this)
}

fun String.logThis(tag: String) {
    Timber.tag(tag).d(this)
}

fun Int.logThis(tag: String) {
    Timber.tag(tag).d(this.toString())
}

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}

fun String.showSnackBar(view: View) {
    Snackbar.make(view, this, Snackbar.LENGTH_LONG).show()
}