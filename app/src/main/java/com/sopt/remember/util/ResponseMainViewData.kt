package com.sopt.remember.util

import android.media.Image

data class ResponseMainViewData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val mainList: List<MainList>,
        val image: String
    ) {
        data class MainList(
            val id: String,
            val subject: String,
            val tagName: String,
            val commentCnt: Int,
            val likeCnt: Int
        )
    }
}