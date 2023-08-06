package com.judahben149.motiontraction.utils

import android.content.Context
import android.widget.Toast
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