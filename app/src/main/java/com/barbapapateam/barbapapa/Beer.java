package com.barbapapateam.barbapapa;


import java.util.LinkedList;

public class Beer
{
    public String name;
    public float price;
    public int note;
    public String imagePath;
    public LinkedList<String> opinions;

    public Beer(String name, float price, int note, String imagePath, LinkedList<String> opinions)
    {
        this.name = name;
        this.price = price;
        this.note = note;
        this.imagePath = imagePath;
        this.opinions = opinions;
    }

}
