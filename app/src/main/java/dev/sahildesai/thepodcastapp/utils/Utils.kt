package dev.sahildesai.thepodcastapp.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.core.text.HtmlCompat

fun String.toAnnotatedHtml(): AnnotatedString {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY).toString().let {
        AnnotatedString(it)
    }
}