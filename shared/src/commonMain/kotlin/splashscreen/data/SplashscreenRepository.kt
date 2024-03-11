package splashscreen.data

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
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tab_home.domain.BannerModel
import tab_home.domain.CategoryModel
import tab_home.domain.GetCategoryResponse

interface SplashscreenRepository {
    fun getCity(): Flow<CityModel?>
}

class SplashscreenRepositoryImpl : SplashscreenRepository, KoinComponent {
    private val client by inject<HttpClient>()
    private val prefs by inject<Prefs>()

    override fun getCity(): Flow<CityModel?> {
        return prefs.getCity()
    }
}