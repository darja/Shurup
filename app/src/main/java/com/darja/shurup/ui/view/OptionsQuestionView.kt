package com.darja.shurup.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.darja.shurup.R
import com.darja.shurup.model.OptionsQuestion

class OptionsQuestionView(context: Context?) : LinearLayout(context) {
    @Suppress("ProtectedInFinal")
    @BindView(R.id.question) protected lateinit var questionView: TextView

    var optionSelectedListener: ((Int) -> Unit)? = null

    init {
        View.inflate(context, R.layout.view_options_question, this)
        ButterKnife.bind(this)
        orientation = VERTICAL
    }

    fun showQuestion(q: OptionsQuestion) {
        questionView.text = q.label

        while (childCount > 1) {
            removeViewAt(1)
        }

        val inflater = LayoutInflater.from(context)
        for (i in 0 until q.options.size) {
            val option = q.options[i]
            val optionView = inflater.inflate(R.layout.inc_option, this, false) as Button
            optionView.text = option
            optionView.setBackgroundResource(R.drawable.bg_option)
            optionView.setOnClickListener { optionSelectedListener?.invoke(i) }

            addView(optionView)
        }
    }

    fun showAnswer(clickedIndex: Int, correctIndex: Int) {
        getAnswerView(correctIndex).setBackgroundResource(R.drawable.bg_option_correct)

        if (clickedIndex != correctIndex) {
            getAnswerView(clickedIndex).setBackgroundResource(R.drawable.bg_option_incorrect)
        }
    }

    private fun getAnswerView(index: Int): View {
        return getChildAt(index + 1)
    }
}