package com.example.uworthy;

public class Item {

    String birdListName;
    int birdListImage;
    String name;

    public Item(String birdName,int birdImage)
    {
        this.birdListImage=birdImage;
        this.birdListName=birdName;
    }
    public String getbirdName()
    {
        return birdListName;
    }
    public int getbirdImage()
    {
        return birdListImage;
    }
    public String getname(){
        return birdListName;
    }
}
