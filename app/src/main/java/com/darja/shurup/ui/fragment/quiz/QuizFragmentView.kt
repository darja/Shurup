package com.darja.shurup.ui.fragment.quiz

import android.content.Context
import android.widget.RelativeLayout
import butterknife.BindView
import com.darja.shurup.R
import com.darja.shurup.model.OptionsQuestion
import com.darja.shurup.model.Question
import com.darja.shurup.ui.view.OptionsQuestionView

class QuizFragmentView {
    @Suppress("ProtectedInFinal")
    @BindView(R.id.quiz_root) protected lateinit var root: RelativeLayout

    fun showQuestion(context: Context?, question: Question) {
        root.removeAllViews()
        when (question) {
            is OptionsQuestion -> showOptionsQuestionView(context, question)
        }
    }

    private fun showOptionsQuestionView(context: Context?, question: OptionsQuestion) {
        val questionView = OptionsQuestionView(context)
        questionView.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT)
        root.addView(questionView)
        questionView.post { questionView.showQuestion(question) }
    }
}