package checkout.data

import core.domain.ProductModel
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.fetch
import core.domain.Result

interface UserRepository {
    suspend fun getProduct(code: String): Result<ProductModel>
}

class UserRepositoryImpl : UserRepository, KoinComponent {
    private val client by inject<HttpClient>()
    override suspend fun getProduct(code: String): Result<ProductModel> {
        return client.fetch<ProductModel> {
            url("/api/v1/product/$code")
            method = HttpMethod.Get
        }
    }
}