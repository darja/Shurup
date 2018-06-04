package com.darja.shurup.ui.fragment.topics

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.darja.shurup.content.ContentReader
import com.darja.shurup.model.Topic
import javax.inject.Inject
import kotlin.concurrent.thread

class TopicsViewModel @Inject constructor(): ViewModel() {
    @Suppress("ProtectedInFinal")
    @Inject
    protected lateinit var contentReader: ContentReader

    private var topics: MutableLiveData<List<Topic>> = MutableLiveData()

    fun getTopics(): LiveData<List<Topic>> {
        if (topics.value == null) {
            thread { topics.postValue(contentReader.readTopics()) }
        }
        return topics
    }
}