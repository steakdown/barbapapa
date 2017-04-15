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

    LinkedList<Beer> beers;
    LinkedList<Command> dummyCommandList;

    private LinkedList<Command> createDummyCommandList()
    {
        LinkedList<Command> result = new LinkedList<>();
        Command command = new Command(beers.get(0), 2, "Julien");
        result.push(command);
        command = new Command(beers.get(0), 1, "Sebastian");
        result.push(command);

        return(result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barman_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        beers = Utils.getBeersFromJSON("beers.json", this);
        dummyCommandList = createDummyCommandList();


        ListView commandList = (ListView) findViewById(R.id.commandList);

        String from[]  = {"name"};
        int to[] = {R.id.checkBox};
        int commandLayoutResId = R.layout.barman_command_layout;

        LinkedList<HashMap<String, String>> commands = new LinkedList<HashMap<String, String>>();
        for(int Index = 0; Index < dummyCommandList.size(); ++Index)
        {
            HashMap<String,String> commandMap = new HashMap<>();
            Command command = dummyCommandList.get(Index);
            String text = command.beer.name + " (" + command.beerCount + ") - " + command.clientName;
            commandMap.put("name", text);
            commands.add(commandMap);
        }
        commandList.setAdapter(new SimpleAdapter(this, commands, commandLayoutResId, from, to));

        commandList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
