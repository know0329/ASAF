package com.d103.asaf.common.model.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Member(
    val id: Int,
    @SerializedName("student_number") val studentNumber: Int,
    val memberName: String,
    val memberEmail: String,
    val memberPassword: String,
//    val memberDate: String,
    @SerializedName("member_info") val memberInfo: String,
    @SerializedName("birth_date") val birthDate: String,
    @SerializedName("electronic_student_id") val electronicStudentId: Int,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("profile_image") val profileImage: String,
    @SerializedName("team_num") val teamNum: Int,
    val authority: String,
){
    // 기본 생성자
    constructor() : this(0, 0, "", "", "", "", "",
        0, "", "", 0, "")

    // 추가 생성자 추가
    constructor(
        memberName: String,
        memberEmail: String,
        memberPassword: String,
        birthDate: String,
        memberInfo: String
    ) : this(0, 0, memberName, memberEmail,
        memberPassword, memberInfo, birthDate, 0, "", "", 0, "")
}