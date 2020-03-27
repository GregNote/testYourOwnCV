package pl.gregnote.testyourowncv.ui.fragment.list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pl.gregnote.testyourowncv.api.ApiServiceInterface
import pl.gregnote.testyourowncv.ui.list.ListContract

class ListPresenter : ListContract.Presenter {

    private lateinit var api: ApiServiceInterface
    private lateinit var view: ListContract.View

    private val compositeDisposable = CompositeDisposable()

    override fun requestDataFromServer() {
        api = ApiServiceInterface.create()
        compositeDisposable.add(api.getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .doOnNext { view.hideProgress() }
            .subscribe(
                { itemArrayList ->
                    view.setDataToRecyclerView(itemArrayList)
                }, { throwable ->
                    view.onResponseFailure(throwable)
                }
            ))
    }

    override fun attach(view: ListContract.View) {
        this.view = view
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}
