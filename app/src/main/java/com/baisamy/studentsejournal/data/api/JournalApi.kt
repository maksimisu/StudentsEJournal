package com.baisamy.studentsejournal.data.api

import com.baisamy.studentsejournal.data.repository.JournalRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JournalApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val journalRepository = retrofit.create(JournalRepository::class.java)
}