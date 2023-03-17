package com.example.a20230317_yovanyortiz_nycschools.data.model


import com.google.gson.annotations.SerializedName

//Borrar por demasiados datos
data class SchoolInfoResponse(
    @SerializedName("attendance_rate")
    val attendanceRate: String? = "",
    @SerializedName("bbl")
    val bbl: String? = "",
    @SerializedName("bin")
    val bin: String? = "",
    @SerializedName("boro")
    val boro: String? = "",
    @SerializedName("borough")
    val borough: String? = "",
    @SerializedName("boys")
    val boys: String? = "",
    @SerializedName("building_code")
    val buildingCode: String? = "",
    @SerializedName("bus")
    val bus: String? = "",
    @SerializedName("campus_name")
    val campusName: String? = "",
    @SerializedName("census_tract")
    val censusTract: String? = "",
    @SerializedName("city")
    val city: String? = "",
    @SerializedName("dbn")
    val dbn: String? = "",
    @SerializedName("latitude")
    val latitude: String? = "",
    @SerializedName("location")
    val location: String? = "",
    @SerializedName("longitude")
    val longitude: String? = "",
    @SerializedName("neighborhood")
    val neighborhood: String? = "",
    @SerializedName("nta")
    val nta: String? = "",
    @SerializedName("overview_paragraph")
    val overviewParagraph: String? = "",
    @SerializedName("pbat")
    val pbat: String? = "",
    @SerializedName("pct_stu_enough_variety")
    val pctStuEnoughVariety: String? = "",
    @SerializedName("pct_stu_safe")
    val pctStuSafe: String? = "",
    @SerializedName("phone_number")
    val phoneNumber: String? = "",
    @SerializedName("primary_address_line_1")
    val primaryAddressLine1: String? = "",
    @SerializedName("school_accessibility_description")
    val schoolAccessibilityDescription: String? = "",
    @SerializedName("school_email")
    val schoolEmail: String? = "",
    @SerializedName("school_name")
    val schoolName: String? = "",
    @SerializedName("school_sports")
    val schoolSports: String? = "",
    @SerializedName("specialized")
    val specialized: String? = "",
    @SerializedName("start_time")
    val startTime: String? = "",
    @SerializedName("state_code")
    val stateCode: String? = "",
    @SerializedName("website")
    val website: String? = ""
)