package com.droidli.data.remote

import com.droidli.domain.model.BellaInfo
import retrofit2.http.GET

interface BellaApi {

    @GET("/api/moneyheist/characters.json")
    suspend fun getBella(): List<BellaInfo>
}