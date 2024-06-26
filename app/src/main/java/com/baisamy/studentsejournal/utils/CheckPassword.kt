package com.baisamy.studentsejournal.utils


fun isPasswordValid(password: String): Boolean {
    return if (password.length < 8) false
    else if (!password.none { it.isWhitespace() }) false
    else if (!password.any { it.isDigit() }) false
    else if (!password.any { it.isUpperCase() }) false
    else password.any { it.isLetterOrDigit() }
}