package com.darja.shurup.ui.fragment.topics

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.darja.shurup.R
import com.darja.shurup.model.Topic

internal class TopicsAdapter: RecyclerView.Adapter<TopicsAdapter.TopicViewHolder>() {
    var topics: List<Topic>? = null

    var clickListener: ((Topic) -> Any)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_topic, parent, false)

        return TopicViewHolder(view)
    }

    override fun getItemCount() = topics?.size ?: 0

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = topics?.get(position) ?: return

        holder.title.text = topic.title
        holder.root.setOnClickListener { clickListener?.invoke(topic) }
    }

    internal class TopicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @BindView(R.id.root) lateinit var root: View
        @BindView(R.id.title) lateinit var title: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }
}

