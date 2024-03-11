package tab_home.data

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
import core.domain.ProductModel
import kotlinx.coroutines.flow.first
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tab_home.domain.BannerModel
import tab_home.domain.CategoryModel
import tab_home.domain.GetCategoryResponse

interface TabHomeRepository {
    fun getCity(): Flow<CityModel?>

    suspend fun getBanners(): Result<List<BannerModel>>
    suspend fun getCategories(): Result<List<CategoryModel>>
    suspend fun getRecommendations(): Result<List<ProductModel>>
    suspend fun getHits(): Result<List<ProductModel>>
}

class TabHomeRepositoryImpl : TabHomeRepository, KoinComponent {
    private val client by inject<HttpClient>()
    private val prefs by inject<Prefs>()

    override fun getCity(): Flow<CityModel?> {
        return prefs.getCity()
    }

    override suspend fun getBanners(): Result<List<BannerModel>> {
        val data = client.fetch<BaseResponse<List<BannerModel>>> {
            url("/api/v1/main-page/banners")
            method = HttpMethod.Get
        }
        return when (data) {
            is Result.Error -> {
                Result.Error(data.text)
            }

            is Result.Success -> {
                Result.Success(data.value.data)
            }
        }
    }

    override suspend fun getCategories(): Result<List<CategoryModel>> {
        val data = client.fetch<BaseResponse<GetCategoryResponse>> {
            url("/api/v1/header/popular-categories")
            method = HttpMethod.Get
        }
        return when (data) {
            is Result.Error -> {
                Result.Error(data.text)
            }

            is Result.Success -> {
                Result.Success(data.value.data.categoryList)
            }
        }
    }

    override suspend fun getRecommendations(): Result<List<ProductModel>> {
        val deviceId = prefs.getMindboxDeviceId().first()
        val data = client.fetch<BaseResponse<List<ProductModel>>> {
            url("/api/v2/recommendations/personal/$deviceId")
            method = HttpMethod.Get
        }
        return when (data) {
            is Result.Error -> {
                Result.Error(data.text)
            }

            is Result.Success -> {
                Result.Success(data.value.data)
            }
        }
    }

    override suspend fun getHits(): Result<List<ProductModel>> {
        val deviceId = prefs.getMindboxDeviceId().first()
        val data = client.fetch<BaseResponse<List<ProductModel>>> {
            url("/api/v2/recommendations/hits/$deviceId")
            method = HttpMethod.Get
        }
        return when (data) {
            is Result.Error -> {
                Result.Error(data.text)
            }

            is Result.Success -> {
                Result.Success(data.value.data)
            }
        }
    }
}