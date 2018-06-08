package com.darja.shurup.ui.view

import android.app.Activity
import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.darja.shurup.R
import com.darja.shurup.model.TypingQuestion
import com.darja.shurup.util.ScreenUtil

@Suppress("ProtectedInFinal")
class TypingQuestionView(context: Context?): LinearLayout(context) {
    @BindView(R.id.question) protected lateinit var questionView: TextView
    @BindView(R.id.correct_answer) protected lateinit var correctAnswerView: TextView
    @BindView(R.id.answer) protected lateinit var answerView: EditText

    var answerEnteredListener: ((String) -> Unit)? = null

    init {
        View.inflate(context, R.layout.view_typing_questions, this)
        ButterKnife.bind(this)
        orientation = VERTICAL

        val paddingHoriz = resources.getDimensionPixelSize(R.dimen.big_margin)
        val paddingTop = resources.getDimensionPixelSize(R.dimen.default_margin)
        setPadding(paddingHoriz, paddingTop, paddingHoriz, 0)
    }

    fun showQuestion(activity: Activity, question: TypingQuestion) {
        questionView.text = question.label
        correctAnswerView.visibility = View.INVISIBLE

        answerView.text.clear()
        answerView.setBackgroundResource(R.drawable.bg_editor)
        answerView.setTextColor(ResourcesCompat.getColor(resources, android.R.color.black, null))
        answerView.isEnabled = true
        answerView.requestFocus()
        answerView.post({ ScreenUtil.showSoftKeyboard(activity, answerView) })

        answerView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                answerEnteredListener?.invoke(answerView.text.toString())
                answerView.isEnabled = false
                ScreenUtil.hideSoftKeyboard(activity, answerView)
                true
            } else
                false
        }
    }

    fun showAnswer(question: TypingQuestion) {
        val typedAnswer = answerView.text.toString()

        if (typedAnswer == question.answer) {
            answerView.setBackgroundResource(R.color.correctAnswerBgr)
            answerView.setTextColor(ResourcesCompat.getColor(resources, R.color.correctAnswerTextColor, null))
        } else {
            answerView.setBackgroundResource(R.color.incorrectAnswerBgr)
            answerView.setTextColor(ResourcesCompat.getColor(resources, R.color.incorrectAnswerTextColor, null))

            correctAnswerView.text = question.answer
            correctAnswerView.visibility = View.VISIBLE
        }
    }
}