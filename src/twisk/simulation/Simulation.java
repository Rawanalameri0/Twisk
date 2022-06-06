package twisk.simulation;

import javafx.concurrent.Task;
import twisk.monde.Monde;
import twisk.mondeIG.SujetObserve;
import twisk.outils.KitC;
/**
 * Classe Simulation
 * @author IKHRICHI SOUMAYA AL-AMERI RAWAN
 */
public class Simulation extends SujetObserve {
    private KitC kitc;
    private int nbClients;
    private boolean start;
    private GestionnairesClients gestClients;

    /**
     * la constructeur de Simulation
     */
    public Simulation() {
        kitc = new KitC();
        kitc.creerEnvironement();
        gestClients = new GestionnairesClients(nbClients);
        nbClients =5;
        start = false ;
    }

    /**
     * une simulation des clients dans une monde définie
     *
     * @param monde le monde utilisé pour l'execution
     */
    public void simuler(Monde monde) {
        System.out.println(monde.toString());
        kitc.creerFichier(monde.toC());
        kitc.compiler();
        kitc.construireLibrairie();
        System.load("/tmp/twisk/libTwisk"+kitc.getCompteur()+".so");
        setStart(true);
        notifierObservateurs();
        int nbClientEtape;
        int nbGuichets = monde.nbGuichets();
        int nbEtapes = monde.nbEtapes();
        int[] tabJetons = monde.getTabJetons();
        int[] tabClients = start_simulation(nbEtapes, nbGuichets, nbClients, tabJetons);
        int[] tClients = new int[getNbClients()];
        notifierObservateurs();
        System.out.printf("Les clients: ");
        for (int i = 0; i < nbClients; i++) {
            System.out.printf(" " + tabClients[i] + " ");
            tClients[i] = tabClients[i];
        }
        gestClients.setClients(tClients);
        System.out.println("\n \n");
        int[] EmplacementClient;
        while (start) {
            EmplacementClient = ou_sont_les_clients(nbEtapes, nbClients);
            notifierObservateurs();
            for (int nbClient = 0, etape = 0; etape < nbEtapes; ++nbClient, ++etape) {
                nbClientEtape = EmplacementClient[nbClient];
                System.out.printf("\nétape " + etape + " (" + monde.getNomDeEtape(etape) + ") " + nbClientEtape + " clients :");
                for (int c = 1; c <= nbClientEtape; c++) {
                    System.out.printf(" " + EmplacementClient[nbClient + c] + " ");
                    gestClients.allerA(EmplacementClient[nbClient + c], monde.getEtape(etape), c);
                    notifierObservateurs();
                }
                nbClient += nbClients;
                if (etape == 1) {
                    if (nbClientEtape == nbClients){
                        start =false;
                        notifierObservateurs();
                    }
                }
            }
            System.out.println("");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setStart(false);
        notifierObservateurs();
        gestClients.nettoyer();
        System.out.println("");
        monde.reset();
        nettoyage();
    }

    /**
     * Commence la simulation
     * @param nbEtapes le nombre d'etapes
     * @param nbGuichets le nombre de guichet
     * @param nbClients le nombre de client
     * @param tabJetonsServices un tableau des jetons
     * @return un tableau d'entiers
     */
    public native int[] start_simulation(int nbEtapes, int nbGuichets, int nbClients, int[] tabJetonsServices);

    /**
     * Récupérer les clients
     *
     * @param nbEtapes  le nombre d'etapes
     * @param nbClients le nombre de client
     * @return un tableau d'entiers
     */
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);

    /**
     * Nettoyage du code
     */
    public native void nettoyage();

    /**
     * Setter du nombre de client
     *
     * @param nbClients le nombre de clients
     */
    public void setNbClients(int nbClients) {
        if (nbClients>= 50)
            this.nbClients= 30;
        else
            this.nbClients = nbClients;
    }

    public GestionnairesClients getGestClients() {
        return gestClients;
    }

    public int getNbClients() {
        return nbClients;
    }


    public boolean estDebStimulation() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

}
