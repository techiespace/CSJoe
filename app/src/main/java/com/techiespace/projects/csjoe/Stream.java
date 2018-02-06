package com.techiespace.projects.csjoe;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Stream {

    private String stream_name;
    private String course_count;

    public Stream() {
    }

    public Stream(String name, String count) {
        course_count = count;
        stream_name = name;
    }

    public String getStream_name() {
        return stream_name;
    }

    public void setStream_name(String stream_name) {
        this.stream_name = stream_name;
    }

    public String getCourse_count() {
        return course_count;
    }

    public void setCourse_count(String course_count) {
        this.course_count = course_count;
    }

}