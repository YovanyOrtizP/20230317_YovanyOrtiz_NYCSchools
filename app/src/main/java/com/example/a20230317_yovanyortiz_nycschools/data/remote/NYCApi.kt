package com.example.a20230317_yovanyortiz_nycschools.data.remote

import com.example.a20230317_yovanyortiz_nycschools.data.model.SatResultResponse
import com.example.a20230317_yovanyortiz_nycschools.data.model.SchoolInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NYCApi {
    /**
     * https://data.cityofnewyork.us/resource/s3k6-pzi2.json
     *
     * https://data.cityofnewyork.us/resource/f9bf-2cp4.json?dbn=01M292
     */

    @GET(ENDPOINT_SCHOOLS)
    suspend fun getSchoolsInfo():Response<List<SchoolInfoResponse>>

    @GET(ENDPOINT_SAT)
    suspend fun getSatInfo(
        @Query(DBN) dbn: String
    ):Response<List<SatResultResponse>>


    companion object{
        const val BASE_URL = "https://data.cityofnewyork.us"
        const val ENDPOINT_SCHOOLS = "/resource/s3k6-pzi2.json"
        const val ENDPOINT_SAT = "/resource/f9bf-2cp4.json"
        const val DBN = "dbn"
    }
}