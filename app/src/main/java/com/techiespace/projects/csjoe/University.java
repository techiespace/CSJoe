package com.techiespace.projects.csjoe;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties

public class University {
    private String uniname;
    private String stream_count;

    public University(){}   //required for firebase
    public University(String name, String uniStreamCount){
        uniname = name;
        stream_count = uniStreamCount;

    }

    public String getUniName() {
        return uniname;
    }

    public String getStreamCount(){
        return stream_count;
    }

    /*public String getUniDesc() {
        return uniDesc;
    }*/

} 