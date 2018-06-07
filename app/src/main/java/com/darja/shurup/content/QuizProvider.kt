package com.darja.shurup.content

import com.darja.shurup.model.OptionsQuestion
import com.darja.shurup.model.Question
import com.darja.shurup.model.TypingQuestion
import com.darja.shurup.model.Word
import java.util.*

class QuizProvider(private val words: List<Word>) {
    private val random = Random(System.currentTimeMillis())
    private val recentWordIndices = LinkedList<Int>()
    private val wordsCount = words.size

    companion object {
        const val RECENT_SIZE = 5

        const val OPTIONS_COUNT = 4

        const val QUESTION_TYPES_COUNT = 1 // todo set 2 to enable typing questions
        const val TYPE_OPTIONS = 0
        const val TYPE_TYPING = 1

        const val OPTIONS_TYPES_COUNT = 2
        const val OPTIONS_GUESS_ORIGINAL = 0
        const val OPTIONS_GUESS_TRANSLATION = 1
    }

    fun next(): Question {
        var wordIndex: Int

        do {
            wordIndex = random.nextInt(wordsCount)
        } while (recentWordIndices.contains(wordIndex))

        recentWordIndices.add(wordIndex)
        if (recentWordIndices.size > RECENT_SIZE) {
            recentWordIndices.removeFirst()
        }

        val questionType = random.nextInt(QUESTION_TYPES_COUNT)
        return when (questionType) {
            TYPE_OPTIONS -> createOptionsQuestion(wordIndex)
            TYPE_TYPING -> createTypingQuestion(wordIndex)
            else -> throw IllegalArgumentException("Invalid question type: $questionType")
        }
    }

    private fun createTypingQuestion(wordIndex: Int): TypingQuestion {
        val word = words[wordIndex]
        return TypingQuestion(word.translation, word.original)
    }

    private fun createOptionsQuestion(wordIndex: Int): OptionsQuestion {
        val word = words[wordIndex]
        val optionsIndices = LinkedList<Int>()
        var index: Int
        while (optionsIndices.size < OPTIONS_COUNT - 1) {
            index = random.nextInt(wordsCount)
            if (index != wordIndex && !optionsIndices.contains(index)) {
                optionsIndices.add(index)
            }
        }

        val correctIndex = random.nextInt(OPTIONS_COUNT - 1)
        optionsIndices.add(correctIndex, wordIndex)

        return when (random.nextInt(OPTIONS_TYPES_COUNT)) {
            OPTIONS_GUESS_ORIGINAL ->
                OptionsQuestion(word.original, optionsIndices.map { words[it].translation }, correctIndex)

            OPTIONS_GUESS_TRANSLATION ->
                OptionsQuestion(word.translation, optionsIndices.map { words[it].original }, correctIndex)

            else -> throw IllegalArgumentException()
        }
    }
}