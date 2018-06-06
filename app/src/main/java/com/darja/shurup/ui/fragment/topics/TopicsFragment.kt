package com.darja.shurup.ui.fragment.topics

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.darja.shurup.R
import com.darja.shurup.ui.events.OnTopicClick
import com.darja.shurup.ui.fragment.BaseFragment
import org.greenrobot.eventbus.EventBus

class TopicsFragment: BaseFragment<TopicsViewModel>() {
    val view = TopicsFragmentView()

    override fun getActionBarTitle() = getString(R.string.topics)

    override fun getViewModel() = TopicsViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)

        ButterKnife.bind(view, rootView)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view.onActivityCreated(activity)

        setupViewModelEvents()
        setupViewEvents()
    }

    private fun setupViewModelEvents() {
        viewModel.getTopics().observe(this, Observer { view.showTopics(it ?: emptyList()) })
    }

    private fun setupViewEvents() {
        view.setTopicClickListener { EventBus.getDefault().post(OnTopicClick(it)) }
    }
}