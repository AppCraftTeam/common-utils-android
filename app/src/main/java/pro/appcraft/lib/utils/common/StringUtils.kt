package pro.appcraft.lib.utils.common

import android.text.Spanned
import androidx.core.text.HtmlCompat
import java.text.SimpleDateFormat
import java.util.*

fun Long.toDate(): String = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(this)

fun Long.toTextDate(): String = SimpleDateFormat("d MMMM yyyy", Locale.getDefault()).format(this)

fun Long.toYear() = SimpleDateFormat("yyyy", Locale.getDefault()).format(this).toInt()

fun String.toTimeStamp(): Long {
    if (this.isNotEmpty()) {
        val date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(this)
        return date?.time ?: 0
    }
    return 0
}

fun String.toHtmlSpanned(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}
