package com.example.a20230317_yovanyortiz_nycschools.data.model.domain

import com.example.a20230317_yovanyortiz_nycschools.data.model.SchoolInfoResponse

data class SchoolInfoDomain(
    val dbn: String,
    val latitude: String,
    val longitude: String,
    val location: String,
    val phoneNumber: String,
    val schoolEmail: String,
    val schoolName: String,
    val website: String,
    val overView:String
)

//Lista porque el objecto Response, pertenece a un ArrayList
fun List<SchoolInfoResponse>.mapToSchoolInfoDomain(): List<SchoolInfoDomain> {
    //mapa transforma de la lista del response a la lista del domain
    return this.map {
        SchoolInfoDomain(
            dbn = it.dbn ?: "Dbn not found",
            latitude = it.latitude ?: "Latitude not found",
            longitude = it.longitude ?: "Longitude not found",
            location = it.location ?: "Location not found",
            phoneNumber = it.phoneNumber ?: "N/A",
            schoolEmail = it.schoolEmail ?: "Email not found",
            schoolName = it.schoolName ?: "Name not available",
            website = it.website ?: "Website not available",
            overView = it.overviewParagraph ?: "N/A"
        )
    }
}