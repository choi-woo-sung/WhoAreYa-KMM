package goal.hyunwoo.whoareya.android.di

import goal.hyunwoo.whoareya.android.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val MainAppModule = module {
    viewModel { MainViewModel(get(),get()) }
}


// Gather all app modules
val appModule = listOf(MainAppModule)
