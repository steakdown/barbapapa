package com.barbapapateam.barbapapa;

import android.content.Context;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Created by hugo on 17/04/17.
 */

public class Database {

    static LinkedList<Beer> beers;
    static LinkedList<Command> commands;
    static boolean initialized = false;
    private static Hashtable<String, Integer> imageIDs;
    private static Beer beerForCommand;

    public static Beer lastBeerOrdered = null;

    static void initDb(Context context)
    {
        imageIDs = new Hashtable<String, Integer>();
        imageIDs.put("Kwak", R.drawable.kwak);
        imageIDs.put("Delirium", R.drawable.delirium);
        imageIDs.put("Tripel Karmeliet", R.drawable.karmeliet);
        imageIDs.put("Leffe", R.drawable.leffe);
        imageIDs.put("Desperados", R.drawable.desperados);
        imageIDs.put("Kriek", R.drawable.kriek);
        imageIDs.put("Punk", R.drawable.punk);
        imageIDs.put("Cuvée des Trolls", R.drawable.trolls);
        imageIDs.put("1664 Blanc", R.drawable.seize);
        imageIDs.put("Pêcheresse", R.drawable.pecheresse);
        imageIDs.put("Elephant", R.drawable.elephant);
        imageIDs.put("Kronenbourg", R.drawable.kronenbourg);
        imageIDs.put("Grimbergen Blanche", R.drawable.blanche);
        imageIDs.put("Grimbergen Double Ambrée", R.drawable.grim_ambree);

        beers = Utils.getBeersFromJSON("beers.json", context);
        commands = new LinkedList<>();

        initialized = true;
    }

    static void pushCommand(Beer beer, int beerCount, String clientName)
    {
        Command c = new Command(beer, beerCount, clientName);
        commands.push(c);
    }

    static int getImageIdFromName(String name)
    {
        return imageIDs.get(name);
    }

    public static void setBeerForCommand(Beer beer)
    {
        beerForCommand = beer;
    }

    public static Beer getBeerForCommand()
    {
        return beerForCommand;
    }

}
