package di

import constants.HOST
import domain.MapRepository
import domain.MapRepositoryImpl
import domain.ProductRepository
import domain.ProductRepositoryImpl
import domain.UserRepository
import domain.UserRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin() =
    startKoin {
        modules(
            networkModule,
            repositoryModule
        )
    }

expect fun createPlatformHttpClient(): HttpClient
private val networkModule = module {
    single {
        createPlatformHttpClient().config {
            defaultRequest {
                url.takeFrom(URLBuilder().takeFrom(HOST))
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15_000
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
        }
    }
}

private val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl() }
    single<UserRepository> { UserRepositoryImpl() }
    single<MapRepository> { MapRepositoryImpl() }
}