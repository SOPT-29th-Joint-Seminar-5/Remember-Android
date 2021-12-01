package com.sopt.remember.util

data class ResponsePostViewData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val exist: Exist
    ) {
        data class Exist(
            val tagName: String,
            val subject: String,
            val nickname: String?,
            val commentCnt: Int,
            val likeCnt: Int,
            val duty: String?,
            val contents: String,
        )
    }
}
