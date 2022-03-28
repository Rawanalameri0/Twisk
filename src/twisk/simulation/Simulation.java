package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;
/**
 * Classe Simulation
 * @author IKHRICHI SOUMAYA AL-AMERI RAWAN
 */
public class Simulation {
    private KitC kitc;
    private int nbClients;

    /**
     * la constructeur de Simulation
     */
    public Simulation(){
        kitc = new KitC();
        kitc.creerEnvironement();
        nbClients=6;//la valeurs par défaut des clients
    }

    /**
     * une simulation des clients dans une monde définie
     * @param monde le monde utilisé pour l'execution
     */
    public void simuler(Monde monde){
        System.out.println(monde.toString());
        kitc.creerFichier(monde.toC());
        kitc.compiler();
        kitc.construireLibrairie();
        System.load("/tmp/twisk/libTwisk.so");
        FonctionMain(monde);
    }

    /**
     * Commencer la simulation
     * @param nbEtapes le nombre d'etapes
     * @param nbGuichets le nombre de guichet
     * @param nbClients le nombre de client
     * @param tabJetonsServices un tableau des jetons
     * @return un tableau d'entiers
     */
    public native int[] start_simulation(int nbEtapes,int nbGuichets,int nbClients,int[] tabJetonsServices);

    /**
     * Récupérer les clients
     * @param nbEtapes le nombre d'etapes
     * @param nbClients le nombre de client
     * @return un tableau d'entiers
     */
    public native int[] ou_sont_les_clients(int nbEtapes,int nbClients);

    /**
     * Nettoyage du code
     */
    public native void nettoyage();

    /**
     * Setter du nombre de client
     * @param nbClients le nombre de clients
     */
    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

    /**
     * Création des processus C depuis le code Java
     * @param monde le monde utilisé pour l'execution
     */
    public void FonctionMain(Monde monde){
        boolean end=false;
        int nbClientEtape=0;
        int nbGuichets=monde.nbGuichets();
        int nbEtapes=monde.nbEtapes();
        int[] tabJetons=monde.getTabJetons();
        int[] tabClients=start_simulation(nbEtapes,nbGuichets,nbClients,tabJetons);
        System.out.printf("Les clients: ");
        for (int i=0;i<nbClients;i++){
            System.out.printf(" "+tabClients[i]+" ");
        }
        System.out.println("\n \n");
        int[] EmplacementClient;
        while (!end){
            EmplacementClient=ou_sont_les_clients(nbEtapes,nbClients);
            for (int nbClient=0,etape=0;etape<nbEtapes;++nbClient,++etape){
                nbClientEtape=EmplacementClient[nbClient];
                System.out.printf("\nétape "+etape+" ("+monde.getNomDeEtape(etape)+") "+EmplacementClient[nbClient]+" clients :");
                for (int c=1;c<=nbClientEtape;c++){
                    System.out.printf(" "+EmplacementClient[nbClient+c]+" ");
                }
                nbClient+=nbClients;
                if (etape==1){
                    end= nbClientEtape==nbClients;
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("");
        nettoyage();
    }
}
