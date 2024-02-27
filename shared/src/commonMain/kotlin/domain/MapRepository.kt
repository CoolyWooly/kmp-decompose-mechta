package domain

import extensions.fetch
import extensions.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import models.BaseResponse
import models.CityModel
import models.GetCitiesResponse
import models.ProductModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface MapRepository {
    suspend fun getCities() : Result<List<CityModel>>
}

class MapRepositoryImpl: MapRepository, KoinComponent {
    private val client by inject<HttpClient>()
    override suspend fun getCities(): Result<List<CityModel>> {
        val data =  client.fetch<BaseResponse<GetCitiesResponse>> {
            url("/api/v2/header/cities")
            method = HttpMethod.Get
        }
        return when (data) {
            is Result.Error -> {
                Result.Error(data.text)
            }
            is Result.Success -> {
                Result.Success(data.value.data.cities)
            }
        }
    }
}