package com.victorxu.muses.product.view.entity;

public class EvaluationItem {

    private String avatarUrl;
    private String userName;
    private String commentDate;
    private String likeNum;
    private String commentNum;
    private String comment;
    private String commentImage1;
    private String commentImage2;
    private String commentImage3;

    public EvaluationItem(String avatarUrl, String userName, String commentDate, String likeNum, String commentNum, String comment, String commentImage1, String commentImage2, String commentImage3) {
        this.avatarUrl = avatarUrl;
        this.userName = userName;
        this.commentDate = commentDate;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
        this.comment = comment;
        this.commentImage1 = commentImage1;
        this.commentImage2 = commentImage2;
        this.commentImage3 = commentImage3;
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

    public String getCommentImage1() {
        return commentImage1;
    }

    public void setCommentImage1(String commentImage1) {
        this.commentImage1 = commentImage1;
    }

    public String getCommentImage2() {
        return commentImage2;
    }

    public void setCommentImage2(String commentImage2) {
        this.commentImage2 = commentImage2;
    }

    public String getCommentImage3() {
        return commentImage3;
    }

    public void setCommentImage3(String commentImage3) {
        this.commentImage3 = commentImage3;
    }
}
