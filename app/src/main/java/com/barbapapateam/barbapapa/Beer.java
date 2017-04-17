package com.barbapapateam.barbapapa;


import java.util.LinkedList;

public class Beer
{
    public String name;
    public float price;
    public int note;
    public String imagePath;
    public LinkedList<String> opinions;
    public String type;
    public String color;
    public float ABV;
    public boolean bottle;

    public Beer(String name, float price, int note, String imagePath, LinkedList<String> opinions, String type, String color, float ABV, boolean bottle)
    {
        this.name = name;
        this.price = price;
        this.note = note;
        this.imagePath = imagePath;
        this.opinions = opinions;
        this.type = type;
        this.color = color;
        this.ABV = ABV;
        this.bottle = bottle;
    }

    public String getColor(){
        return this.color;
    }

     public String getType(){
         return this.type;
     }

     public float getABV(){
         return  this.ABV;
     }

     public boolean getBottle(){
         return this.bottle;
     }
}
