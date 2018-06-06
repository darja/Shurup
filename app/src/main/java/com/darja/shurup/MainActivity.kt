package com.darja.shurup

import android.os.Bundle
import com.darja.shurup.content.ContentReader
import com.darja.shurup.ui.events.OnTopicClick
import com.darja.shurup.ui.fragment.quiz.QuizFragment
import com.darja.shurup.ui.fragment.topics.TopicsFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Suppress("ProtectedInFinal")
    @Inject
    protected lateinit var contentReader: ContentReader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, TopicsFragment())
                .commit()
        }
    }

    // TODO use ViewModel for Eventbus
    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onTopicClick(arg: OnTopicClick) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, QuizFragment.newInstance(arg.topic))
            .commit()
    }

}
