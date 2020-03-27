package pl.gregnote.testyourowncv

class BaseContract {
    interface View

    interface Presenter<in T : View> {
        fun attach(view: T)
        fun onDestroy()
    }
}
