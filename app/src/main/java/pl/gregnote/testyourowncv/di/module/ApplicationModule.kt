package pl.gregnote.testyourowncv.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import pl.gregnote.testyourowncv.BaseApp
import pl.gregnote.testyourowncv.di.PerApplication
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}
