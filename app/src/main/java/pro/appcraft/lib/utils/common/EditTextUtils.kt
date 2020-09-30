package pro.appcraft.lib.utils.common

import android.widget.EditText

fun EditText.syncWithValue(value: String?) {
    val notNullValue = value.orEmpty()
    if (notNullValue != text.toString()) {
        setText(notNullValue)
    }
}

fun EditText.syncWithValue(value: Double?) = value?.let {
    val editTextValue = text.toString().toDoubleOrNull() ?: 0.0
    if (editTextValue != it) {
        setText(value.toString())
    }
} ?: run {
    if (!text.isNullOrBlank()) {
        text = null
    }
}

fun EditText.syncWithValue(value: Int?) = value?.let {
    val editTextValue = text.toString().toIntOrNull() ?: 0
    if (editTextValue != it) {
        setText(value.toString())
    }
} ?: run {
    if (!text.isNullOrBlank()) {
        text = null
    }
}
