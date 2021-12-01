package com.sopt.remember.activity

import com.sopt.remember.util.RequestPostWriteData
import com.sopt.remember.util.ResponseMainViewData
import com.sopt.remember.util.ResponsePostViewData
import com.sopt.remember.util.ResponsePostWriteData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MainViewService {
    @GET("main")
    fun getData() : Call<ResponseMainViewData>
}