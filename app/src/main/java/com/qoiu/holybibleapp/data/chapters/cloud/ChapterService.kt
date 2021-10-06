package com.qoiu.holybibleapp.data.chapters.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ChapterService {

    @GET("books/{id}/chapters")
    suspend fun fetchChapters(@Path ("id")id: Int): ResponseBody
}