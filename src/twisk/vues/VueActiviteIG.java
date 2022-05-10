package twisk.vues;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;


public class VueActiviteIG extends VueEtapeIG implements Observateur{
private Label label;

    /**
     * La constructeur VueActiviteIG
     * @param monde
     * @param etape
     */
    public VueActiviteIG(MondeIG monde, EtapeIG etape){
        super(monde,etape);
        Label label = new Label("");
        HBox hBox = new HBox();
        this.setPadding(new Insets(2, 2, 2, 2));
        hBox.setStyle("-fx-border-color: #0059FF; -fx-background-color: #d3d3d3;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
        hBox.setPrefWidth(10);
        hBox.setPrefHeight(40);
        this.setPadding(new Insets(8,6,8,6));
        int x = this.etape.getPosX();
        int y = this.etape.getPosY();
        this.relocate(x,y);
        this.getChildren().add(hBox);
        TailleComposants tailleComposants=TailleComposants.getInstance();
        this.setPrefSize(tailleComposants.getInstance().getLargeur(),tailleComposants.getInstance().getHauteur());

    }



    @Override
    public void reagir() {

    }
}
