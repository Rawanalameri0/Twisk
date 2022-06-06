package twisk.vues;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import twisk.mondeIG.*;
import twisk.outils.TailleComposants;


public class VueActiviteIG extends VueEtapeIG implements Observateur{
    private Label label;
    private HBox hBox;
    /**
     * La constructeur VueActiviteIG
     * @param monde
     * @param etape
     */
    public VueActiviteIG(MondeIG monde, EtapeIG etape){
        super(monde,etape);
        this.labl=new Label(this.etape.getnomEtape()+": "+ this.etape.getDelai());
        this.labl.setStyle("-fx-font-family: Calibri ;-fx-font-size:14 ; -fx-font-weight: bold" );
        hBox = new HBox();
        this.setPadding(new Insets(2, 2, 2, 2));
        hBox.setStyle("-fx-border-color: #006DF0; -fx-background-color: white;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
        hBox.setPrefWidth(10);
        hBox.setPrefHeight(40);
        this.setPadding(new Insets(8,6,8,6));
        int x = this.etape.getPosX();
        int y = this.etape.getPosY();
        this.relocate(x,y);
        this.getChildren().addAll(labl,hBox);
        TailleComposants taille=TailleComposants.getInstance();
        this.setPrefSize(taille.getLargeurActivite(),taille.getHauteurActivite());

    }
    @Override
    public void setClient(VueClientIG vue){
        this.getChildren().clear();
        hBox.getChildren().addAll(vue);
        this.getChildren().addAll(labl,hBox);
        monde.notifierObservateurs();
    }



    @Override
    public void reagir() {

    }

    @Override
    public HBox getHBox() {
        return hBox;
    }
}
