package com.qoiu.holybibleapp.data.net

import retrofit2.http.GET

interface BookService {

    @GET("books")
    suspend fun fetchBooks() : List<BookCloud>
}