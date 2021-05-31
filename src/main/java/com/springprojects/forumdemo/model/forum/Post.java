package com.springprojects.forumdemo.model.forum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Post {
    private int postID, catID, replies;
    private String title, username;
    private LocalDateTime time;

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        return formatter.format(time);
    }

    public void setTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSSSSS");
        this.time = LocalDateTime.parse(time, formatter);
    }

    public LocalDateTime getTimeStamp() {
        return time;
    }

    public void setLocalTimDate(LocalDateTime time) {
        this.time = time;
    }

}

