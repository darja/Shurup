package com.darja.shurup.model

import java.io.Serializable

class Word(src: HashMap<String, String>): Serializable {
    val translation = src["t"]
    val original = src["o"]
}