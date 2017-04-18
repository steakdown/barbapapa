package com.barbapapateam.barbapapa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Hashtable;

public class NotationActivity extends AppCompatActivity {

    private TextView noteText;
	private Hashtable<String, Integer> imageIDs;

	private int chosenBeerIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

		TextView text = (TextView) findViewById(R.id.textView);

		// TODO(hugo) : We should fail if we get -1 at this step
		chosenBeerIndex = findBeerIndexFromBeer(Database.getBeerForCommand());
		text.setText("Notez votre bière (" + Database.beers.get(chosenBeerIndex).name + ")");

        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                noteText.setText(progress + "/5");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        noteText = (TextView) findViewById(R.id.textView2);
        noteText.setText(seekBar.getProgress() + "/5");

        Button backToMenu = (Button) findViewById(R.id.button_back_to_menu);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchMainActivity();
            }
        });

        Button addNoteButton = (Button) findViewById(R.id.button_add_note);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // NOTE(hugo) : Can be improved since it's not _exactly_
                // the beer from the last command. But should work for the MVP
                Database.beers.get(chosenBeerIndex).addNote(seekBar.getProgress());
                LaunchMainActivity();
            }
        });

		// TODO(hugo): Maybe refactor this into the DB ?
		ImageView thumbnail = (ImageView) findViewById(R.id.imageView);
		Picasso.with(this).load(Database.getImageIdFromName(Database.beers.get(chosenBeerIndex).name)).into(thumbnail);
    }

    private void LaunchMainActivity()
    {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

	private int findBeerIndexFromBeer(Beer beer)
	{
		for(int BeerIndex = 0; BeerIndex < Database.beers.size(); ++BeerIndex)
		{
			Beer dbBeer = Database.beers.get(BeerIndex);
			if(dbBeer.name.equals(beer.name))
			{
				return(BeerIndex);
			}
		}

		return(-1);
	}

}
