package com.barbapapateam.barbapapa;

/**
 * Created by Thomas on 17/04/2017.
 */

public class AdvancedRecommandation {


    private String question;
    private boolean choix;

    // Permettra d'afficher les questions
    private String getQuestion(){
        return question;
    }

    private void setQuestion(String question){
        this.question = question;
    }

    // Permettra d'indiquer la réponse de l'utilisateur à la question (les questions étant binaires)
    private boolean getChoix(){
        return choix;
    }

    private void setChoix(boolean choix){
        this.choix = choix;
    }

}
