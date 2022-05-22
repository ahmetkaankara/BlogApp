package com.example.blogapp.Model;

public class PostModel {
    String pImage, pTitle, pDesc;

    public PostModel() {
    }

    public PostModel(String pImage, String pTitle, String pDesc) {
        this.pImage = pImage;
        this.pTitle = pTitle;
        this.pDesc = pDesc;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }
}
