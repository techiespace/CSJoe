package com.techiespace.projects.csjoe;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties

public class University {


    private String uniname;
    private String stream_count;

    public University(){}   //required for firebase
    public University(String uniname, String stream_count){
        this.uniname = uniname;
        this.stream_count = stream_count;
    }


    public void setUniname(String uniname) {
        this.uniname = uniname;
    }

    public void setStream_count(String stream_count) {
        this.stream_count = stream_count;
    }

    public String getUniname() {
        return uniname;
    }

    public String getStream_count() {
        return stream_count;
    }
}