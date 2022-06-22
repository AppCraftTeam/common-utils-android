package pro.appcraft.lib.utils.common

import android.annotation.SuppressLint
import android.os.Build
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.View
import android.widget.TextView

private class URLSpanNoUnderline(
    url: String
) : URLSpan(url) {
    override fun updateDrawState(textPaint: TextPaint) {
        super.updateDrawState(textPaint)
        textPaint.isUnderlineText = false
    }
}

private class ClickableSpanNoUnderline(
    val url: String,
    val clickCallback: ((String) -> Unit)
) : ClickableSpan() {
    override fun updateDrawState(textPaint: TextPaint) {
        super.updateDrawState(textPaint)
        textPaint.isUnderlineText = false
    }

    override fun onClick(widget: View) {
        clickCallback(url)
    }
}

fun TextView.removeLinkUnderlines(clickCallback: ((String) -> Unit)? = null) {
    SpannableString(text).also { spannable ->
        spannable.getSpans(0, spannable.length, URLSpan::class.java).forEach { span ->
            val start = spannable.getSpanStart(span)
            val end = spannable.getSpanEnd(span)
            val newSpan = clickCallback?.let {
                ClickableSpanNoUnderline(span.url, clickCallback)
            } ?: run {
                URLSpanNoUnderline(span.url)
            }
            spannable.removeSpan(span)
            spannable.setSpan(newSpan, start, end, 0)
        }
    }.also(::setText)
}

fun TextView?.undo() {
    this?.onTextContextMenuItem(android.R.id.undo)
}

fun TextView?.redo() {
    this?.onTextContextMenuItem(android.R.id.redo)
}

/**
 * WARNING: This code uses blocked private api.
 * Add implementation 'com.github.ChickenHook:RestrictionBypass:2.2' to gradle.
 * */
@SuppressLint("DiscouragedPrivateApi")
fun TextView?.canUndo(): Boolean = runCatching {
    if (!isApiAtLeast(Build.VERSION_CODES.S)) {
        val canUndo = TextView::class.java.getDeclaredMethod("canUndo")
        canUndo.isAccessible = true
        canUndo.invoke(this) as? Boolean ?: false
    } else false
}.getOrDefault(false)

/**
 * WARNING: This code uses blocked private api.
 * Add implementation 'com.github.ChickenHook:RestrictionBypass:2.2' to gradle.
 * */
@SuppressLint("DiscouragedPrivateApi")
fun TextView?.canRedo(): Boolean = runCatching {
    if (!isApiAtLeast(Build.VERSION_CODES.S)) {
        val canRedo = TextView::class.java.getDeclaredMethod("canRedo")
        canRedo.isAccessible = true
        canRedo.invoke(this) as? Boolean ?: false
    } else false
}.getOrDefault(false)
