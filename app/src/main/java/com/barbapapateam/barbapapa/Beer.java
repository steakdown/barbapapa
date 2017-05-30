package com.barbapapateam.barbapapa;


import java.io.Serializable;
import java.util.LinkedList;

public class Beer implements Serializable
{
    public String description;
    public String name;
    public float price;
    public int imageID;
    public float noteSum;
    public float noteCount;
    //public String imagePath;
    public LinkedList<String> opinions;
    public String type;
    public String color;
    public float ABV;
    public boolean bottle;
    public boolean checked;


    public Beer(String name, float price, LinkedList<String> opinions, String type, String color, float ABV, boolean bottle, boolean checked, String description) {
        this.name = name;
        this.price = price;
        //this.imagePath = imagePath;
        this.imageID = Database.getImageIdFromName(name);
        this.opinions = opinions;
        this.type = type;
        this.color = color;
        this.ABV = ABV;
        this.bottle = bottle;
        this.noteSum = 3;
        this.noteCount = 1;
        this.checked = checked;
        this.description = description;
    }

    public float getNote()
    {
        if(noteCount != 0)
            return(noteSum / noteCount);
        else return 0;
    }

    public String getColor(){
        return this.color;
    }

     public String getType(){
         return this.type;
     }

     public float getABVfloat(){
         return  this.ABV;
     }

     public boolean getBottle(){
         return this.bottle;
     }
     
    public void addNote(int note)
    {
        noteSum += note;
        noteCount += 1;
    }

    public String getABV()
    {
        return String.valueOf(ABV) + "%";
    }

    public String getPrice()
    {
        return String.valueOf(price) + "€";
    }

    public String getBottleDesc()
    {
        if(bottle)
            return "Bière en bouteille";
        else
            return "Bière pression";
    }
}
