package twisk.simulation;

import twisk.monde.Etape;
/**
 * Classe Client
 * @author IKHRICHI SOUMAYA AL-AMERI RAWAN
 */
public class Client {
    private int numeroClient;
    private int rang;
    private Etape etape;

    /**
     * la constructeur du client
     * @param numero le numéro du client
     */
    public Client(int numero){
        this.numeroClient = numero;
        this.rang=0;
        this.etape=null;
    }

    /**
     * met à jour les attributs etape et rang d’un client
     * @param etape l'etape dans le monde
     * @param rang le rang d'un client
     */
    public void allerA(Etape etape, int rang){
            this.etape=etape;
            this.rang=rang;
    }

    public int getNumeroClient() {
        return numeroClient;
    }


   //Un getter pour tester la fonction allerA
    /**
     * @return un getter d'un etape
     */
    public Etape getEtape() {
        return etape;
    }

    // Un getter pour tester la fonction allerA
    /**
     * @return un getter d'un rang
     */
    public int getRang() {
        return rang;
    }

}
