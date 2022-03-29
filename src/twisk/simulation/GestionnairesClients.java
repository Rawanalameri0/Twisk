package twisk.simulation;

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
     * indique le nombre de clients à gérer
     * @param nbClients
     */
    public void setNbClients(int nbClients){
        clients=new ArrayList<>(nbClients);
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

    @Override
    public Iterator<Client> iterator() {
        return clients.iterator();
    }
}
