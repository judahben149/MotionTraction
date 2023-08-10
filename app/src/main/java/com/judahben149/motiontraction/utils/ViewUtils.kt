package com.judahben149.motiontraction.utils

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout

fun SwipeRefreshLayout.hide() {
    this.visibility = View.GONE
}

fun RecyclerView.hide() {
    this.visibility = View.GONE
}

fun ShimmerFrameLayout.hide() {
    this.visibility = View.GONE
}

fun SwipeRefreshLayout.show() {
    this.visibility = View.GONE
}

fun RecyclerView.show() {
    this.visibility = View.GONE
}

fun ShimmerFrameLayout.show() {
    this.visibility = View.GONE
}

fun TextView.hide() {
    this.visibility = View.INVISIBLE
}

fun TextView.show() {
    this.visibility = View.VISIBLE
}



