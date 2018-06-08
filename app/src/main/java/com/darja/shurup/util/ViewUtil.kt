package com.darja.shurup.util

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.EditText

fun EditText.getString() = this.text.toString()

fun Resources.getSpanned(resId: Int, vararg args: Any): Spanned {
    val html = this.getString(resId, *args)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(html)
    }
}