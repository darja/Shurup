package com.darja.shurup.content

import android.app.Application
import com.darja.shurup.R
import com.darja.shurup.model.Topic
import com.darja.shurup.model.Word
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

    fun readTopicContent(name: String): List<Word> {
        val res = app.resources
        val yaml = Yaml()
        val resId = res.getIdentifier(name, "raw", app.packageName)
        val input = res.openRawResource(resId)
        val root = yaml.load<HashMap<String, ArrayList<HashMap<String, String>>>>(input)

        return root["words"]!!
            .map { Word(it) }
    }
}