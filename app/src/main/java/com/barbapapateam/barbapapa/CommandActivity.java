package com.barbapapateam.barbapapa;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Created by timotheetallon on 16/04/2017.
 */

public class CommandActivity extends AppCompatActivity {

    private Beer beer = null;
    private int beerNumber = 1;
    private TextView number;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command_layout);

        beer = Database.getBeerForCommand();


        TextView beerText = (TextView) findViewById(R.id.beerText);
        beerText.setText(beer.name);

        // RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        // ratingBar.setRating(beer.getNote());

        ImageView beerImage = (ImageView) findViewById(R.id.beerPhoto);
        beerImage.setImageResource(beer.imageID);



        TextView type = (TextView) findViewById(R.id.typeText);
        type.setText(beer.type);

        TextView color = (TextView) findViewById(R.id.colorText);
        color.setText("Couleur : " + beer.color);

        TextView abv = (TextView) findViewById(R.id.ABVText);
        abv.setText(beer.getABV());

        TextView bottle = (TextView) findViewById(R.id.bottleText);
        bottle.setText(beer.getBottleDesc());

        // TextView price = (TextView) findViewById(R.id.priceText);
        // price.setText(beer.getPrice());

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(beer.description);

        ImageButton cancel = (ImageButton) findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                returnToMainActivity();
            }
        });

        number = (TextView) findViewById(R.id.number);
        number.setText(String.valueOf(beerNumber));

        ImageButton minus = (ImageButton) findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                if(beerNumber > 1)
                {
                    beerNumber--;
                    number.setText(String.valueOf(beerNumber));
                }
            }
        });

        ImageButton plus = (ImageButton) findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                if(beerNumber < 10)
                {
                    beerNumber++;
                    number.setText(String.valueOf(beerNumber));
                }
            }
        });

		final Button commanderButton = (Button) findViewById(R.id.commanderButton);
        final Button retourButton = (Button) findViewById(R.id.retourButton);

        if(beer.checked){
            commanderButton.setText("Checked");
            retourButton.setText("Annuler");
        } else {
            commanderButton.setText("Check-in");
            retourButton.setText("Retour");
        }
		commanderButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View view)
			{
                //Database.pushCommand(Database.getBeerForCommand(), beerNumber, "userName");
                //Database.lastBeerOrdered = Database.getBeerForCommand();
                if(!beer.checked) {
                    beer.checked = true;
                    Utils.goToActivity(CommandActivity.this, MainActivity.class);
                }
			}
		});

        retourButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                if(beer.checked) {
                    beer.checked = false;
                }
                Utils.goToActivity(CommandActivity.this, MainActivity.class);
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
