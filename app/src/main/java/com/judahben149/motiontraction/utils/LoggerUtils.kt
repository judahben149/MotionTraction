package com.judahben149.motiontraction.utils

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