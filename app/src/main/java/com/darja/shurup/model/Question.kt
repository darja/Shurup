package com.darja.shurup.model

abstract class Question

class OptionsQuestion(
    val label: String,
    val options: List<String>,
    val answerIndex: Int
) : Question()

class TypingQuestion(
    val label: String,
    val answer: String
) : Question()