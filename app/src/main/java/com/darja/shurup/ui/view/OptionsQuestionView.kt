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

        q.options.forEach {
            val optionView = LayoutInflater.from(context).inflate(R.layout.inc_option, this, false) as Button
            optionView.text = it
            optionView.setBackgroundResource(R.drawable.bg_option)
            addView(optionView)
        }
    }
}