package twisk.outils;
/**
 * Classe FabriqueNumero
 * @author IKHRICHI SOUMAYA AL-AMERI RAWAN
 */


public class FabriqueNumero {
    private int cptEtape;
    private  int cptSemaphore;
    private  int cptLibTwisk;
    private static FabriqueNumero instance=new FabriqueNumero();

    /**
     * La constructeur FabriqueNumero
     */
    private FabriqueNumero(){
        cptEtape=-1;
        cptSemaphore=0;
        cptLibTwisk=0;
    }

    /**
     * @return L'instance de FabriqueNumero
     */
    public static FabriqueNumero getInstance() {
        return instance;
    }

    /**
     * @return le numéro de l'etape
     */
    public int getNumeroEtape() {
        cptEtape++;
        return cptEtape;
    }

    /**
     * @return le numéro de guichet
     */
    public int getNumeroSemaphore() {
        cptSemaphore++;
        return cptSemaphore;
    }

    /**
     * Reset les nombres d'etapes et semaphore
     */
    public void reset(){
        cptEtape=-1;
        cptSemaphore=0;
    }

    public int getCptLibTwisk() {
        cptLibTwisk++;
        return cptLibTwisk;
    }
}
