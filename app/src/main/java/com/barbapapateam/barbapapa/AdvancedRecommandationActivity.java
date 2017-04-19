package com.barbapapateam.barbapapa;

/**
 * Created by Thomas on 16/04/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class AdvancedRecommandationActivity extends Activity implements View.OnClickListener {



    /* Pour les questions, on choisira des questions ayant pour réponse "oui" ou "non""
     ex : -Voulez-vous une bière forte ?
          -Non.
          -Plutôt douce ?.
          NextQuestion();
     Dans un 1er temps, l'utilisateur devra toujours valider en cliquant sur "Oui" pour passer à la question suivante.
    */

    //Zone de texte contenant la question
    TextView t1;

    //Zone d'image contenant les images
    ImageView v1;

    //On charge les bière dans une liste, et en fonction des réponses aux questions, on supprimera des éléments de la liste
    LinkedList<Beer> beers;

    public AdvancedRecommandationActivity() {
        // Required empty public constructor
    }



    //On distinguera les questions en questions principales (question1, 2 et 3) et questions secondaires (contenue des question1, 2 et 3)
    private String[] question1 = {"Voulez vous une bière en Pression ?", "Donc plutôt en bouteille ?"};
    private String[] question2 = {"Voulez vous une bière Blonde ?", "Plutôt blanche donc ?" ,"Plutôt ambrée du coup ? ", "Non plus ? Plutôt brune alors ?"};
    private String[] question3 = {"Voulez vous une bière forte ?", "On part sur une bière plutôt douce donc ?"};

    private String[][] questions = {question1, question2, question3};

    private boolean[] bottle = {true, false};
    private String[] color = {"Blonde", "Blanche", "Ambrée", "Brune"};
    private String[] degre = {"forte", "douce"};
    private String[][] attributes = {{},color, degre};

    private int[] image1 = {R.drawable.pression,R.drawable.bouteille};
    private int[] image2 = {R.drawable.blonde,R.drawable.blanch,R.drawable.ambree,R.drawable.brune};
    private int[] image3 = {R.drawable.forte, R.drawable.douce};
    private int[][] images = {image1,image2,image3};


    private int indice1 = 0 ;
    private int indice2 = 0 ;
    String question = questions[0][0];
    int image = images[0][0];


    //Affiche le résultat de la recherche
    private LinkedList<Beer> getResult(){
        //Affiche la liste des bières correspondantes
        return beers;
    }

    //Fonction proposant la bière la mieux notée correspondant aux goûts de l'utilisateur
    public Beer getMyBeer(LinkedList<Beer> listResult ){
        Beer myBeer = listResult.getFirst();
        if(listResult.size() != 0){
            for (int i = 1; i< listResult.size(); i++){
                if(listResult.get(i).getNote()> myBeer.getNote()){
                    myBeer = listResult.get(i);
                }
            }
        } else {
            t1.setText("Aucun résultat");
        }
        return myBeer;
    }

    //Permet de passer à la question principale suivante
    private void getNextQuestion(){
        indice1++;
        if(indice1 == 3){
            t1.setText("ok");
            /*
            LinkedList<Beer> listResult = getResult();
            if(listResult.size() != 0) {
                LaunchCommandActivity(getMyBeer(listResult));
            } else {
                t1.setText("Aucun résultat");
            }*/
        } else {
            indice2 = 0;
            question = questions[indice1][indice2];
            t1.setText(question);
            image = images[indice1][indice2];
            v1.setImageResource(image);
        }
    }

    //Permet de passer à la question principale précédente.
    private void getPreviousQuestion(){
        indice1--;
        if (indice1 == -1) {
            indice1 = 0;
        } else {
            indice2 = 0;
            question = questions[indice1][indice2];
            t1.setText(question);
            image = images[indice1][indice2];
            v1.setImageResource(image);
        }
    }

    //Permet de passer à la question secondaire suivante.
    private void getNextNuance(){
        indice2++;
        if (indice1 == 0) {
            if (indice2 == 2) {
                indice2 = 0;
                question = questions[indice1][indice2];
                image = images[indice1][indice2];
            } else {
                question = questions[indice1][indice2];
                image = images[indice1][indice2];
            }
        } else if (indice1 == 1) {
            if (indice2 == 4) {
                indice2 = 0;
                question = questions[indice1][indice2];
                image = images[indice1][indice2];
            } else {
                question = questions[indice1][indice2];
                image = images[indice1][indice2];
            }
        } else {
            if(indice2 == 2 ){
                indice2 = 0;
                question = questions[indice1][indice2];
                image = images[indice1][indice2];
            } else {
                question = questions[indice1][indice2];
                image = images[indice1][indice2];
            }
        }
        t1.setText(question);
        v1.setImageResource(image);
    }

    // L'utilisateur répond oui
    //Cette fonction seulement supprimera des éléments de la liste
    private void yes(){
        //On supprime des éléments de la liste.
        if (indice1 == 2){
            if(indice2 == 0){
                for(int i = 0; i< beers.size(); i++){
                    Beer beer = beers.get(i);
                    if(beer.ABV < 6){
                        beers.remove(i);
                    }
                }
            } else if (indice2 == 1){
                for(int i = 0; i< beers.size(); i++){
                    Beer beer = beers.get(i);
                    if(beer.ABV >= 6){
                        beers.remove(i);
                    }
                }
            }
        } else if (indice1 == 0) {
            //pour les autres question, les listes dans attributes correspondent a la valeur des attributs de Beer.
            for (int i = 0; i < beers.size(); i++) {
                Beer beer = beers.get(i);
                if (beer.getBottle() != bottle[indice2]){
                    beers.remove(i);
                }
            }
        } else if (indice1 == 1) { //pour les autres question, les listes dans attributes correspondent a la valeur des attributs de Beer.
            for (int i = 0; i < beers.size(); i++) {
                Beer beer = beers.get(i);
                if (!beer.getColor().equals(attributes[indice1][indice2])){
                    beers.remove(i);
                }
            }
        }
        //On passe à la question suivante.
        getNextQuestion();
    }

    // L'utilisateur répond non, on passe à la question secondaire suivante
    private void no(){
        getNextNuance();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_recommandation);

        /*//Ajout d'une bar d'action
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        //On charge les bière dans une liste, et en fonction des réponses aux questions, on supprimera des éléments de la liste
        beers = Utils.getBeersFromJSON("beers.json", this);

        //Zone de texte contenant la question
        t1 = (TextView) findViewById(R.id.ARtextView);

        v1 = (ImageView) findViewById(R.id.ARImageView);


        //connection des boutons entre model et view
        ImageButton noB = (ImageButton) findViewById(R.id.imageButtonCross);
        ImageButton yesB = (ImageButton) findViewById(R.id.imageButtonValid);
        ImageButton goBackB = (ImageButton) findViewById(R.id.imageButtonBack);

        noB.setOnClickListener(this);
        yesB.setOnClickListener(this);
        goBackB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageButtonCross:
                no();
                break;

            case R.id.imageButtonValid:
                yes();
                break;

            case R.id.imageButtonBack:
                getPreviousQuestion();
                break;
        }
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
/*
    private void LaunchCommandActivity(Beer beer) {

        //Intent commandIntent = new Intent(this.getContext(), CommandActivity.class);
      //  Database.setBeerForCommand(beer);
     //   startActivity(commandIntent);

    }*/
}
