package com.Fricipe_2.Model;

/**
 * Created by lily on 2017-03-12.
 * Recipe Item Data Model
 */

public class RecipeItem {
    private final int id;
    private final String category;
    private final String recipeName;
    private final String author;
    private final String uploadDate;
    private final String howTo;
    private final String description;
    private final byte[] thumbnail;
    private final byte[] mainImg;
    private final int likeCount;

    public RecipeItem(int id, String category, String recipeName, String author, String uploadDate, String howTo, String description, byte[] thumbnail, byte[] mainImg, int likeCount) {

        this.id = id;
        this.category = category;
        this.recipeName = recipeName;
        this.author = author;
        this.uploadDate = uploadDate;
        this.howTo = howTo;
        this.description = description;
        this.thumbnail = thumbnail;
        this.mainImg = mainImg;
        this.likeCount = likeCount;
    }

    public int get_id() {
        return id;
    }

    public String get_category() {
        return category;
    }

    public String get_recipeName() {
        return recipeName;
    }

    public String get_author() {
        return author;
    }

    public String get_uploadDate() {
        return uploadDate;
    }

    public String get_howTo() {
        return howTo;
    }

    public String get_Description() {
        return description;
    }

    public byte[] get_thumbnail() {
        return thumbnail;
    }

    public byte[] get_mainImg() {
        return mainImg;
    }

    public int get_likeCount() {
        return likeCount;
    }

}
