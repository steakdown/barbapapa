package com.barbapapateam.barbapapa;

/**
 * Created by Thomas on 16/04/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.LinkedList;

public class AdvancedRecommandationActivity extends Activity implements View.OnClickListener {

    //Zone de texte contenant la question
    TextView t1;

    /* Pour les questions, on choisira des questions ayant pour réponse "oui" ou "non""
     ex : -Voulez-vous une bière forte ?
          -Non.
          -Plutôt douce ?.
          NextQuestion();
     Dans un 1er temps, l'utilisateur devra toujours valider en cliquant sur "Oui" pour passer à la question suivante.
    */

    //On charge les bière dans une liste, et en fonction des réponses aux questions, on supprimera des éléments de la liste
    LinkedList<Beer> beers;


    //On distinguera les questions en questions principales (question1, 2 et 3) et questions secondaires (contenue des question1, 2 et 3)
    String[] question1 = {"Voulez vous une bière en Pression ?", "Donc plutôt en bouteille ?"};
    String[] question2 = {"Voulez vous une bière Blonde ?", "Plutôt blanche donc ?" ,"Plutôt ambrée du coup ? ", "Non plus ? Plutôt brune alors ?"};
    String[] question3 = {"Voulez vous une bière forte ?", "On part sur une bière plutôt douce donc ?"};

    private String[][] questions = {question1, question2, question3};

    private boolean[] bottle = {true, false};
    private String[] color = {"Blonde", "Blanche", "Ambrée", "Brune"};
    private String[] degre = {"forte", "douce"};

    private String[][] attributes = {{},color, degre};
    private int indice1 = 0 ;
    private int indice2 = 0 ;
    String question = questions[0][0];


    //Affiche le résultat de la recherche
    private LinkedList<Beer> getResult(){
        //Affiche la liste des bières correspondantes
        return beers;
    }

    //Permet de passer à la question principale suivante
    private void getNextQuestion(){
        indice1++;
        if(indice1 == 3){
            getResult();
        } else {
            indice2 = 0;
            question = questions[indice1][indice2];
            t1.setText(question);
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
        }
    }

    //Permet de passer à la question secondaire suivante.
    private void getNextNuance(){
        indice2++;
        if (indice1 == 0) {
           if (indice2 == 2) {
               indice2 = 0;
               question = questions[indice1][indice2];
           } else {
               question = questions[indice1][indice2];
           }
       } else if (indice1 == 1) {
           if (indice2 == 4) {
               indice2 = 0;
               question = questions[indice1][indice2];
           } else {
               question = questions[indice1][indice2];
           }
       } else {
           if(indice2 == 2 ){
               indice2 = 0;
               question = questions[indice1][indice2];
           } else {
               question = questions[indice1][indice2];
           }
        }
        t1.setText(question);
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
                if(indice2 == 0)

                    if(beer.getABVfloat() <= 6)
                        beers.remove(i);
                //L'utilisateur veut une bière douce.
                else
                    if(beer.getABVfloat()>6)

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
                    if (beer.getColor().equals(attributes[indice1][indice2])){
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


}
