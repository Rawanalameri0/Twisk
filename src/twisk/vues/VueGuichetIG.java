package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

import java.util.ArrayList;

public class VueGuichetIG extends VueEtapeIG implements Observateur{
    private ArrayList<VBox> boxes;
    /**
     * la constructeur VueEtapeIG
     * @param monde
     * @param etape
     */
    public VueGuichetIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        boxes=new ArrayList<>(10);
        this.labl=new Label(this.etape.getnomEtape()+":" +this.etape.getJetons()+" jetons");
        int x = this.etape.getPosX();
        int y = this.etape.getPosY();
        TailleComposants taille=TailleComposants.getInstance();
        int haut=taille.getHauteurGuichet();
        int larg=taille.getLargeurGuichet();
        double px=x+(haut/2)+2;
        double py= y+haut/2;
        for(int i=0;i<10;i++) {
            VBox hBox = new VBox();
            hBox.setPrefSize(larg/10, haut/10);
            //hBox.setLayoutX(px);
            //hBox.setLayoutY(py);
            hBox.setStyle("-fx-border-color: green; -fx-background-color: transparent; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
            boxes.add(hBox);
            px += 20;
        }
        this.relocate(x,y);
        boxes.forEach(hBox -> this.getChildren().add(hBox));
        this.getChildren().addAll(labl);
        this.setPrefSize(larg,haut);
    }
}
