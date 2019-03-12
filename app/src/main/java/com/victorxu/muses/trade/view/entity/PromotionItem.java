package com.victorxu.muses.trade.view.entity;

public class PromotionItem {

    private String description;
    private boolean isShowButton;

    public PromotionItem(String description, boolean isShowButton) {
        this.description = description;
        this.isShowButton = isShowButton;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isShowButton() {
        return isShowButton;
    }

    public void setShowButton(boolean showButton) {
        isShowButton = showButton;
    }
}
