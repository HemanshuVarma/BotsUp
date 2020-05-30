package com.varma.hemanshu.botsup

import com.mikhaellopez.circularimageview.CircularImageView

data class Chat(
    val profile: CircularImageView,
    val name: String,
    val message: String
)