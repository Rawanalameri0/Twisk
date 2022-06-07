package twisk.simulation;

import twisk.monde.Etape;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionnairesClients implements Iterable<Client>{
    private ArrayList<Client> clients;

    /**
     * Constructeur sans paramètre
     */
    public GestionnairesClients(){
        clients=new ArrayList<>();
    }

    /**
     * Constructeur
     * @param nbClients le nombre de clients à gérer
     */
    public GestionnairesClients(int nbClients){
        clients=new ArrayList<>(nbClients);
    }

    /**
     *  instancie les clients identifiés par leur numéro de processus (numéro de client)
     * @param tabClients collection des identifiants des clients
     */
    public void setClients(int... tabClients) {
       for (int Numcl:tabClients)
           clients.add(new Client(Numcl));
    }

    /**
     * fait le ménage dans les clients, pour traiter une nouvelle simulation
     */
    public void nettoyer(){
        clients.clear();
    }

    /**
     * @return le nombre de clients à gèrer
     */
    public int getNbClients(){
        return clients.size();
    }

    /**
     * met à jour les attributs etape et rang du client
     * @param numeroClient le numéro du client
     * @param etape etape où se trouve le client
     * @param rang le rang du client
     */
    public void allerA(int numeroClient,Etape etape,int rang){
        for (Client c:clients){
            if (c.getNumeroClient()==numeroClient)
                c.allerA(etape,rang);
        }
    }

    /**
     * @return l'iterator des clients
     */
    @Override
    public Iterator<Client> iterator() {
        return clients.iterator();
    }

    /**
     * @return la liste des clients
     */
    public ArrayList<Client> getClients() {
        return clients;
    }

}
