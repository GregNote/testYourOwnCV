package pl.gregnote.testyourowncv

import android.app.Application
import pl.gregnote.testyourowncv.di.component.ApplicationComponent
import pl.gregnote.testyourowncv.di.component.DaggerApplicationComponent
import pl.gregnote.testyourowncv.di.component.DaggerFragmentComponent
import pl.gregnote.testyourowncv.di.component.FragmentComponent

class BaseApp: Application() {

    lateinit var component: ApplicationComponent
    lateinit var fragmentComponent: FragmentComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        component = DaggerApplicationComponent.builder().build()
        component.suntik(this)
        fragmentComponent = DaggerFragmentComponent.builder().build()
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}
