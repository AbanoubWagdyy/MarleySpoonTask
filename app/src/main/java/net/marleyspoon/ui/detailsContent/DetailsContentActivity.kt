package net.marleyspoon.ui.detailsContent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.marleyspoon.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_content)

        supportFragmentManager.beginTransaction()
            .add(R.id.frame, DetailsContentFragment.newInstance())
            .commitAllowingStateLoss()
    }
}