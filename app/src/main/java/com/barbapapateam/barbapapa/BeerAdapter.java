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


    public BeerAdapter(Context context, LinkedList<Beer> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
        beerType.setText(beer.type);

        Picasso.with(mContext).load(Database.getImageIdFromName(beer.name)).into(thumbnail);

        return rowView;
    }
}
