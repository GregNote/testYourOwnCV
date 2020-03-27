package pl.gregnote.testyourowncv.di.component

import dagger.Component
import pl.gregnote.testyourowncv.di.module.ActivityModule
import pl.gregnote.testyourowncv.ui.MainActivity

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}
