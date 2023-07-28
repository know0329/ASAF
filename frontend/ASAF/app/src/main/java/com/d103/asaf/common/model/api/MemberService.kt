package com.d103.asaf.common.model.api

import com.d103.asaf.common.model.dto.Member
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface MemberService {
    // 사용자 정보를 추가한다.
//    @POST("rest/user")
    @POST("member/save")
    suspend fun insert(@Body body: Member): Boolean

    // 중복 확인
    @GET("member/email-check/{memberEmail}")
    suspend fun emailCheck(@Path("memberEmail") memberEmail: String): Boolean

    @GET("member/email/{memberEmail}")
    suspend fun getUserInfo(@Path("memberEmail") memberEmail: String): Response<Member>


    // 이미지 업로드 메서드
    @Multipart
    @POST("member/upload-image")
    suspend fun uploadProfileImage(
        @Part("memberEmail") memberEmail: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<Boolean>
}