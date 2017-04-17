package com.barbapapateam.barbapapa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;


public class ListFragment extends Fragment {

    public ListFragment() {
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

        LinkedList<Beer> beers = Utils.getBeersFromJSON("beers.json", getActivity().getApplicationContext()); /*
        String[] names = new String[beers.size()];
        for(int i = 0; i < beers.size(); i++){
            Beer beer = beers.get(i);
            names[i] = beer.name;
        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, names);*/
        BeerAdapter adapter = new BeerAdapter(getActivity().getApplicationContext(), beers);
        beerList.setAdapter(adapter);
        return view;
    }
}
