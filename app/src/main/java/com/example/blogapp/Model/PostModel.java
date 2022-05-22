package com.example.blogapp.Model;

public class PostModel {

    String pImage, pTitle, pDescription,uName;

    public PostModel() {
    }

    public PostModel(String pImage, String pTitle, String pDescription,String uName) {
        this.pImage = pImage;
        this.pTitle = pTitle;
        this.pDescription = pDescription;
        this.uName = uName;
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

    public String getpDescription() {
        return pDescription;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

}