package data.repository

import extensions.fetch
import extensions.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import models.ProductModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface UserRepository {
    suspend fun getProduct(code: String) : Result<ProductModel>
}

class UserRepositoryImpl: UserRepository, KoinComponent {
    private val client by inject<HttpClient>()
    override suspend fun getProduct(code: String): Result<ProductModel> {
        return client.fetch<ProductModel> {
            url("/api/v1/product/$code")
            method = HttpMethod.Get
        }
    }
}