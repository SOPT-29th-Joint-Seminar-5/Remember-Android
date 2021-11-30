package com.sopt.remember.util

data class ResponsePostWriteData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val post: Post
    ) {
        data class Post(
            val id: Int,
            val communityId: Int,
            val subject: String,
            val nickname: String?,
            val commentCnt: Int,
            val likeCnt: Int,
            val duty: String?,
            val contents: String,
        )
    }
}
