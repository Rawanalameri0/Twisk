package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;

public class Simulation {
    private KitC kitc;
    private int nbClients;

    public Simulation(){
        kitc = new KitC();
        kitc.creerEnvironement();
        nbClients=6;//la valeurs par défaut des clients
    }

    public void simuler(Monde monde){
        System.out.println(monde.toString());
        kitc.creerFichier(monde.toC());
        kitc.compiler();
        kitc.construireLibrairie();
        System.load("/tmp/twisk/libTwisk.so");
        boolean end=false;
        int nbClientEtape;
        int nbGuichets=monde.nbGuichets();
        int nbEtapes=monde.nbEtapes();
        int[] tabJetons=monde.getTabJetons();
        int[] tabClients=start_simulation(nbEtapes,nbGuichets,nbClients,tabJetons);
        System.out.printf("Les clients: ");
        for (int i=0;i<nbClients;i++){
            System.out.printf(" "+tabClients[i]+" ");
        }
        System.out.println("\n");
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
            System.out.println("\n***************************************************************");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nettoyage();
    }
    public native int[] start_simulation(int nbEtapes,int nbGuichets,int nbClients,int[] tabJetonsServices);
    public native int[] ou_sont_les_clients(int nbEtapes,int nbClients);
    public native void nettoyage();

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

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
            System.out.println("***************************************************************");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nettoyage();
    }
}
