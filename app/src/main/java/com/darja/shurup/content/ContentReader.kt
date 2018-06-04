package com.darja.shurup.content

import android.app.Application
import com.darja.shurup.R
import com.darja.shurup.model.Topic
import org.yaml.snakeyaml.Yaml

class ContentReader(val app: Application) {
    fun readTopics(): List<Topic> {
        val res = app.resources

        val yaml = Yaml()
        val input = res.openRawResource(R.raw.topics)
        val root = yaml.load<HashMap<String, ArrayList<HashMap<String, String>>>>(input)

        return root["topics"]!!
            .map { Topic(it) }
    }
}