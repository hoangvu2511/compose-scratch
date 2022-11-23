package com.vunguyenhoang.hapipet.utils

import  android.util.Patterns.EMAIL_ADDRESS

fun String.validateEmail(): Boolean = isNotEmpty() && EMAIL_ADDRESS.matcher(this).matches()