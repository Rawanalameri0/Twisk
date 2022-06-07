package twisk.mondeIG;

import twisk.vues.Observateur;

import java.util.ArrayList;

/**
 * Classe SujetObserve
 */
public class SujetObserve {
    private ArrayList<Observateur> obs = new ArrayList<>();

    /**
     * ajoute les observateur
     * @param obs
     */
    public void ajouterObservateur(Observateur obs){
        this.obs.add(obs);
    }

    /**
     * notifie les observateurs
     */
    public void notifierObservateurs(){
        for(Observateur obs:this.obs) obs.reagir();
    }
}
