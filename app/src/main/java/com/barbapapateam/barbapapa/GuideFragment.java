package com.barbapapateam.barbapapa;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GuideFragment extends Fragment {


    public GuideFragment() {
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

        // Inflate the layout for this fragment
        return view;
    }


    private void LaunchCommandActivity(Beer beer) {

        Intent commandIntent = new Intent(this.getContext(), CommandActivity.class);
        commandIntent.putExtra("BEER", beer);
        startActivity(commandIntent);

    }
}
