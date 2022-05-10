package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

public class VueGuichetIG extends VueEtapeIG implements Observateur{
    /**
     * la constructeur VueEtapeIG
     *
     * @param monde
     * @param etape
     */
    public VueGuichetIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        this.labl=new Label(this.etape.getnomEtape()+":" +this.etape.getJetons()+" jetons");
        for(int i=1;i<11;i++) {
            HBox hBox=new HBox();
            hBox.setPrefSize(4,40);
            hBox.setStyle("-fx-border-color: green; -fx-background-color: transparent;");
            this.getChildren().add(hBox);
        }
        int x = this.etape.getPosX();
        int y = this.etape.getPosY();
        this.relocate(x,y);
        this.getChildren().addAll(labl);
        TailleComposants taille=TailleComposants.getInstance();
        this.setPrefSize(taille.getLargeurGuichet(),taille.getHauteurGuichet());
    }
}
