package twisk.monde;

import twisk.outils.FabriqueNumero;

public class Guichet extends Etape{
    private int nbjetons;
    private FabriqueNumero fb= FabriqueNumero.getInstance();
    private int numero= fb.getNumeroSemaphore();

    public Guichet(String nom){
        super(nom);
        this.nbjetons=1;
    }

    public Guichet(String nom,int nb){
        super(nom);
        this.nbjetons=nb;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }

    @Override
    public String toString() {
        return "Guichet "+this.getNumero()+":"+this.nom+": "+nbjetons+" jetons";
    }
}
