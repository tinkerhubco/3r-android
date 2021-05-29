package com.tinkerhub.replenish.common.ui

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import coil.load
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.Shimmer.AlphaHighlightBuilder
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.common.extensions.formatUTCDate

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: AppCompatImageView, imageUrl: String?) {
    val shimmer = AlphaHighlightBuilder()
        .setDuration(1800)
        .setBaseAlpha(0.7f)
        .setHighlightAlpha(0.6f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()
    
    val shimmerDrawable = ShimmerDrawable()
    shimmerDrawable.setShimmer(shimmer)
    
    imageView.load(imageUrl) {
        placeholder(shimmerDrawable)
    }
}

@BindingAdapter("startDate", "endDate", "dateFormat", requireAll = true)
fun setStartDate(
    textView: AppCompatTextView,
    startDate: String?,
    endDate: String?,
    dateFormat: String = "MMM dd, yyyy"
) {
    val startDateFormat = startDate?.formatUTCDate(dateFormat)
    val endDateFormat = endDate?.formatUTCDate(dateFormat)
    
    textView.text = textView.context.getString(
        R.string.date_format,
        startDateFormat,
        endDateFormat
    )
}

@BindingAdapter("attemptsCount", "maxAttemptsCount", requireAll = true)
fun setPointsProgress(
    progressIndicator: LinearProgressIndicator,
    attemptsCount: Int?,
    maxAttemptsCount: Int?
) {
    if (attemptsCount == null) return
    if (maxAttemptsCount == null) return
    
    progressIndicator.progress = if (maxAttemptsCount == 1) {
        if (attemptsCount == 0) 0
        else 100
    } else {
        if (attemptsCount == 0) 0
        else ((attemptsCount.toFloat() / maxAttemptsCount.toFloat()) * 100).toInt()
    }
}