package pro.appcraft.lib.utils.common

import android.view.View
import android.view.ViewGroup

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