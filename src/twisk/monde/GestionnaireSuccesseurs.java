package twisk.monde;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireSuccesseurs implements Iterable<Etape> {
    private ArrayList<Etape> etapes;

    public GestionnaireSuccesseurs(){
        this.etapes=new ArrayList<Etape>();
    }

    public void ajouter(Etape... etapes){
        for(Etape e: etapes){
            this.etapes.add(e);
        }
    }

    public int nbEtapes(){
        return this.etapes.size();
    }

    @Override
    public Iterator<Etape> iterator() {
        return this.etapes.iterator();
    }

    public String toString(){
        return "Successeurs: "+this.etapes;
    }

}