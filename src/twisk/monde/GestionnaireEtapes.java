package twisk.monde;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe GestionnaireEtapes
 * @author IKHRICHI SOUMAYA AL-AMERI RAWAN
 */
public class GestionnaireEtapes implements Iterable<Etape>{
    private ArrayList<Etape> etapes;

    /**
     * Constructeur du GestionnaireEtape
     */
    public GestionnaireEtapes(){
        this.etapes=new ArrayList<Etape>();
    }

    /**
     * ajoute une collection d'étapes donnée à la liste
     * @param etapes collection d'Etapes
     */
    public void ajouter(Etape... etapes){
        for(Etape e:etapes){
            this.etapes.add(e);
        }
    }

    /**
     * @return le nombres d'étapes
     */
    public int nbEtapes(){
        return  this.etapes.size();
    }

    /**
     * @return l'iterateur de la liste
     */
    @Override
    public Iterator<Etape> iterator() {
        return this.etapes.iterator();
    }

    /**
     * @return la chaine de caractères qui décrit les étapes
     */
    public String toString(){
        return ""+this.etapes;
    }

    /**
     * @param index de l'étape souhaité
     * @return le nom de l'etape correspondant à l'index donné en paramètre
     */
    public String getNomEtape(int index){
        return this.etapes.get(index).getNom();
    }
}
