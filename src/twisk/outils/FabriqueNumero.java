package twisk.outils;

public class FabriqueNumero {
    private int cptEtape;
    private static FabriqueNumero instance=new FabriqueNumero();

    private FabriqueNumero(){
        cptEtape=-1;
    }

    public static FabriqueNumero getInstance() {
        return instance;
    }

    public int getNumeroEtape() {
        cptEtape++;
        return cptEtape;
    }

    public void reset(){
        cptEtape=-1;
    }
}
