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

    val optionViewsCount: Int
        get() = childCount - 1

    init {
        View.inflate(context, R.layout.view_options_question, this)
        ButterKnife.bind(this)
        orientation = VERTICAL
    }

    fun showQuestion(q: OptionsQuestion) {
        questionView.text = q.label

        val optionsCount = q.options.size

        // remove excessive option views if needed
        while (optionViewsCount > optionsCount) {
            removeViewAt(optionsCount)
        }

        // add more option views if needed
        if (optionViewsCount < optionsCount) {
            val inflater = LayoutInflater.from(context)
            while (optionViewsCount < optionsCount) {
                val optionView = inflater.inflate(R.layout.inc_option, this, false) as Button
                addView(optionView)
            }
        }

        // bind titles
        for (i in 0 until optionsCount) {
            val optionView = getOptionView(i)
            val option = q.options[i]
            optionView.text = option
            optionView.setBackgroundResource(R.drawable.bg_option)
            optionView.setOnClickListener { optionSelectedListener?.invoke(i) }
        }
    }

    fun showAnswer(clickedIndex: Int, correctIndex: Int) {
        getOptionView(correctIndex).setBackgroundResource(R.drawable.bg_option_correct)

        if (clickedIndex != correctIndex) {
            getOptionView(clickedIndex).setBackgroundResource(R.drawable.bg_option_incorrect)
        }
    }

    private fun getOptionView(index: Int): Button {
        return getChildAt(index + 1) as Button
    }

    private fun getOptionsViewsCount() = childCount - 1
}