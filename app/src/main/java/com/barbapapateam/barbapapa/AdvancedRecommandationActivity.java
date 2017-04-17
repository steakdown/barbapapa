package com.barbapapateam.barbapapa;

/**
 * Created by Thomas on 16/04/2017.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class AdvancedRecommandationActivity extends Activity {

    /* Pour les questions, on choisira des questions ayant pour réponse "oui" ou "non""
     ex : -Voulez-vous une bière forte ?
          -Non.
          -Plutôt douce ?.
          NextQuestion();
     Dans un 1er temps, l'utilisateur devra toujours valider en cliquant sur "Oui" pour passer à la question suivante.
    */

    //On distinguera les questions en questions principales (question1, 2 et 3) et questions secondaires (contenue des question1, 2 et 3)
    String[] question1 = {"Voulez vous une bière en Pression ?", "Donc plutôt en bouteille ?"};
    String[] question2 = {"Voulez vous une bière Blonde ?", "Plutôt blanche ?" ,"Plutôt ambrée", "Plutôt brune ?"};
    String[] question3 = {"Voulez vous une bière forte ?", "Plutôt douce ?"};

    private String[][] questions = {question1, question2, question3};

    private boolean[] bottle = {true, false};
    private String[] color = {"Blonde", "Blanche", "Ambrée", "Brune"};
    private String[] degre = {"forte", "douce"};

    private String[][] attributes = {{},color, degre};
    private int indice1 = 0 ;
    private int indice2 = 0 ;
    String question = questions[0][0];

    //On charge les bière dans une liste, et en fonction des réponses aux questions, on supprimera des éléments de la liste
    LinkedList<Beer> beers = Utils.getBeersFromJSON("beers.json", this);


    //Permet de passer à la question principale suivante
    private void getNextQuestion(){
        indice1++;
        indice2 = 0;
        question = questions[indice1][indice2];
    }

    //Permet de passer à la question principale précédente.
    private void getPreviousQuestion(){
        indice1--;
        indice2 = 0;
        question = questions[indice1][indice2];
    }

    //Permet de passer à la question secondaire suivante.
    private void getNextNuance(){
        indice2++;
        if(questions[indice1][indice2] == null){

        } else {
            question = questions[indice1][indice2];
        }
    }

    // L'utilisateur répond oui
    //Cette fonction seulement supprimera des éléments de la liste
    private void yes(){
        //On supprime des éléments de la liste.
        if (indice1 == 2){
            for(int i = 0; i< beers.size(); i++){
                Beer beer = beers.get(i);
                //L'utilisateur veut une bière forte
                //Dans le cas du degré, on doit faire un prétraite car ABV est un float
                if(attributes[indice1][indice2] == "forte")
                    if(beer.getABV() <= 6)
                        beers.remove(i);
                //L'utilisateur veut une bière douce.
                else
                    if(beer.getABV()>6)
                        beers.remove(i);
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
                    if (beer.getColor() != attributes[indice1][indice2]){
                        beers.remove(i);
                    }
                }
            }
        //On passe à la question suivante.
        getNextQuestion();
    }

    // L'utilisateur répond non, on passe à la question secondaire suivante
    private void non(){
        getNextNuance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_recommandation);

        //Ajout d'une bar d'action
        ActionBar actionBar = getActionBar();
        //
        actionBar.setDisplayHomeAsUpEnabled(true);
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
        switch(item.getItemId()){
            //Display back button on action bar
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
