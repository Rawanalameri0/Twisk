package twisk.monde;

import twisk.outils.FabriqueNumero;

public class Guichet extends Etape{
    private int nbjetons;
    private int num= FabriqueNumero.getInstance().getNumeroSemaphore();

    public Guichet(String nom){
        super(nom);
        this.nbjetons=1;
    }

    public Guichet(String nom,int nb){
        super(nom);
        this.nbjetons=nb;
    }

    @Override
    public int getNumeroGuichet() {
        return num;
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }

    public String toC(){
        return "\ndelai(4,2);\nP(ids,"+this.NameSemaphore()+");\n" +
                "transfert("+this.nom+","+this.gestSucc.getNomSuccesseur()+");\n" +
                "V(ids,"+this.NameSemaphore()+");\n" +this.gestSucc.getSuccesseur().toC();
    }

    public String NameSemaphore(){
        return "NbSemaGuichet"+num;
    }

    @Override
    public int getNbjetons() {
        return nbjetons;
    }
}
