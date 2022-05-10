package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.ecouteurs.EcouteurVuePointdeControl;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

/**
 * Classe VuePontDeControleIG
 */
public class VuePointDeControleIG extends Circle implements Observateur {
    private MondeIG monde;
    private EtapeIG etape;
    private PointDeControleIG ptdecontrole;

    /**
     * la constructeur de VuePontDeControleIG
     * @param monde
     * @param etape
     * @param ptdecontrole
     */
    public VuePointDeControleIG(MondeIG monde, EtapeIG etape, PointDeControleIG ptdecontrole) {
        this.monde=monde;
        this.etape=etape;
        this.ptdecontrole=ptdecontrole;
        this.setCenterX(ptdecontrole.getX());
        this.setCenterY(ptdecontrole.getY());
        this.setFill(Color.RED);
        this.setRadius(4);
        this.setOnMouseClicked(new EcouteurVuePointdeControl(this.monde,this));


    }

    /**
     * @return getter de point de controle
     */
    public PointDeControleIG getPtdecontrole() {
        return ptdecontrole;
    }

    @Override
    public void reagir() {

    }
}
