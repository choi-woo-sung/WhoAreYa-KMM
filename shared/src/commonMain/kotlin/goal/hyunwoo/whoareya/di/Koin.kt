package goal.hyunwoo.whoareya.di

import goal.hyunwoo.whoareya.data.remote.ClothRemoteSource
import goal.hyunwoo.whoareya.data.remote.MapRemoteSource
import goal.hyunwoo.whoareya.network.KtorApi
import goal.hyunwoo.whoareya.network.ClothKtorApiImpl
import goal.hyunwoo.whoareya.network.NaverAPI
import goal.hyunwoo.whoareya.network.NaverKtorApiImpl
import goal.hyunwoo.whoareya.network.ProductAPI
import goal.hyunwoo.whoareya.repository.ClothRepository
import goal.hyunwoo.whoareya.repository.MapRepository
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
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
    single<KtorApi>(qualifier = named("Cloth") ) { ClothKtorApiImpl() }
    single<KtorApi>(qualifier = named("Naver") ) { NaverKtorApiImpl() }
    factory { ProductAPI(get(named("Cloth"))) }
    factory { NaverAPI(get(named("Naver"))) }
    factory { ClothRemoteSource(get()) }
    factory { MapRemoteSource(get()) }
    single { ClothRepository(get()) }
    single { MapRepository(get()) }
//    single { GalwayBusRepository() }
//    single { GalwayBusApi(get()) }
//    single { CityBikesApi(get()) }
//    single { AppSettings(get()) }
}
