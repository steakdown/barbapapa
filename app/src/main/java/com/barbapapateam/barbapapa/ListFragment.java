package com.barbapapateam.barbapapa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.LinkedList;


public class ListFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private LinkedList<Beer> beers;
    private BeerAdapter adapter;
    private ListView beerList;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        beerList = (ListView) view.findViewById(R.id.beer_list_view);

        LinkedList<Beer> beers = Database.beers;
        adapter = new BeerAdapter(getActivity().getApplicationContext(), beers);
        beerList.setAdapter(adapter);

        beerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                LinkedList<Beer> beers = Database.beers;
                Beer beer = (Beer) parent.getItemAtPosition(position);
                Database.setBeerForCommand(beer);
                LaunchCommandActivity();
            }
        });

        Spinner spinner = (Spinner) view.findViewById(R.id.sort_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.sort_options, R.layout.spinner_layout);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
        return view;
    }


    private void LaunchCommandActivity() {

        Intent commandIntent = new Intent(this.getContext(), CommandActivity.class);
        startActivity(commandIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapter.sortBy(i);
        beerList.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onRadioButtonClicked(View view) {
        /*boolean checked = ((CheckBox) view).isChecked();

        if(checked)
            switch(view.getId()) {
                case R.id.radio_pression:
                    adapter.getFilter().filter("draught");
                    break;
                case R.id.radio_bouteille:
                    adapter.getFilter().filter("bottle");
                    break;
            }*/
    }
}
