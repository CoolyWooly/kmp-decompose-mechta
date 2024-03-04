package checkout.data

import utils.fetch
import core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import checkout.domain.model.ProductModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ProductRepository {
    suspend fun getProduct(code: String) : Result<ProductModel>
}

class ProductRepositoryImpl: ProductRepository, KoinComponent {
    private val client by inject<HttpClient>()
    override suspend fun getProduct(code: String): Result<ProductModel> {
        return client.fetch<ProductModel> {
            url("/api/v1/product/$code")
            method = HttpMethod.Get
        }
    }
}