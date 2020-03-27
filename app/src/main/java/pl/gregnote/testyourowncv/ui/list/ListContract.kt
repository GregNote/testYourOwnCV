package pl.gregnote.testyourowncv.ui.list

import pl.gregnote.testyourowncv.BaseContract
import pl.gregnote.testyourowncv.models.Item
import java.util.*

class ListContract {
    interface View : BaseContract.View {
        fun showProgress()
        fun hideProgress()
        fun setDataToRecyclerView(itemArrayList: ArrayList<Item>)
        fun onResponseFailure(throwable: Throwable)
    }
    interface Presenter : BaseContract.Presenter<View> {
        fun requestDataFromServer()
    }
}
