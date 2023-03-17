package com.example.a20230317_yovanyortiz_nycschools.data.repository

import com.example.a20230317_yovanyortiz_nycschools.data.model.SatResultResponse
import com.example.a20230317_yovanyortiz_nycschools.data.model.domain.SchoolInfoDomain
import com.example.a20230317_yovanyortiz_nycschools.util.ResponseType
import kotlinx.coroutines.flow.Flow

interface NYCSchoolsRepository {

    //Si tu creas la clase, cualquier persona que necesite el repositorio,
    // va a poder modificar la implementacion
    //metodos para obtener la informacion de la implementacion

    fun getSchoolsInfoFlow(): Flow<ResponseType<List<SchoolInfoDomain>>>

    fun getSatInfoFlow(dbn: String): Flow<ResponseType<List<SatResultResponse>>>
}