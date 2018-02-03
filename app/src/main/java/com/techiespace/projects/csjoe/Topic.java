package com.techiespace.projects.csjoe;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Topic {
    private String topic_name;

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }
}