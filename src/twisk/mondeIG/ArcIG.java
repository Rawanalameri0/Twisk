package twisk.mondeIG;

/**
 * Classe ArcIG
 */
public class ArcIG {
    private PointDeControleIG pt1;
    private PointDeControleIG pt2;
    private boolean select;

    /**
     *La constructeur de ArcIG
     * @param pt1 premier point de controle
     * @param pt2 deuxieme point de controle
     */
    public ArcIG(PointDeControleIG pt1, PointDeControleIG pt2) {
        this.pt1 = pt1;
        this.pt2 = pt2;
        this.select=false;
    }

    /**
     * @return le premier un point de controle
     */
    public PointDeControleIG getpt1() {
        return pt1;
    }

    /**
     *
     * @return le deuxieme point de controle
     */
    public PointDeControleIG getpt2() {
        return pt2;
    }

    /**
     * @return getter de select
     */
    public boolean isSelect() {
        return select;
    }

    /**
     *
     * @param select setter de select
     */
    public void setSelect(boolean select) {
        this.select = select;
    }
}
