package twisk.monde;

import twisk.outils.FabriqueNumero;
/**
 * Classe Guichet
 * @author IKHRICHI SOUMAYA AL-AMERI RAWAN
 */
public class Guichet extends Etape{
    private int nbjetons;
    private int num= FabriqueNumero.getInstance().getNumeroSemaphore();

    /**
     * la constructeur du Guichet
     * @param nom le nom du guichet
     */
    public Guichet(String nom){
        super(nom);
        this.nbjetons=1;
    }

    /**
     *
     * @param nom le nom du guichet
     * @param nb le nombre de jeton
     */
    public Guichet(String nom,int nb){
        super(nom);
        this.nbjetons=nb;
    }

    /**
     * @return un getter de le numéro du guichet
     */
    @Override
    public int getNumeroGuichet() {
        return num;
    }

    /**
     * @return true si c'est bien un guichet
     */
    @Override
    public boolean estUnGuichet() {
        return true;
    }

    /**
     * @return le code C en chaine de caractere
     */
    public String toC(){
        return "\n   delai(4,2);\n   P(ids,"+this.NameSemaphore()+");\n" +
                "   transfert("+this.getNom()+","+this.gestSucc.getSuccesseur().getNom()+");\n" +
                "   V(ids,"+this.NameSemaphore()+");" +this.gestSucc.getSuccesseur().toC();
    }

    /**
     * @return une chaine de caractères de semaphore du guichet
     */
    public String NameSemaphore(){
        return "NbSemaGuichet"+num;
    }

    /**
     * @return un getter du nombre des jetons dans le guichet
     */
    @Override
    public int getNbjetons() {
        return nbjetons;
    }
}
