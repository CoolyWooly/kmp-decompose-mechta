package city_select.data

import core.data.Prefs
import core.domain.Result
import utils.fetch
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import core.domain.BaseResponse
import city_select.domain.model.CityModel
import city_select.domain.model.GetCitiesResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface CitySelectRepository {
    suspend fun getCities(): Result<List<CityModel>>
    fun getCity(): Flow<CityModel?>
    suspend fun setCity(cityModel: CityModel?): Result<Boolean>
}

class CitySelectRepositoryImpl : CitySelectRepository, KoinComponent {
    private val client by inject<HttpClient>()
    private val prefs by inject<Prefs>()
    override suspend fun getCities(): Result<List<CityModel>> {
        val data = client.fetch<BaseResponse<GetCitiesResponse>> {
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