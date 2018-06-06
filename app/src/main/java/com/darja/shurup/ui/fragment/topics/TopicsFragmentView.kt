package com.darja.shurup.ui.fragment.topics

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.darja.shurup.R
import com.darja.shurup.model.Topic

@Suppress("ProtectedInFinal")
class TopicsFragmentView {
    @BindView(R.id.list) protected lateinit var list: RecyclerView

    private val adapter = TopicsAdapter()

    fun onActivityCreated(activity: Activity?) {
        list.layoutManager = LinearLayoutManager(activity)
        list.adapter = adapter
    }

    fun showTopics(topics: List<Topic>) {
        adapter.topics = topics
        adapter.notifyDataSetChanged()
    }

    fun setTopicClickListener(listener: ((Topic) -> Any)?) {
        adapter.clickListener = listener
    }
}