package twisk.mondeIG;

/**
 * Classe PointDeControleIG
 */
public class PointDeControleIG {
    private int x;
    private int y;
    private String identifiant;
    private EtapeIG etape;
    private boolean select;

    /**
     * La constructeur PointDeControleIG
     * @param identifiant
     * @param etape du point de contrôle
     * @param x coordonnée x
     * @param y coordonnée y
     */
    public PointDeControleIG(String identifiant,EtapeIG etape,int x,int y){
        this.identifiant=identifiant;
        this.etape=etape;
        this.x=x;
        this.y=y;
        this.select=false;
    }

    /**
     * @return getter position x
     */
    public int getX() {
        return x;
    }

    /**
     * @return getter position y
     */
    public int getY() {
        return y;
    }

    /**
     * @return getter etape
     */
    public EtapeIG getEtape() {
        return etape;
    }

}
