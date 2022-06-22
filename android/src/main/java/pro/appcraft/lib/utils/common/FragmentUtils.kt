package pro.appcraft.lib.utils.common

import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import java.util.*

fun Fragment.appLocale(): Locale = requireContext().appLocale()

inline fun <reified T : Any?> Fragment.argument(key: String): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        requireArguments().get(key) as T
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun <T : Fragment> T.withArgs(vararg args: Pair<String, *>): T {
    return this.apply {
        arguments = bundleOf(*args)
    }
}

fun Bundle.getIntIfExists(key: String): Int? = getInt(key, Int.MIN_VALUE)
    .takeIf { containsKey(key) && it != Int.MIN_VALUE }

fun Bundle.getLongIfExists(key: String): Long? = getLong(key, Long.MIN_VALUE)
    .takeIf { containsKey(key) && it != Long.MIN_VALUE }

fun Bundle.getStringIfExists(key: String): String? = getString(key)?.takeIf { containsKey(key) }

inline fun <reified T : Parcelable> Bundle.getParcelableIfExists(key: String): T? =
    getParcelable<T>(key)?.takeIf { containsKey(key) }

inline fun <reified T : Parcelable> Bundle.getParcelableListIfExists(key: String): List<T>? =
    getParcelableArrayList<T>(key)?.takeIf { containsKey(key) }