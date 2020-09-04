package pro.appcraft.lib.utils.common

import android.view.View
import android.view.ViewGroup

fun View?.setVisible() {
    this?.let {
        it.visibility = View.VISIBLE
    }
}

fun View?.setVisible(isVisibly: Boolean) {
    this?.let {
        it.visibility = if (isVisibly) View.VISIBLE else View.GONE
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

fun View?.updateMargins(l: Int = 0, t: Int = 0, r: Int = 0, b: Int = 0) {
    this?.let {
        if (layoutParams is ViewGroup.MarginLayoutParams) {
            val p = layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(l, t, r, b)
            requestLayout()
        }
    }
}