package com.darja.shurup.model

import java.io.Serializable

class Word(src: HashMap<String, Any>): Serializable {
    val translation: String = src["t"]!!.toString()
    val original: String = src["o"]!!.toString()
}