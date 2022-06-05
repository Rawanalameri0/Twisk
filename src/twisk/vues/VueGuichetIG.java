package twisk.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

import java.util.ArrayList;

public class VueGuichetIG extends VueEtapeIG implements Observateur{
    private Label labl;
    /**
     * la constructeur VueEtapeIG
     * @param monde
     * @param etape
     */
    public VueGuichetIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
       this.labl =new Label(this.etape.getnomEtape()+":" +this.etape.getJetons()+" jetons");
        this.setPadding(new Insets(6, 6, 6, 2));
        HBox hBox=new HBox();
        ArrayList<HBox> hbox=new ArrayList<>();
        for(int i=0;i<8;i++){
            hbox.add(new HBox());
            hbox.get(i).setPadding(new Insets(10,10,10,10));
            hbox.get(i).setStyle("-fx-border-color: green;-fx-background-color: #D3D3D3;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
            hBox.getChildren().add(hbox.get(i));
        }
        this.setPadding(new Insets(12,6,8,6));
        int x = this.etape.getPosX();
        int y = this.etape.getPosY();
        this.relocate(x,y);
        TailleComposants taille=TailleComposants.getInstance();
        this.setPrefSize(taille.getLargeurGuichet(),taille.getHauteurGuichet());
        this.getChildren().addAll(labl,hBox);
    }
}
