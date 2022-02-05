package twisk.monde;

public class Guichet extends Etape{
    private int nbjetons;

    public Guichet(String nom){
        super(nom);
        this.nbjetons=1;
    }

    public Guichet(String nom,int nb){
        super(nom);
        this.nbjetons=nb;
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }

    @Override
    public String toString() {
        return this.nom+": "+nbjetons+" jetons";
    }
}
