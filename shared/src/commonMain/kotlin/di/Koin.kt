package di

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import constants.HOST
import data.data_source.Network
import data.data_source.Prefs
import data.repository.MapRepository
import data.repository.MapRepositoryImpl
import data.repository.ProductRepository
import data.repository.ProductRepositoryImpl
import data.repository.UserRepository
import data.repository.UserRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() =
    startKoin {
        modules(
            networkModule,
            prefsModule,
            repositoryModule
        )
    }

private val networkModule = module {
    single { Network.httpClient }
}

private val prefsModule = module {
    single { Prefs }
}
private val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl() }
    single<UserRepository> { UserRepositoryImpl() }
    single<MapRepository> { MapRepositoryImpl() }
}