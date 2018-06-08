package com.darja.shurup.ui.fragment.quiz

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.darja.shurup.R
import com.darja.shurup.model.Topic
import com.darja.shurup.ui.fragment.BaseFragment

class QuizFragment: BaseFragment<QuizViewModel>() {
    private lateinit var view: QuizFragmentView
    private val handler = Handler()

    companion object {
        private const val ARG_TOPIC = "topic"

        fun newInstance(topic: Topic) : QuizFragment {
            val args = Bundle()
            args.putSerializable(ARG_TOPIC, topic)

            val fragment = QuizFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun getViewModel() = QuizViewModel::class.java

    override fun getActionBarTitle() = viewModel.getTopic()?.title ?: ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_quiz, container, false)

        view = QuizFragmentView(context)
        ButterKnife.bind(view, rootView)

        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setTopic(arguments?.getSerializable(ARG_TOPIC) as Topic)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewModelEvents()
        setupViewEvents()
    }

    private fun setupViewModelEvents() {
        viewModel.question.observe(this, Observer {
            if (it != null && activity != null) {
                view.showQuestion(activity!!, it)
            }
        })

        viewModel.statistics.observe(this, Observer {
            view.showStatistics(resources, it)
        })
    }

    private fun setupViewEvents() {
        view.optionSelectedListener = {
            val correctIndex = viewModel.getOptionsQuestion().correctIndex
            viewModel.updateStatistics(it == correctIndex)

            view.showOptionsAnswer(it, correctIndex)
            handler.postDelayed({ viewModel.nextQuestion() }, 1000)
        }

        view.typingAnswerEnteredListener = {
            val question = viewModel.getTypingQuestion()
            viewModel.updateStatistics(it == question.answer)

            view.showTypingAnswer(question)
            handler.postDelayed({ viewModel.nextQuestion() }, 2000)
        }
    }
}