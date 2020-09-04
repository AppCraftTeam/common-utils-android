package pro.appcraft.lib.utils.common

import android.view.View
import android.view.ViewGroup

fun View?.setVisible() {
    this?.let {
        it.visibility = View.VISIBLE
    }
}

fun View?.setVisible(isVisible: Boolean) {
    this?.let {
        it.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}

fun View?.setInvisible() {
    this?.let {
        it.visibility = View.INVISIBLE
    }
}

fun View?.setGone() {
    this?.let {
        it.visibility = View.GONE
    }
}

fun View?.updateMargins(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
    this?.let {
        if (layoutParams is ViewGroup.MarginLayoutParams) {
            val marginLayoutParams = layoutParams as ViewGroup.MarginLayoutParams
            marginLayoutParams.setMargins(left, top, right, bottom)
            requestLayout()
        }
    }
}