package pro.appcraft.lib.utils.common

import android.text.Spanned
import androidx.core.text.HtmlCompat

fun String.toHtmlSpanned(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}