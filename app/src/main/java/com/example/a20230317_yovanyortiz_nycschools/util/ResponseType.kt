package com.example.a20230317_yovanyortiz_nycschools.util

sealed class ResponseType<out T>{

    object LOADING : ResponseType<Nothing>()

    data class SUCCESS<T>(val response: T): ResponseType<T>()

    class ERROR(val e: String): ResponseType<Nothing>()
}
//No hablar de genericos (Tema cabron)

