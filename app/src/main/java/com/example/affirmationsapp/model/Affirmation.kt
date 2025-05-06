package com.example.affirmationsapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.affirmationsapp.R

data class Affirmation(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
