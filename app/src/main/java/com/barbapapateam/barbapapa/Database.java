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
        imageIDs.put("Superbock", R.drawable.superbock);
        imageIDs.put("San Miguel", R.drawable.sanmiguel);
        imageIDs.put("Asahi", R.drawable.asahi);
        imageIDs.put("Brahma", R.drawable.brahma);
        imageIDs.put("Pilsen Urquell", R.drawable.pilsner_urquell);
        imageIDs.put("Hoegaarden", R.drawable.hoegaarden);
        imageIDs.put("Angelus", R.drawable.angelus);
        imageIDs.put("Duchesse Anne", R.drawable.duchesse_anne);
        imageIDs.put("Goudale", R.drawable.goudale);
        imageIDs.put("Queue de charrue", R.drawable.queue_de_charrue);
        imageIDs.put("Pietra", R.drawable.pietra);
        imageIDs.put("Bush triple", R.drawable.bush_triple_ambree);
        imageIDs.put("Orval", R.drawable.orval);
        imageIDs.put("Brooklyn East IPA", R.drawable.brooklyn_east);
        imageIDs.put("Big Job", R.drawable.big_job);
        imageIDs.put("Carolus", R.drawable.carolus);
        imageIDs.put("Duvel", R.drawable.triple_hop_duvel);
        imageIDs.put("Fruit défendu", R.drawable.fruit_defendu);
        imageIDs.put("Gauloise brune", R.drawable.gauloise_brune);
        imageIDs.put("Mort subite", R.drawable.mort_subite);
        imageIDs.put("Queue de charrue rouge", R.drawable.queue_rouge);
        imageIDs.put("Kasteel rouge", R.drawable.kasteel_rouge);
        imageIDs.put("BrewDog 5AM saint", R.drawable.fiveam_saint);
        imageIDs.put("Faro", R.drawable.faro);
        imageIDs.put("Gueuze", R.drawable.gueuze);
        imageIDs.put("BrewDog Jet Black Heart", R.drawable.jet_black_heart);
        imageIDs.put("Guinness", R.drawable.guinness);
        imageIDs.put("Erdinger", R.drawable.erdinger);
        imageIDs.put("Page 24", R.drawable.page_24);
        imageIDs.put("Vezelay Stout", R.drawable.vezelay);
        imageIDs.put("Kasteel Donker", R.drawable.kasteel_donker);

        commands = new LinkedList<>();
        beers = Utils.getBeersFromJSON("beers.json", context);


        initialized = true;
    }

    static void pushCommand(Beer beer, int beerCount, String clientName)
    {
        Command c = new Command(beer, beerCount, clientName);
        commands.push(c);
    }

    static int getImageIdFromName(String name)
    {
        int id = imageIDs.get(name);
        return id;
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
