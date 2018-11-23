package com.victorxu.muses.message.view.entity;

public class ListMessageItem {

    private String imageUrl;
    private String userName;
    private String messageContent;
    private String messageTime;
    private int messageCount;

    public ListMessageItem(String imageUrl, String userName, String messageContent, String messageTime, int messageCount) {
        this.imageUrl = imageUrl;
        this.userName = userName;
        this.messageContent = messageContent;
        this.messageTime = messageTime;
        this.messageCount = messageCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }
}
