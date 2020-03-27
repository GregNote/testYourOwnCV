package pl.gregnote.testyourowncv.ui.fragment.details

import pl.gregnote.testyourowncv.BaseContract
import pl.gregnote.testyourowncv.models.Details

class DetailsContract {
    interface View : BaseContract.View {
        fun showProgress()
        fun hideProgress()
        fun setDetails(details: Details)
        fun onResponseFailure(throwable: Throwable)
    }
    interface Presenter : BaseContract.Presenter<View> {
        fun requestDataFromServer(name: String)
    }
}
