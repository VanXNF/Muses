package com.victorxu.muses.gallery.view.entity;

public class TicketItem {

    private int percentage;
    private String subDesc;
    private String desc;
    private int score;

    public TicketItem(int percentage, String subDesc, String desc, int score) {
        this.percentage = percentage;
        this.subDesc = subDesc;
        this.desc = desc;
        this.score = score;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getSubDesc() {
        return subDesc;
    }

    public void setSubDesc(String subDesc) {
        this.subDesc = subDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
