package pl.gregnote.testyourowncv.di.component

import dagger.Component
import pl.gregnote.testyourowncv.BaseApp
import pl.gregnote.testyourowncv.di.module.ApplicationModule

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun suntik(application: BaseApp)
}
