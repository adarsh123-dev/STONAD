package com.stonad.model;

public class Comment {

    private int id;
    private int artworkId;
    private int userId;
    private String username;
    private String comment;
    private String createdAt;

    public Comment() {
    }

    public Comment(int id, int artworkId, int userId,
                   String username, String comment, String createdAt) {
        this.id = id;
        this.artworkId = artworkId;
        this.userId = userId;
        this.username = username;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}