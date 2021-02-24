package com.mkpatir.showcase.api

import com.mkpatir.showcase.api.models.DiscoverResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppRepositoryImpl(private val appService: AppService): AppRepository {

    override fun discover(): Flow<ArrayList<DiscoverResponseModel>>  = flow { emit(appService.discover()) }

}