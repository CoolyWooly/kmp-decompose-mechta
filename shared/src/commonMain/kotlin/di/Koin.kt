package di

import city_select.data.CitySelectRepository
import city_select.data.CitySelectRepositoryImpl
import core.data.Network
import core.data.Prefs
import checkout.data.ProductRepository
import checkout.data.ProductRepositoryImpl
import checkout.data.UserRepository
import checkout.data.UserRepositoryImpl
import on_boarding.data.OnBoardingRepository
import on_boarding.data.OnBoardingRepositoryImpl
import org.koin.core.context.startKoin
import org.koin.dsl.module
import root.data.RootRepository
import root.data.RootRepositoryImpl
import splashscreen.data.SplashscreenRepository
import splashscreen.data.SplashscreenRepositoryImpl
import tab_home.data.TabHomeRepository
import tab_home.data.TabHomeRepositoryImpl

fun initKoin() =
    startKoin {
        modules(
            networkModule,
            prefsModule,
            repositoryModule,
            useCaseModule,
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
    single<CitySelectRepository> { CitySelectRepositoryImpl() }
    single<OnBoardingRepository> { OnBoardingRepositoryImpl() }
    single<TabHomeRepository> { TabHomeRepositoryImpl() }
    single<RootRepository> { RootRepositoryImpl() }
    single<SplashscreenRepository> { SplashscreenRepositoryImpl() }
}

private val useCaseModule = module {
//    single<ProductRepository> { ProductRepositoryImpl() }
//    single<UserRepository> { UserRepositoryImpl() }
//    single<MapRepository> { MapRepositoryImpl() }
}