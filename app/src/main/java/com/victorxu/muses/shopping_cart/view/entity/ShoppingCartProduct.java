package com.victorxu.muses.shopping_cart.view.entity;

public class ShoppingCartProduct {

    private boolean isChecked;

    private String imageUri;

    private String titleText;

    private String attributeText;

    private String price;

    private int number;

    private boolean isEditedMode;

    public ShoppingCartProduct(String imageUri, String titleText, String attributeText, String price, int number) {
        this.imageUri = imageUri;
        this.titleText = titleText;
        this.attributeText = attributeText;
        this.price = price;
        this.number = number;
        this.isChecked = false;
        this.isEditedMode = false;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getAttributeText() {
        return attributeText;
    }

    public void setAttributeText(String attributeText) {
        this.attributeText = attributeText;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isEditedMode() {
        return isEditedMode;
    }

    public void setEditedMode(boolean editedMode) {
        isEditedMode = editedMode;
    }
}
