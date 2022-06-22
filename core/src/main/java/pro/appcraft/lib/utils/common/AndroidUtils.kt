package pro.appcraft.lib.utils.common

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import pro.appcraft.lib.core.BuildConfig

@Suppress("NOTHING_TO_INLINE")
@ChecksSdkIntAtLeast(parameter = 0)
inline fun isApiAtLeast(apiCode: Int): Boolean = Build.VERSION.SDK_INT >= apiCode

@Suppress("NOTHING_TO_INLINE")
@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
inline fun isAtLeastOreo(): Boolean = isApiAtLeast(Build.VERSION_CODES.O)

@Suppress("NOTHING_TO_INLINE")
inline fun isDebug(): Boolean = BuildConfig.DEBUG

@Suppress("NOTHING_TO_INLINE")
inline fun isRelease(): Boolean = BuildConfig.BUILD_TYPE.lowercase().contains("release")