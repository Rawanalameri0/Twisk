package twisk.vues;

import javafx.scene.control.Label;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public class VueGuichetIG extends VueEtapeIG implements Observateur{
    /**
     * la constructeur VueEtapeIG
     *
     * @param monde
     * @param etape
     */
    public VueGuichetIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        this.labl=new Label(this.etape.getnomEtape()+":" +this.etape.getJetons());

    }
}
