package pro.appcraft.lib.utils.common.delegate

import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object ExtDelegate {
    /**
     * Variation of Delegates.notNull() that can only be initialized once
     */
    fun <T : Any> frozen(): ReadWriteProperty<Any?, T> = FrozenVar()

    /**
     * Variation of Delegates.observable() emitting the value only if it differs from the last value
     */
    inline fun <T> distinctUntilChanged(initial: T, crossinline callback: (T) -> Unit) =
        object : ObservableProperty<T>(initial) {
            override fun beforeChange(property: KProperty<*>, oldValue: T, newValue: T): Boolean =
                oldValue != newValue

            override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
                callback(newValue)
            }
        }

    /**
     * A delegate that has lifecycle callbacks
     */
    fun <T : Any> lifecycleAware(
        owner: LifecycleOwner,
        onCreate: (T) -> Unit = {},
        onStart: (T) -> Unit = {},
        onStop: (T) -> Unit = {},
        onDestroy: (T) -> Unit = {},
        propertyProvider: () -> T,
    ): ReadOnlyProperty<Any?, T> = object : LifecycleAwareDelegate<Any?, T>() {
        override val owner: LifecycleOwner = owner
        override val propertyProvider: () -> T = propertyProvider
        override val onCreate: (T) -> Unit = onCreate
        override val onStart: (T) -> Unit = onStart
        override val onStop: (T) -> Unit = onStop
        override val onDestroy: (T) -> Unit = onDestroy
    }
}