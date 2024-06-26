package com.baisamy.studentsejournal.utils

import android.text.TextUtils
import android.util.Patterns


fun isEmailValid(email: CharSequence?): Boolean {
    return if (TextUtils.isEmpty(email) || email == null) {
        false
    } else {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}