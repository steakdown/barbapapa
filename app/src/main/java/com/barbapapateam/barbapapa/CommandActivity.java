package com.barbapapateam.barbapapa;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by timotheetallon on 16/04/2017.
 */

public class CommandActivity extends AppCompatActivity {

    private Beer beer = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command_layout);


        Intent intent = getIntent();
        beer = (Beer) intent.getSerializableExtra("BEER");

        TextView beerText = (TextView) findViewById(R.id.beerText);
        beerText.setText(beer.name);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(beer.note);

        ImageView beerImage = (ImageView) findViewById(R.id.beerPhoto);
        beerImage.setImageResource(beer.imageID);



        TextView type = (TextView) findViewById(R.id.typeText);
        type.setText(beer.type);

        TextView color = (TextView) findViewById(R.id.colorText);
        color.setText(beer.color);

        TextView abv = (TextView) findViewById(R.id.ABVText);
        abv.setText(beer.getABV());

        TextView bottle = (TextView) findViewById(R.id.bottleText);
        bottle.setText(beer.getBottleDesc());

        TextView price = (TextView) findViewById(R.id.priceText);
        price.setText(beer.getPrice());

        ImageButton cancel = (ImageButton) findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                returnToMainActivity();
            }
        });
    }

    public void returnToMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
