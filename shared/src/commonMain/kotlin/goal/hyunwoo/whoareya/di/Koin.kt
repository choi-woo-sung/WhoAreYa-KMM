package goal.hyunwoo.whoareya.di

import goal.hyunwoo.whoareya.data.remote.ClothRemoteSource
import goal.hyunwoo.whoareya.network.KtorApi
import goal.hyunwoo.whoareya.network.KtorApiImpl
import goal.hyunwoo.whoareya.network.ProductAPI
import goal.hyunwoo.whoareya.repository.ClothRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule())
    }

@ExperimentalTime
fun commonModule() = module {
    single<KtorApi> { KtorApiImpl() }
    factory { ProductAPI(get()) }
    factory { ClothRemoteSource(get()) }
    single { ClothRepository(get()) }
//    single { GalwayBusRepository() }
//    single { GalwayBusApi(get()) }
//    single { CityBikesApi(get()) }
//    single { AppSettings(get()) }
}