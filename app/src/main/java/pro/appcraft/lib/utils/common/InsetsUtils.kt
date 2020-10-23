package pro.appcraft.lib.utils.common

import android.view.View
import android.view.ViewGroup
import androidx.core.view.*

fun View.addSystemWindowInsetToPadding(
    left: Boolean = false,
    top: Boolean = false,
    right: Boolean = false,
    bottom: Boolean = false,
    leftOffset: Int = 0,
    topOffset: Int = 0,
    rightOffset: Int = 0,
    bottomOffset: Int = 0
) {
    val (initialLeft, initialTop, initialRight, initialBottom) = listOf(
        paddingLeft + leftOffset,
        paddingTop + topOffset,
        paddingRight + rightOffset,
        paddingBottom + bottomOffset
    )

    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        view.updatePadding(
            left = initialLeft + (if (left) insets.systemWindowInsetLeft.coerceAtLeast(0) else 0),
            top = initialTop + (if (top) insets.systemWindowInsetTop.coerceAtLeast(0) else 0),
            right = initialRight + (if (right) insets.systemWindowInsetRight.coerceAtLeast(0) else 0),
            bottom = initialBottom + (if (bottom) insets.systemWindowInsetBottom.coerceAtLeast(0) else 0)
        )

        insets
    }
}

fun View.addSystemWindowInsetToMargin(
    left: Boolean = false,
    top: Boolean = false,
    right: Boolean = false,
    bottom: Boolean = false,
    leftOffset: Int = 0,
    topOffset: Int = 0,
    rightOffset: Int = 0,
    bottomOffset: Int = 0
) {
    val (initialLeft, initialTop, initialRight, initialBottom) = listOf(
        marginLeft + leftOffset,
        marginTop + topOffset,
        marginRight + rightOffset,
        marginBottom + bottomOffset
    )

    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        view.updateLayoutParams {
            (this as? ViewGroup.MarginLayoutParams)?.let {
                updateMargins(
                    left = initialLeft + (if (left) insets.systemWindowInsetLeft.coerceAtLeast(0) else 0),
                    top = initialTop + (if (top) insets.systemWindowInsetTop.coerceAtLeast(0) else 0),
                    right = initialRight + (if (right) insets.systemWindowInsetRight.coerceAtLeast(0) else 0),
                    bottom = initialBottom + (if (bottom) insets.systemWindowInsetBottom.coerceAtLeast(0) else 0)
                )
            }
        }

        insets
    }
}