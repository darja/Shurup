package com.darja.shurup.model

import java.io.Serializable

class Topic(src: HashMap<String, String>): Serializable {
    val title = src["t"]!!
    val contentResName = src["s"]!!
}
