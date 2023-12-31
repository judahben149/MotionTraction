package com.judahben149.motiontraction.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.judahben149.motiontraction.utils.Constants.BACKDROP_BASE_URL

fun ImageView.loadImage(
    context: Context,
    url: String,
    onResourceReady:() -> Unit
) {

    Glide.with(context)
        .load(BACKDROP_BASE_URL + url)
        .placeholder(ShimmerProvider.shimmerDrawable)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onResourceReady()
                return false
            }
        })
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun ImageView.loadBackdropImage(
    context: Context,
    url: String,
    onResourceReady:() -> Unit
) {

    Glide.with(context)
        .load(BACKDROP_BASE_URL + url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onResourceReady()
                return false
            }
        })
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}


fun ImageView.loadImagePlaceholder(
    context: Context,
    @DrawableRes resource: Int,
    onResourceReady:() -> Unit
) {

    Glide.with(context)
        .load(resource)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onResourceReady()
                return false
            }
        })
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

object ShimmerProvider {
    private val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(800)
        .setBaseAlpha(0.7f)
        .setHighlightAlpha(0.6f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()

    val shimmerDrawable = ShimmerDrawable().apply { setShimmer(shimmer) }
}