package com.barbapapateam.barbapapa;

public class Command
{
    public Beer beer;
    public int beerCount;
    public boolean done;
    public String clientName;

    public Command(Beer beer, int beerCount, String clientName)
    {
        this.beer = beer;
        this.beerCount = beerCount;
        this.done = false;
        this.clientName = clientName;
    }
}
