package com.example.a20230317_yovanyortiz_nycschools.data.repository

import com.example.a20230317_yovanyortiz_nycschools.data.model.SatResultResponse
import com.example.a20230317_yovanyortiz_nycschools.data.model.domain.SchoolInfoDomain
import com.example.a20230317_yovanyortiz_nycschools.data.model.domain.mapToSchoolInfoDomain
import com.example.a20230317_yovanyortiz_nycschools.data.remote.NYCApi
import com.example.a20230317_yovanyortiz_nycschools.util.ResponseType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


//Collect is a suspend function
//flow es un observable, lo que hace es emitir datos y quien este escuchando
//los datos va a reaccionar a eso
//state fow necesita un valor por default, es un hot flow, listo para ser emitido

//Este es un code flow, es decir, esta por pedir y generar los datos.
//collect cacha los datos que se emiten en el flow

//COMMAND + SHIFT + T
class NYCSchoolsRepositoryImpl @Inject constructor(
    private val nycApi: NYCApi
) : NYCSchoolsRepository {

    override fun getSchoolsInfoFlow(): Flow<ResponseType<List<SchoolInfoDomain>>> = flow {
        emit(ResponseType.LOADING)
        try{
            val response = nycApi.getSchoolsInfo()
            if(response.isSuccessful){
                response.body()?.let {
                    //map es una extension function
                    emit(ResponseType.SUCCESS(it.mapToSchoolInfoDomain()))
                }?:throw Exception("Info not available")
            }else{
                throw Exception(response.errorBody()?.string())
            }
        }catch (e: java.lang.Exception){
            emit(ResponseType.ERROR(e.localizedMessage))
        }
    }

    override fun getSatInfoFlow(dbn: String): Flow<ResponseType<List<SatResultResponse>>> = flow {
        emit(ResponseType.LOADING)
        try{
            val response = nycApi.getSatInfo(dbn)
            if(response.isSuccessful){
                response.body()?.let {
                    emit(ResponseType.SUCCESS(it))
                }?:throw Exception("Info not available")
            }else{
                throw Exception(response.errorBody()?.string())
            }
        }catch (e: java.lang.Exception){
            emit(ResponseType.ERROR(e.localizedMessage))
        }
    }
}
