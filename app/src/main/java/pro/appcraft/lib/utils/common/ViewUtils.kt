package pro.appcraft.lib.utils.common

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat

fun View?.setVisible() {
    this?.visibility = View.VISIBLE
}

fun View?.setVisible(isVisible: Boolean) {
    this?.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View?.setInvisible() {
    this?.visibility = View.INVISIBLE
}

fun View?.setGone() {
    this?.visibility = View.GONE
}

fun View?.updateMargins(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
    this?.apply {
        if (layoutParams is ViewGroup.MarginLayoutParams) {
            val marginLayoutParams = layoutParams as ViewGroup.MarginLayoutParams
            marginLayoutParams.setMargins(left, top, right, bottom)
            requestLayout()
        }
    }
}

fun View.setBackgroundTintList(@ColorRes res: Int) {
    this.context?.let { this.backgroundTintList = ContextCompat.getColorStateList(it, res) }
}

fun AppCompatImageView.setImageTintList(@ColorRes res: Int) {
    this.context?.let { this.imageTintList = ContextCompat.getColorStateList(it, res) }
}

fun TextView.setTextColorRes(@ColorRes res: Int) {
    this.context?.let { this.setTextColor(ContextCompat.getColor(it, res)) }
}