package com.barbapapateam.barbapapa;

import android.content.Context;

import java.util.LinkedList;

/**
 * Created by hugo on 17/04/17.
 */

public class Database {

    static LinkedList<Beer> beers;
    static LinkedList<Command> commands;
    static boolean initialized = false;

    static void initDb(Context context)
    {
        beers = Utils.getBeersFromJSON("beers.json", context);
        commands = new LinkedList<>();
        initialized = true;
    }

    static void pushCommand(Beer beer, int beerCount, String clientName)
    {
        Command c = new Command(beer, beerCount, clientName);
        commands.push(c);
    }

}
