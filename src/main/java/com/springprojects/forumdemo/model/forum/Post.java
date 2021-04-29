package com.springprojects.forumdemo.model.forum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Post {
    private int postID, catID;
    private String title, username;
    private LocalDateTime time;

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        return formatter.format(time);
    }

    public LocalDateTime getTimeStamp() {
        return time;
    }

    public void setTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSSSSS");
        this.time = LocalDateTime.parse(time, formatter);
    }

    public void setLocalTimDate(LocalDateTime time) {
        this.time = time;
    }

}

