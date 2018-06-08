package com.darja.shurup.ui.fragment.quiz

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.darja.shurup.content.ContentReader
import com.darja.shurup.content.QuizProvider
import com.darja.shurup.model.*
import javax.inject.Inject
import kotlin.concurrent.thread

class QuizViewModel @Inject constructor(): ViewModel() {
    @Suppress("ProtectedInFinal")
    @Inject
    protected lateinit var contentReader: ContentReader

    private lateinit var quizProvider: QuizProvider

    private var topic: Topic? = null
    private var quizStatistics = QuizStatistics()

    var question = MutableLiveData<Question>()
    var statistics = MutableLiveData<QuizStatistics>()

    fun setTopic(topic: Topic) {
        this.topic = topic

        thread {
            val words = contentReader.readTopicContent(topic.contentResName)
            quizProvider = QuizProvider(words)

            quizStatistics.reset()
            statistics.postValue(quizStatistics)

            question.postValue(quizProvider.next())
        }
    }

    fun getTopic() = topic

    fun getOptionsQuestion() = question.value as OptionsQuestion

    fun getTypingQuestion() = question.value as TypingQuestion

    fun updateStatistics(isCorrect: Boolean) {
        quizStatistics.countAnswer(isCorrect)
        statistics.postValue(quizStatistics)
    }

    fun nextQuestion() {
        question.postValue(quizProvider.next())
    }
}