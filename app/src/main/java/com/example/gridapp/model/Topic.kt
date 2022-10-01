package com.example.gridapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val titleId: Int,
    val numberOfCourses: Int,
    @DrawableRes val imageId: Int

) {

}
