package com.springprojects.forumdemo.controllers;

import com.springprojects.forumdemo.model.forum.Category;
import com.springprojects.forumdemo.model.forum.Comment;
import com.springprojects.forumdemo.model.forum.Post;
import com.springprojects.forumdemo.model.forum.UserPost;
import com.springprojects.forumdemo.repositories.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ForumController {

    @Autowired
    private ForumRepository repo;


    @GetMapping("/getCategories")
    public List<Category> getCategories(HttpServletResponse response) throws IOException {
        List<Category> categories = repo.getCategories();
        if (categories != null) return categories;
        else response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error in DB, try again later");
        return null;
    }

    @GetMapping("/getCategoryTitle")
    public String getCategoryTitle(int id) {
        return repo.getCategoryTitle(id);
    }

    @GetMapping("/getPosts")
    public List<Post> getPosts(int id, HttpServletResponse response) throws IOException {
        List<Post> posts = repo.getPosts(id);
        System.out.println(posts.size());
        if (posts != null) return posts;
        else response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error in DB, try again");
        return null;
    }

    @PostMapping("/createPost")
    public int createPost(Post post) {
        LocalDateTime now = LocalDateTime.now();
        post.setLocalTimDate(now);
        return repo.createPost(post);
    }

    @GetMapping("/getPostTitle")
    public String getPostTitle(int id) {
        return repo.getPostTitle(id);
    }

    @GetMapping("/getComments")
    public List<Comment> getComments(int id) {
        return repo.getComments(id);
    }

    @PostMapping("/createComment")
    public void createComment(Comment comment) {
        LocalDateTime now = LocalDateTime.now();
        comment.setLocalTimDate(now);
        repo.createComment(comment);
    }

    @GetMapping("/getUserPosts")
    public List<UserPost> getUserPosts(String username) {
        return repo.getUserPosts(username);
    }
}
