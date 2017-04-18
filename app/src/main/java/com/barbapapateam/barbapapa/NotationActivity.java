package com.barbapapateam.barbapapa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class NotationActivity extends AppCompatActivity {

    private TextView noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

		TextView text = (TextView) findViewById(R.id.textView);
		// TODO(hugo) : Replace Database.beers.get(3) with the chosen beer
		text.setText("Notez votre bière (" + Database.Database.beers.get(3).name + ")");

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
                Database.beers.get(3).addNote(seekBar.getProgress());
                LaunchMainActivity();
            }
        });
    }

    private void LaunchMainActivity()
    {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

}
