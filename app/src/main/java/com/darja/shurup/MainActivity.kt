package com.darja.shurup

import android.os.Bundle
import android.util.Log
import com.darja.shurup.content.ContentReader
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    protected lateinit var contentReader: ContentReader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contentReader.readTopics()
            .forEach { Log.d("Main", "Topic: ${it.title}") }
    }
}
