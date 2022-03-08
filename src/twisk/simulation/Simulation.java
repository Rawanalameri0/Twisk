package twisk.simulation;

import twisk.monde.Etape;
import twisk.monde.Monde;
import twisk.outils.KitC;

public class Simulation {

    public Simulation(){
        KitC kitc = new KitC();
        kitc.creerEnvironement();
    }

    public void simuler(Monde monde){
        System.out.println(monde.toC());
    }
}
