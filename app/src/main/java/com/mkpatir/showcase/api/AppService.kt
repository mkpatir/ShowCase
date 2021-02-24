package com.mkpatir.showcase.api

import com.mkpatir.showcase.api.models.DiscoverResponseModel
import retrofit2.http.GET

interface AppService {

    @GET("discover")
    suspend fun discover(): ArrayList<DiscoverResponseModel>

}