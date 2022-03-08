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
        gestEtapes.ajouter(sasEntree);
        gestEtapes.ajouter(sasSortie);
    }

    public void aCommeEntree(Etape... etapes){
        this.sasEntree.ajouterSuccesseur(etapes);
    }

    public void aCommeSortie(Etape... etapes){
        for (Etape e:etapes){
            e.ajouterSuccesseur(sasSortie);
        }

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
        StringBuilder s=new StringBuilder();
        for (Etape e:gestEtapes){
            s.append(e.toString()+"\n");
        }
        return new String(s);
    }

    public String toC(){
        StringBuilder s=new StringBuilder();
        s.append("#include <stdlib.h>\n#include <stdio.h>\n#include <unistd.h>\n#include <stdbool.h>\n");
        for (Etape e:gestEtapes){
            s.append("#define "+e.nom+" "+e.getNumero()+"\n");
            if (e.estUnGuichet()){
                s.append("#define NbSemaGuichet"+e.getNumeroGuichet()+" "+e.getNumeroGuichet()+"\n");
            }
        }
        s.append("\nvoid simulation(int ids){");
        s.append(sasEntree.toC());
        s.append("\n}\n");
        return new String(s);
    }
}
