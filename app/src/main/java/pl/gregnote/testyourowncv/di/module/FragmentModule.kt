package pl.gregnote.testyourowncv.di.module

import dagger.Module
import dagger.Provides
import pl.gregnote.testyourowncv.ui.fragment.details.DetailsContract
import pl.gregnote.testyourowncv.ui.fragment.details.DetailsPresenter
import pl.gregnote.testyourowncv.ui.fragment.list.ListPresenter
import pl.gregnote.testyourowncv.ui.list.ListContract

@Module
class FragmentModule {

    @Provides
    fun provideListPresenter(): ListContract.Presenter {
        return ListPresenter()
    }

    @Provides
    fun provideDetailsPresenter(): DetailsContract.Presenter {
        return DetailsPresenter()
    }
}
