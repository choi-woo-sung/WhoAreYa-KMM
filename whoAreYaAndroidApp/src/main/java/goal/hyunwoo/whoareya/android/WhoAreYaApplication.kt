package goal.hyunwoo.whoareya.android

import android.app.Application
import goal.hyunwoo.whoareya.android.di.appModule
import goal.hyunwoo.whoareya.di.initKoin
import org.koin.android.ext.koin.androidContext
import kotlin.time.ExperimentalTime


class WhoAreYaApplication : Application() {

    @OptIn(ExperimentalTime::class)
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@WhoAreYaApplication)
            modules(appModule)
        }

    }
}
