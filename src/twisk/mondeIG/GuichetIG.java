package twisk.mondeIG;

public class GuichetIG extends EtapeIG{
    private int nbJetons;
    /**
     * La constructeur EtapeIG
     *
     * @param nom
     * @param idf
     * @param larg
     * @param haut
     */
    public GuichetIG(String nom, String idf, int larg, int haut) {
        super(nom, idf, larg, haut);
        nbJetons=3;
    }

    @Override
    public void setNbJetons(int nbJetons){
        this.nbJetons=nbJetons;
    }

    @Override
    public int getJetons(){
        return nbJetons;
    }

}
