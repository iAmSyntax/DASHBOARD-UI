package com.example.ui

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Features(
    val title :String,
    @DrawableRes val iconId : Int,
    val lightClr : Color,
    val medClr : Color,
    val darkClr : Color
)
