package com.mkpatir.showcase.api

import com.mkpatir.showcase.api.models.DiscoverResponseModel
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun discover(): Flow<ArrayList<DiscoverResponseModel>>

}