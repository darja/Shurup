package com.darja.shurup.ui.fragment.quiz

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import com.darja.shurup.model.Topic
import com.darja.shurup.ui.fragment.BaseFragment

class QuizFragment: BaseFragment<QuizViewModel>() {
    companion object {
        private const val ARG_TOPIC = "topic"

        fun newInstance(topic: Topic) : QuizFragment {
            val args = Bundle()
            args.putSerializable(ARG_TOPIC, topic)

            val fragment = QuizFragment()
            fragment.arguments = args

            return fragment;
        }
    }

    override fun getViewModel() = QuizViewModel::class.java

    override fun getActionBarTitle() = viewModel.getTopic()?.title ?: ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setTopic(arguments?.getSerializable(ARG_TOPIC) as Topic)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewModelEvents()
    }

    private fun setupViewModelEvents() {
        viewModel.question.observe(this, Observer { Log.d("Quiz", "Question: $it") })
    }

}