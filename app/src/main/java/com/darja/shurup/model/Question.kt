package com.darja.shurup.model

abstract class Question

class OptionsQuestion(
    val label: String,
    val options: List<String>,
    val correctIndex: Int
) : Question() {
    override fun toString() = String.format("OptionsQuestion label=[%s], options=[%s], answer=[%s]",
        label, options, correctIndex)
}

class TypingQuestion(
    val label: String,
    val answer: String
) : Question() {
    override fun toString() = String.format("TypingQuestion label=[%s], answer=[%s]", label, answer)
}