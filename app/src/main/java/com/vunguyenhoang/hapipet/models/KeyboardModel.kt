package com.vunguyenhoang.hapipet.models

sealed class KeyboardModel {
    data class Digit(val num: String) : KeyboardModel()
    object Remove : KeyboardModel()
    object Empty : KeyboardModel()
}
