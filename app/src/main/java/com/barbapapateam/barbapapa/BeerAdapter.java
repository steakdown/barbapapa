package com.barbapapateam.barbapapa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Created by dave on 17/04/17.
 */

public class BeerAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private LinkedList<Beer> mDataSource;
    private Hashtable<String, Integer> imageIDs;

    public BeerAdapter(Context context, LinkedList<Beer> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    }

    //1
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_item_beer, parent, false);

        TextView beerName =
                (TextView) rowView.findViewById(R.id.beer_name);

        TextView beerABV =
                (TextView) rowView.findViewById(R.id.beer_ABV);

        TextView beerPrice =
                (TextView) rowView.findViewById(R.id.beer_price);

        TextView beerType =
                (TextView) rowView.findViewById(R.id.beer_type);
        ImageView thumbnail = (ImageView) rowView.findViewById(R.id.beer_photo);

        Beer beer = (Beer) getItem(position);
        beerName.setText(beer.name);
        beerABV.setText(beer.ABV + "%");
        beerPrice.setText(beer.price + "€");
        beerType.setText("Style : " + beer.type);

        Picasso.with(mContext).load(imageIDs.get(beer.name)).into(thumbnail);

        return rowView;
    }
}
