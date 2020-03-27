package pl.gregnote.testyourowncv.di.component

import dagger.Component
import pl.gregnote.testyourowncv.di.module.FragmentModule
import pl.gregnote.testyourowncv.ui.fragment.details.DetailsFragment
import pl.gregnote.testyourowncv.ui.fragment.list.ListFragment

@Component(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(listFragment: ListFragment)
    fun inject(detailsFragment: DetailsFragment)
}
