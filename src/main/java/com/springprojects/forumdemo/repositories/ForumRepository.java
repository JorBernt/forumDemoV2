package com.springprojects.forumdemo.repositories;


import com.springprojects.forumdemo.model.forum.Category;
import com.springprojects.forumdemo.model.forum.Comment;
import com.springprojects.forumdemo.model.forum.Post;
import com.springprojects.forumdemo.model.forum.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ForumRepository {

    @Autowired
    private JdbcTemplate db;

    public List<Category> getCategories() {
        try {
            String sql = "SELECT * FROM Category";
            return db.query(sql, new BeanPropertyRowMapper<>(Category.class));
        } catch (Exception e) {
            return null;
        }
    }

    public String getCategoryTitle(int id) {
        try {
            String sql = "Select title from Category where catID = ?";
            return db.queryForObject(sql, String.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Post> getPosts(int id) {
        String sql = "SELECT Post.*, COUNT(Comment.commentId) as replies From Post, Comment where catID = ? AND Comment.PostId = Post.PostId group by Post.postId;";
        return db.query(sql, new BeanPropertyRowMapper<Post>(Post.class), id);
    }

    public int createPost(Post post) {
        String sql = "INSERT INTO Post (title, username, time, catID) VALUES(?,?,?,?)";
        KeyHolder id = new GeneratedKeyHolder();
        db.update(con -> {
            PreparedStatement par = con.prepareStatement(sql, new String[]{"postid"});
            par.setString(1, post.getTitle());
            par.setString(2, post.getUsername());
            par.setObject(3, post.getTimeStamp());
            par.setInt(4, post.getCatID());
            return par;
        }, id);
        return id.getKeyAs(Integer.class);
    }

    public String getPostTitle(int id) {
        try {
            String sql = "SELECT title FROM Post where postID = ?";
            return db.queryForObject(sql, String.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Comment> getComments(int id) {
        try {
            String sql = "Select * from Comment where postID = ?";
            return db.query(sql, new BeanPropertyRowMapper<>(Comment.class), id);

        } catch (Exception e) {
            return null;
        }
    }

    public void createComment(Comment comment) {
        try {
            String sql = "INSERT INTO Comment (text, username, time, postID) VALUES(?,?,?,?)";
            db.update(sql, comment.getText(), comment.getUsername(), comment.getTimeStamp(), comment.getPostID());
        } catch (Exception e) {
            return;
        }
    }

    public List<UserPost> getUserPosts(String username) {
        try {
            String sql = "Select p.postID as postID, p.title as title, c.text as text, cat.title as category, c.time as time from Post p, Comment c, Category cat where p.postID = c.postID and p.catID = cat.catID and c.username = ?";
            return db.query(sql, new BeanPropertyRowMapper<>(UserPost.class), username);
        } catch (Exception e) {
            return null;
        }
    }


}

