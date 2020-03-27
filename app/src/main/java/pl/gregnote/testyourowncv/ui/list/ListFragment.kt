package pl.gregnote.testyourowncv.ui.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_list.*
import pl.gregnote.testyourowncv.NetworkChangeReceiver
import pl.gregnote.testyourowncv.R
import pl.gregnote.testyourowncv.adapter.ItemClickListener
import pl.gregnote.testyourowncv.adapter.ListAdapter
import pl.gregnote.testyourowncv.di.component.DaggerFragmentComponent
import pl.gregnote.testyourowncv.di.module.FragmentModule
import pl.gregnote.testyourowncv.models.Item
import pl.gregnote.testyourowncv.ui.fragment.details.DetailsFragment
import pl.gregnote.testyourowncv.ui.list.ListContract
import java.util.*
import javax.inject.Inject

class ListFragment : Fragment(), ListContract.View {

    @Inject
    lateinit var presenter: ListContract.Presenter

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
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attach(this)
        super.onViewCreated(view, savedInstanceState)
        presenter.requestDataFromServer()
        receiver = NetworkChangeReceiver.registerListener(
            requireContext(),
            object : NetworkChangeReceiver.ConnectionChangeListener {
                override fun callback(connected: Boolean) {
                    if (connected) presenter.requestDataFromServer()
                    else {
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

    override fun setDataToRecyclerView(itemArrayList: ArrayList<Item>) {
        itemsList.adapter = ListAdapter(itemArrayList, object : ItemClickListener {
            override fun onItemClick(name: String) {
                val fragment = DetailsFragment()
                fragment.arguments = bundleOf("name" to name)

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.listFrameLayout,
                        fragment,
                        fragment::class.java.simpleName
                    ).addToBackStack(fragment::class.java.simpleName)
                    .commit()
            }
        })
    }

    override fun onResponseFailure(throwable: Throwable) {
        Toast.makeText(
            requireContext(),
            "onResponseFailure(${throwable.message})",
            Toast.LENGTH_SHORT
        ).show()
    }
}
