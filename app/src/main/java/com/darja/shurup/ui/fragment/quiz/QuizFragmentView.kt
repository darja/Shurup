package com.darja.shurup.ui.fragment.quiz

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import android.widget.RelativeLayout.BELOW
import butterknife.BindView
import com.darja.shurup.R
import com.darja.shurup.model.OptionsQuestion
import com.darja.shurup.model.Question
import com.darja.shurup.model.TypingQuestion
import com.darja.shurup.ui.view.OptionsQuestionView
import com.darja.shurup.ui.view.TypingQuestionView

class QuizFragmentView(context: Context?) {
    @Suppress("ProtectedInFinal")
    @BindView(R.id.quiz_root) protected lateinit var root: RelativeLayout

    private var optionsQuestionView: OptionsQuestionView = OptionsQuestionView(context)
    var optionSelectedListener: ((Int) -> Unit)? = null

    private var typingQuestionView: TypingQuestionView = TypingQuestionView(context)
    var typingAnswerEnteredListener: ((String) -> Unit)? = null

    init {
        optionsQuestionView.optionSelectedListener = {
            optionSelectedListener?.invoke(it)
        }

        typingQuestionView.answerEnteredListener = {
            typingAnswerEnteredListener?.invoke(it)
        }
    }

    fun showQuestion(question: Question) {
        when (question) {
            is OptionsQuestion -> showOptionsQuestionView(question)
            is TypingQuestion -> showTypingQuestionView(question)
        }
    }

    fun showOptionsAnswer(clickedIndex: Int, correctIndex: Int) = optionsQuestionView.showAnswer(clickedIndex, correctIndex)

    fun showTypingAnswer(question: TypingQuestion) = typingQuestionView.showAnswer(question)

    private fun showTypingQuestionView(question: TypingQuestion) {
        if (typingQuestionView.parent == null && root.childCount > 1) {
            root.removeViewAt(1)
        }

        if (typingQuestionView.parent == null) {
            placeQuestionView(typingQuestionView)
        }
        typingQuestionView.post { typingQuestionView.showQuestion(question) }
    }

    private fun placeQuestionView(questionView: View): RelativeLayout.LayoutParams {
        val lp = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT)
        lp.addRule(BELOW, R.id.statistics)

        questionView.layoutParams = lp
        root.addView(questionView)

        return lp
    }

    private fun showOptionsQuestionView(question: OptionsQuestion) {
        if (optionsQuestionView.parent == null && root.childCount > 1) {
            root.removeViewAt(1)
        }
        if (optionsQuestionView.parent == null) {
            placeQuestionView(optionsQuestionView)
        }
        optionsQuestionView.post { optionsQuestionView.showQuestion(question) }
    }
}