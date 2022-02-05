package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

public abstract class Etape implements Iterable<Etape>{
    protected String nom;
    protected GestionnaireSuccesseurs gestSucc;
    private FabriqueNumero fb= FabriqueNumero.getInstance();
    protected int numero= fb.getNumeroEtape();

    public Etape(String nom){
        this.nom=nom;
        this.gestSucc= new GestionnaireSuccesseurs();
    }

    public int getNumero() {
        return numero;
    }

    public void reset(){
        fb.reset();
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

    public abstract String toString();

}
