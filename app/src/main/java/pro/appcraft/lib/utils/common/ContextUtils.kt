package pro.appcraft.lib.utils.common

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager

fun Context.convertDpToPx(value: Float): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    value,
    resources.displayMetrics
)

fun Context.convertPxToDp(value: Float): Float =
    value * (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)

fun Context.convertMmToPx(value: Float): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_MM,
    value,
    resources.displayMetrics
)

fun Context.convertPxToMm(value: Float): Float =
    value / (resources.displayMetrics.xdpi * (1.0f / 25.4f))

fun Context.getDeviceWidth(): Int = getDeviceSize().x

fun Context.getDeviceHeight(): Int = getDeviceSize().y

fun Context.getDeviceSize(): Point {
    val display = if (isApiAtLeast(Build.VERSION_CODES.R))
        this.display
    else (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    val size = Point()
    display?.getSize(size)
    return size
}
