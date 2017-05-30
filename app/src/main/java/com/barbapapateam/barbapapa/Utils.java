package com.barbapapateam.barbapapa;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.LinkedList;

/**
 * Created by hugo on 15/04/17.
 */

public class Utils {

    static LinkedList<Beer> getBeersFromJSON(String file, Context context)
    {
        LinkedList<Beer> beers = new LinkedList<>();
        try {
            InputStream is = context.getAssets().open("beers.json");
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            String jsonString = new String(buffer, "UTF-8");
            JSONObject jsonBeers = new JSONObject(jsonString);
            JSONArray beersJsonArray = jsonBeers.getJSONArray("beers");
            for(int BeerIndex = 0; BeerIndex < beersJsonArray.length(); ++BeerIndex)
            {
                JSONObject jsonBeer = beersJsonArray.getJSONObject(BeerIndex);
                String beerName = jsonBeer.getString("name");
                String beerType = jsonBeer.getString("type");
                String beerColor = jsonBeer.getString("color");
                float beerABV = Float.parseFloat(jsonBeer.getString("ABV"));
                float beerPrice = Float.parseFloat(jsonBeer.getString("price"));
                boolean beerBottle = jsonBeer.getString("bottle").equals("true");
                String path = jsonBeer.getString("path");
                boolean checked = jsonBeer.getString("checked").equals("true");
                String description = jsonBeer.getString("description");

                Beer beer = new Beer(beerName, beerPrice, null, beerType, beerColor, beerABV, beerBottle, checked, description);
                beers.push(beer);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return(beers);
    }

    static public void goToActivity(Context context, Class<?> activityClass)
    {
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }

    static void saveBeersToJSON(LinkedList<Beer> beers) {
        Gson gson = new Gson();
        try {
            Writer osWriter = new OutputStreamWriter( new FileOutputStream("beers.json"));
            gson.toJson(beers, osWriter);
            osWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
