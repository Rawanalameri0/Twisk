package twisk.outils;

public class FabriqueNumero {
    private int cptEtape;
    private  int cptSemaphore;
    private static FabriqueNumero instance=new FabriqueNumero();

    private FabriqueNumero(){

        cptEtape=-1;
        cptSemaphore=0;
    }

    public static FabriqueNumero getInstance() {
        return instance;
    }

    public int getNumeroEtape() {
        cptEtape++;
        return cptEtape;
    }

    public int getNumeroSemaphore() {
        cptSemaphore++;
        return cptSemaphore;
    }

    public void reset(){

        cptEtape=-1;
        cptSemaphore=0;
    }
}
