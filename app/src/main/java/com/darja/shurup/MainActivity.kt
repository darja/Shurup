package com.darja.shurup

import android.os.Bundle
import com.darja.shurup.content.ContentReader
import com.darja.shurup.ui.fragment.topics.TopicsFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Suppress("ProtectedInFinal")
    @Inject
    protected lateinit var contentReader: ContentReader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        contentReader.readTopics()
//            .forEach { Log.d("Main", "Topic: ${it.title}") }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, TopicsFragment())
                .commit()
        }
    }
}
