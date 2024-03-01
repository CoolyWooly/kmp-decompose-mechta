package data.repository

import data.data_source.Prefs
import extensions.Result
import extensions.fetch
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import models.BaseResponse
import models.CityModel
import models.GetCitiesResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface MapRepository {
    suspend fun getCities() : Result<List<CityModel>>
    fun getCity() : Flow<CityModel?>
    suspend fun setCity(cityModel: CityModel?) : Result<Boolean>
}

class MapRepositoryImpl: MapRepository, KoinComponent {
    private val client by inject<HttpClient>()
    private val prefs by inject<Prefs>()
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

    override fun getCity(): Flow<CityModel?> {
        return prefs.getCity()
    }

    override suspend fun setCity(cityModel: CityModel?): Result<Boolean> {
        prefs.setCity(cityModel)
        return Result.Success(true)
    }
}