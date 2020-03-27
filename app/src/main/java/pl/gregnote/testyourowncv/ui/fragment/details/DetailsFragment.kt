package pl.gregnote.testyourowncv.ui.fragment.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_details.*
import pl.gregnote.testyourowncv.NetworkChangeReceiver
import pl.gregnote.testyourowncv.R
import pl.gregnote.testyourowncv.di.component.DaggerFragmentComponent
import pl.gregnote.testyourowncv.di.module.FragmentModule
import pl.gregnote.testyourowncv.models.Details
import javax.inject.Inject

class DetailsFragment : Fragment(), DetailsContract.View {

    @Inject
    lateinit var presenter: DetailsContract.Presenter

    private var receiver: NetworkChangeReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attach(this)
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("name")?.also { name ->
            presenter.requestDataFromServer(name)
        }
        receiver = NetworkChangeReceiver.registerListener(
            requireContext(),
            object : NetworkChangeReceiver.ConnectionChangeListener {
                override fun callback(connected: Boolean) {
                    if (connected) arguments?.getString("name")?.also { name ->
                        presenter.requestDataFromServer(name)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "isConnected = $connected",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        receiver?.let {
            NetworkChangeReceiver.unregisterListener(requireContext(), it)
        }
    }

    override fun showProgress() = progressBar.show()

    override fun hideProgress() = progressBar.hide()

    override fun setDetails(details: Details) {
        name.text = details.fieldName
        text.text = "${details.fieldDesc}:\n${details.fieldValue}"
    }

    override fun onResponseFailure(throwable: Throwable) {
        Toast.makeText(
            requireContext(),
            "onResponseFailure(${throwable.message})",
            Toast.LENGTH_SHORT
        ).show()
    }
}