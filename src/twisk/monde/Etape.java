package twisk.monde;

import twisk.outils.FabriqueNumero;
import java.util.Iterator;

public abstract class Etape implements Iterable<Etape>{
    protected String nom;
    protected GestionnaireSuccesseurs gestSucc;
    protected int numero= FabriqueNumero.getInstance().getNumeroEtape();

    public Etape(String nom){
        this.nom=nom;
        this.gestSucc= new GestionnaireSuccesseurs();
    }

    public int getNumero() {
        return numero;
    }

    public void ajouterSuccesseur(Etape... e){
        this.gestSucc.ajouter(e);
    }

    public int nbSuccesseurs(){
        return this.gestSucc.nbEtapes();
    }

    public boolean estUneActivite(){
        return  false;
    }

    public boolean estUnGuichet(){
        return  false;
    }

    @Override
    public Iterator<Etape> iterator(){
        return gestSucc.iterator();
    }

    public String toString(){

        return this.nom +" :"+this.gestSucc.toString();
    }

}
