package twisk.mondeIG;

public class GuichetIG extends EtapeIG{
    private int nbJetons;
    /**
     * La constructeur EtapeIG
     *
     * @param nom nom de l'étape
     * @param idf identifiant de l'étape
     * @param larg largeur de l'étaoe
     * @param haut hauteur de l'étape
     */
    public GuichetIG(String nom, String idf, int larg, int haut) {
        super(nom, idf, larg, haut);
        this.nbJetons= 3;
    }

    /**
     * modifie le nombre de jetons
     * @param nbJetons
     */
    @Override
    public void setNbJetons(int nbJetons){
        this.nbJetons=nbJetons;
    }

    @Override
    public int getJetons(){
        return nbJetons;
    }
    @Override
    public boolean estUnGuichet(){
        return true;
    }
}
