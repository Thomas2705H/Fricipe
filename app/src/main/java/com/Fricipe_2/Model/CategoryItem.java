package com.Fricipe_2.Model;

public class CategoryItem {
    private final String category;
    private final byte[] mainImg;

    public CategoryItem(String category, byte[] mainImg) {
        this.category = category;
        this.mainImg = mainImg;
    }

    public String get_category() {
        return category;
    }

    public byte[] get_mainImg() {
        return mainImg;
    }
}
