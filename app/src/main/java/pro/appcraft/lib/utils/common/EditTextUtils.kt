package pro.appcraft.lib.utils.common

import android.text.TextUtils
import android.widget.EditText

fun EditText.syncWithValue(value: String?) {
    val notNullValue = value ?: ""
    if (notNullValue != text.toString()) {
        setText(notNullValue)
    }
}

fun EditText.syncWithValue(value: Double?) {
    if (value == null) {
        if (!TextUtils.isEmpty(text)) {
            text = null
        }
    } else {
        val editTextValue = text.toString().toDoubleOrNull() ?: 0.0
        if (editTextValue != value) {
            setText(value.toString())
        }
    }
}

fun EditText.syncWithValue(value: Int?) {
    if (value == null) {
        if (!TextUtils.isEmpty(text)) {
            text = null
        }
    } else {
        val editTextValue = text.toString().toIntOrNull() ?: 0
        if (editTextValue != value) {
            setText(value.toString())
        }
    }
}
