package com.springprojects.forumdemo.model.forum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserPost {
    int postID;
    String title, text, category;
    Timestamp time;

    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(time);
    }

    public Timestamp getTimeStamp() {
        return time;
    }
}
