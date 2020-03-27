package pl.gregnote.testyourowncv.ui.fragment.details

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pl.gregnote.testyourowncv.api.ApiServiceInterface

class DetailsPresenter : DetailsContract.Presenter {

    private lateinit var api: ApiServiceInterface
    private lateinit var view: DetailsContract.View

    private val compositeDisposable = CompositeDisposable()

    override fun requestDataFromServer(name: String) {
        api = ApiServiceInterface.create()
        compositeDisposable.add(api.getDetails(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .doOnNext { view.hideProgress() }
            .subscribe(
                { details ->
                    view.setDetails(details)
                }, { throwable ->
                    view.onResponseFailure(throwable)
                }
            ))
    }

    override fun attach(view: DetailsContract.View) {
        this.view = view
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}
