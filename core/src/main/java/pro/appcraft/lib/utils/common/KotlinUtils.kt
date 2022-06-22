package pro.appcraft.lib.utils.common

import java.util.*

fun String.longHash() = UUID.fromString(this).mostSignificantBits

fun Int?.toBoolean(): Boolean = (this ?: 0) != 0

inline fun <T> T.defaultIf(other: T, crossinline provider: () -> T) =
    if (this == other) provider() else this

fun <T : Number> T.nullIfZero(): T? = takeUnless { it == 0 || it == 0L || it == 0f || it == 0.0 }

fun <T : CharSequence> T.nullIfBlank(): T? = takeIf { it.isNotBlank() }

suspend fun <T> repeat(callback: suspend () -> T): T {
    while (true) callback()
}

fun <T> Collection<T>.addOrReplace(element: T, compare: (old: T, new: T) -> Boolean): List<T> {
    val existing = find { compare(it, element) }
    return toMutableList().apply {
        if (existing != null) {
            set(indexOf(existing), element)
        } else {
            add(element)
        }
    }
}

/**
 * Immutable version of the consecutive grouping method
 * Original: https://stackoverflow.com/a/65357359
 */
fun <T> Iterable<T>.groupConsecutiveBy(groupIdentifier: (prev: T, cur: T) -> Boolean): List<List<T>> =
    if (none())
        emptyList()
    else drop(1).fold(listOf(listOf(first()))) { groups, t ->
        if (groupIdentifier(groups.last().last(), t)) {
            groups.dropLast(1).plusElement(groups.last().plusElement(t))
        } else {
            groups.plusElement(listOf(t))
        }
    }
