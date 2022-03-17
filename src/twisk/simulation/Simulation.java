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
        kitc.construireLibrairie();
        System.load("/tmp/twisk/libTwisk.so");
    }
    public native int[] start_simulation(int nbEtapes,int nbGuichets,int nbClients,int[] tabJetonsServices);
    public native int[] ou_sont_les_clients(int nbEtapes,int nbClients);
    public native void nettoyage();
}
