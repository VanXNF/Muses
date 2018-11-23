package com.victorxu.muses.product.view.entity;

import java.util.List;

public class EvaluationItem {

    private String avatarUrl;
    private String userName;
    private String commentDate;
    private String likeNum;
    private String commentNum;
    private String comment;
    private float rank;
    private List<String> commentImages;
    private String type;

    public EvaluationItem(String avatarUrl, String userName, String commentDate, String likeNum, String commentNum, String comment, List<String> commentImages) {
        this.avatarUrl = avatarUrl;
        this.userName = userName;
        this.commentDate = commentDate;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
        this.comment = comment;
        this.commentImages = commentImages;
    }

    public EvaluationItem(String avatarUrl, String userName, String commentDate, String likeNum, String commentNum, String comment, float rank, List<String> commentImages, String type) {
        this.avatarUrl = avatarUrl;
        this.userName = userName;
        this.commentDate = commentDate;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
        this.comment = comment;
        this.rank = rank;
        this.commentImages = commentImages;
        this.type = type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    public List<String> getCommentImages() {
        return commentImages;
    }

    public void setCommentImages(List<String> commentImages) {
        this.commentImages = commentImages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
