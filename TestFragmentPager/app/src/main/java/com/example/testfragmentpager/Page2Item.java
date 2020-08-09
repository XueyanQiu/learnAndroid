package com.example.testfragmentpager;

public class Page2Item {
    private int imgId;
    private String  name;

    public String getName() {
        return name;
    }

    public int getImgId() {
        return imgId;
    }
    Page2Item(int imgId, String name){
        this.imgId = imgId;
        this.name = name;
    }
}
