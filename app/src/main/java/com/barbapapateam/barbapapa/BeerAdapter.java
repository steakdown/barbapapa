package com.barbapapateam.barbapapa;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
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
        if(beer.checked) {
            beerName.setPaintFlags(beerName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            rowView.setBackgroundColor(Color.LTGRAY);
        }
        Picasso.with(mContext).load(Database.getImageIdFromName(beer.name)).into(thumbnail);

        return rowView;
    }

    public void sortBy(int i) {
        switch (i) {
            case 1:
                Collections.sort(mDataSource, new Comparator<Beer>() {
                    @Override
                    public int compare(Beer b1, Beer b2) {
                        if(b1.price > b2.price) return 1;
                        else if(b1.price < b2.price) return -1;
                        else return 0;
                    }
                });
                break;
            case 2:
                Collections.sort(mDataSource, new Comparator<Beer>() {
                    @Override
                    public int compare(Beer b1, Beer b2) {
                        if(b1.ABV > b2.ABV) return 1;
                        else if(b1.ABV < b2.ABV) return -1;
                        else return 0;
                    }
                });
                break;
            case 3:
                Collections.sort(mDataSource, new Comparator<Beer>() {
                    @Override
                    public int compare(Beer b1, Beer b2){
                        return Collator.getInstance().compare(b1.name, b2.name);
                    }
                });
                break;
        }
    }
}
