package pl.gregnote.testyourowncv.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.gregnote.testyourowncv.R
import pl.gregnote.testyourowncv.ui.fragment.list.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = ListFragment()
        supportFragmentManager.beginTransaction().replace(
            R.id.listFrameLayout,
            fragment, fragment::class.java.simpleName
        ).commit()
    }
}
