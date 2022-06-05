package twisk.mondeIG;

/**
 * Classe ActiviteIG
 */
public class ActiviteIG extends EtapeIG{
    private int ecart,temps;
    /**
     * ActiviteIG qui décrit une activité
     * @param nom
     * @param idf
     * @param larg
     * @param haut
     */
   public ActiviteIG(String nom,String idf,int larg,int haut) {
        super(nom,idf,larg,haut);
        ecart=2;
        temps=4;
   }

   @Override
   public  String getDelai(){
       return ""+temps+ " ± "+ecart+" temps";
   }

   @Override
   public void setDelai(int delai) {
        this.temps=delai;
    }

    @Override
    public void setEcart(int ecart){
       this.ecart=ecart;
    }

    @Override
    public boolean isActivite(){
       return true;
    }

    @Override
    public boolean isActiviteRestreinte(){
       return activiteRestreinte;
    }

    @Override
    public int getEcart() {
        return ecart;
    }

    @Override
    public int getTemps() {
        return temps;
    }
}
