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
        this.nbjetons=3;
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
        return "   P(ids,"+this.NameSemaphore()+");\n" +
                "   transfert("+this.getNom()+this.getNumero()+","+this.gestSucc.getSuccesseur().getNom()+this.gestSucc.getSuccesseur().getNumero()+");\n    " +
                this.gestSucc.getSuccesseur().getDelai()+"\n"+"   V(ids,"+this.NameSemaphore()+");\n" +this.gestSucc.getSuccesseur().toC();
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
