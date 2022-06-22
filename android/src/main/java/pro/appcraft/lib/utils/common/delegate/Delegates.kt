package pro.appcraft.lib.utils.common.delegate

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class FrozenVar<T : Any> : ReadWriteProperty<Any?, T> {
    private lateinit var value: T

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (!this::value.isInitialized) {
            this.value = value
        } else {
            throw IllegalArgumentException("Frozen variable cannot be assigned twice")
        }
    }
}

internal abstract class LifecycleAwareDelegate<R, T : Any> : ReadOnlyProperty<R, T> {
    protected abstract val owner: LifecycleOwner
    protected abstract val propertyProvider: () -> T
    protected open val onCreate: (T) -> Unit = {}
    protected open val onStart: (T) -> Unit = {}
    protected open val onStop: (T) -> Unit = {}
    protected open val onDestroy: (T) -> Unit = {}

    protected var prop: T? = null
    private var observer: LifecycleObserver? = null

    override fun getValue(thisRef: R, property: KProperty<*>): T {
        if (prop == null) {
            prop = propertyProvider()
        }

        if (observer == null) {
            observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_CREATE -> {
                        requireNotNull(prop)
                        onCreate(prop!!)
                    }
                    Lifecycle.Event.ON_DESTROY -> {
                        onDestroy(prop!!)
                        prop = null
                    }
                    Lifecycle.Event.ON_START -> onStart(prop!!)
                    Lifecycle.Event.ON_STOP -> onStop(prop!!)
                    else -> {}
                }
            }.also(owner.lifecycle::addObserver)
        }

        return prop!!
    }
}