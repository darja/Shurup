package com.darja.shurup.model

class QuizStatistics {
    var correctCount = 0
        private set

    var incorrectCount = 0
        private set


    fun countAnswer(isCorrect: Boolean) {
        if (isCorrect) {
            correctCount++
        } else {
            incorrectCount++
        }
    }

    fun reset() {
        correctCount = 0
        incorrectCount = 0
    }
}