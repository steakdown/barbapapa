package com.barbapapateam.barbapapa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by timotheetallon on 16/04/2017.
 */

public class CommandActivity extends AppCompatActivity {

    private Beer beer = null;
    //TextView beerText = null;

    /*public static void start(Context context, Beer beer)
    {
        Intent intent = new Intent(context, CommandActivity.class);
        //beer = (Beer) intent.getSerializableExtra("BEER");
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Intent intent = getIntent();

        TextView beerText = (TextView) findViewById(R.id.beerText);



    }

}
