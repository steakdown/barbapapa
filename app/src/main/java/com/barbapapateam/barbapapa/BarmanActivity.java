package com.barbapapateam.barbapapa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by hugo on 15/04/17.
 */

public class BarmanActivity extends AppCompatActivity 
{

	static boolean debugInitialized = false;

    private void createDummyCommandList()
    {

        Command command = new Command(Database.beers.get(0), 2, "Julien");
        Database.commands.push(command);
        command = new Command(Database.beers.get(0), 1, "Sebastian");
        Database.commands.push(command);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barman_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

		if(!debugInitialized)
		{
			createDummyCommandList();
			debugInitialized = true;
		}


        ListView commandList = (ListView) findViewById(R.id.commandList);

        String from[]  = {"beer_name", "client_name"};
        int to[] = {R.id.beer_name, R.id.client_name};
        int commandLayoutResId = R.layout.barman_command_layout;

        LinkedList<HashMap<String, String>> commands = new LinkedList<HashMap<String, String>>();
        for(int Index = 0; Index < Database.commands.size(); ++Index)
        {
            HashMap<String,String> commandMap = new HashMap<>();
            Command command = Database.commands.get(Index);
            String text = command.beer.name + " (" + command.beerCount + ")";
            commandMap.put("beer_name", text);
            commandMap.put("client_name", command.clientName);
            commands.add(commandMap);
        }
        commandList.setAdapter(new SimpleAdapter(this, commands, commandLayoutResId, from, to));

        commandList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Database.commands.remove(position);
				recreate();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.debug_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.goToActivity(BarmanActivity.this, MainActivity.class);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
