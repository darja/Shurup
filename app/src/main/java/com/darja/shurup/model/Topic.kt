package com.darja.shurup.model

class Topic(src: HashMap<String, String>) {
    val title = src["t"]
    val contentResId = src["s"]
}
