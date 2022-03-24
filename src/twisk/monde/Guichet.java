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
        return "\n   delai(4,2);\n   P(ids,"+this.NameSemaphore()+");\n" +
                "   transfert(etape"+this.getNumero()+",etape"+this.gestSucc.getSuccesseur().getNumero()+");\n" +
                "   V(ids,"+this.NameSemaphore()+");" +this.gestSucc.getSuccesseur().toC();
    }

    public String NameSemaphore(){
        return "NbSemaGuichet"+num;
    }

    @Override
    public int getNbjetons() {
        return nbjetons;
    }
}
