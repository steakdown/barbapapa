package com.barbapapateam.barbapapa;


import java.io.Serializable;
import java.util.LinkedList;

public class Beer implements Serializable
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

    public Beer(String name, float price, int note, String imagePath, LinkedList<String> opinions, String type, String color, float ABV, boolean bottle) {
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

    public String getDescriptiveText()
    {
        //TODO : pour faciliter le display des infos
        return null;
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
