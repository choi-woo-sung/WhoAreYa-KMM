package goal.hyunwoo.whoareya.di

import goal.hyunwoo.whoareya.network.KtorApi
import goal.hyunwoo.whoareya.network.KtorApiImpl
import goal.hyunwoo.whoareya.network.ProductAPI
import org.koin.dsl.module

private val apiModule =
    module {
        // Single은 앱이 실행되는 동안 계속 유지되는 객체
        single<KtorApi> { KtorApiImpl() }
        factory { ProductAPI(get()) }
    }
