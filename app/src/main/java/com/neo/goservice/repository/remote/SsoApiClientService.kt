package com.neo.goservice.repository.remote

import io.reactivex.Single
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface SsoApiClientService {
    @Headers("Content-Type: application/xml")
    @POST("AuthService/ws/ssoOther")
    fun login(
        @Body body: RequestBody
    ): Single<ResponseBody>

    @Headers("Content-Type: application/xml")
    @POST("AuthService/ws/ssoOther")
    fun ssoLogin(
            @Body body: RequestBody
    ): Single<ResponseBody>
}