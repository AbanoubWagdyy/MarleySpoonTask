package net.marleyspoon.ui.listContent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.marleyspoon.R

class ListContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_content)

        supportFragmentManager.beginTransaction()
            .add(R.id.frame, ListContentFragment.newInstance())
            .commitAllowingStateLoss()
    }
}