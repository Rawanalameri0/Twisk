package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;

public class Simulation {
    private KitC kitc;

    public Simulation(){
        kitc = new KitC();
        kitc.creerEnvironement();
    }

    public void simuler(Monde monde){
        System.out.println(monde.toC());
        kitc.creerFichier(monde.toC());
        kitc.compiler();
    }
}
