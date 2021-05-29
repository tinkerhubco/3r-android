package com.tinkerhub.replenish.common.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun String.formatUTCDate(format: String): String {
    val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    val date: Date = df.parse(this) ?: Date()
    return SimpleDateFormat(format, Locale.getDefault()).format(date)
}