package com.barbapapateam.barbapapa;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;


public class BasicRecomFragment extends Fragment {
    public BasicRecomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ListView beerList = (ListView) view.findViewById(R.id.beer_list_view);

        LinkedList<Beer> beers = (LinkedList<Beer>) Database.beers.clone();
        Collections.shuffle(beers, new Random(0));

        if(Database.lastBeerOrdered != null)
        {
            beers.remove(Database.lastBeerOrdered);
            beers.addFirst(Database.lastBeerOrdered);
        }

        BeerAdapter adapter = new BeerAdapter(getActivity().getApplicationContext(), beers);
        beerList.setAdapter(adapter);

        beerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Beer beer = (Beer) parent.getItemAtPosition(position);
                Database.setBeerForCommand(beer);
                LaunchCommandActivity();
            }
        });

        return view;
    }


    private void LaunchCommandActivity() {

        Intent commandIntent = new Intent(this.getContext(), CommandActivity.class);
        startActivity(commandIntent);
    }
}
