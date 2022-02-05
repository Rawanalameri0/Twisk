package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape>{
    private GestionnaireEtapes gestEtapes;
    private SasSortie sasSortie;
    private SasEntree sasEntree;

    public  Monde(){
        gestEtapes=new GestionnaireEtapes();
        sasEntree= new SasEntree();
        sasSortie = new SasSortie();
    }

    public void aCommeEntree(Etape... etapes){
        this.sasEntree.ajouterSuccesseur(etapes);
    }

    public void aCommeSortie(Etape... etapes){
        this.sasSortie.ajouterSuccesseur(etapes);
    }

    public int NbSorties(){
        return sasSortie.nbSuccesseurs();
    }

    public int NbEntrees(){
        return sasEntree.nbSuccesseurs();
    }

    public void ajouter(Etape... etapes){
        this.gestEtapes.ajouter(etapes);
    }

    public int nbEtapes(){
        return gestEtapes.nbEtapes();
    }

    public int nbGuichets(){
        int cpt=0;
        for (Etape e: this.gestEtapes){
            if (e.estUnGuichet()){
                cpt++;
            }
        }
        return cpt;
    }

    @Override
    public Iterator<Etape> iterator() {
        return this.gestEtapes.iterator();
    }

    @Override
    public String toString() {
        return "Entrée(s) : "+ sasEntree.toString() +"\n"
                +"Sortie(s) : " +sasSortie.toString() +"\n";
    }
}
