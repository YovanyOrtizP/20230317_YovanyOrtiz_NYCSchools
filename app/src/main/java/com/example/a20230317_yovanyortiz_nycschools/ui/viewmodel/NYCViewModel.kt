package com.example.a20230317_yovanyortiz_nycschools.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20230317_yovanyortiz_nycschools.data.model.SatResultResponse
import com.example.a20230317_yovanyortiz_nycschools.data.model.domain.SchoolInfoDomain
import com.example.a20230317_yovanyortiz_nycschools.data.repository.NYCSchoolsRepository
import com.example.a20230317_yovanyortiz_nycschools.util.ResponseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NYCViewModel @Inject constructor(
    private val nycSchoolsRepository: NYCSchoolsRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    var schoolsObject: SchoolInfoDomain? = null

    private val _resultSchools = MutableLiveData<ResponseType<List<SchoolInfoDomain>>>()
    val resultSchools : LiveData<ResponseType<List<SchoolInfoDomain>>> = _resultSchools

    private val _resultSat = MutableLiveData<ResponseType<List<SatResultResponse>>>()
    val resultSat : LiveData<ResponseType<List<SatResultResponse>>> = _resultSat

    fun getSchoolsInfoFlow(){
        viewModelScope.launch(ioDispatcher){
            nycSchoolsRepository.getSchoolsInfoFlow().collect(){
                _resultSchools.postValue(it)
            }
        }
    }

    fun getSatInfoFlow(){
        viewModelScope.launch(ioDispatcher){
            schoolsObject?.dbn?.let {
                nycSchoolsRepository.getSatInfoFlow(it).collect { info->
                    _resultSat.postValue(info)
                }
            } ?: _resultSat.postValue(ResponseType.ERROR("NO SCHOOL SELECTED"))
        }
    }
}
