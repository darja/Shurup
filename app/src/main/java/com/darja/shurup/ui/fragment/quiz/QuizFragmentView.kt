package com.darja.shurup.ui.fragment.quiz

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import android.widget.RelativeLayout.BELOW
import butterknife.BindView
import com.darja.shurup.R
import com.darja.shurup.model.OptionsQuestion
import com.darja.shurup.model.Question
import com.darja.shurup.ui.view.OptionsQuestionView

class QuizFragmentView {
    @Suppress("ProtectedInFinal")
    @BindView(R.id.quiz_root) protected lateinit var root: RelativeLayout

    fun showQuestion(context: Context?, question: Question) {
        if (root.childCount > 1) {
            root.removeViewAt(1)
        }
        when (question) {
            is OptionsQuestion -> showOptionsQuestionView(context, question)
        }
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

    private fun showOptionsQuestionView(context: Context?, question: OptionsQuestion) {
        val questionView = OptionsQuestionView(context)
        placeQuestionView(questionView)
        questionView.post { questionView.showQuestion(question) }
    }
}