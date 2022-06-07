package twisk.vues;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import twisk.ecouteurs.*;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

import java.util.ArrayList;

/**
 * Classe abstract VueEtapeIG
 */
public abstract class VueEtapeIG extends VBox implements Observateur {
    protected MondeIG monde;
    protected EtapeIG etape;
    protected Label labl;
    /**
     * la constructeur VueEtapeIG
     * @param monde
     * @param etape
     */
    public VueEtapeIG(MondeIG monde,EtapeIG etape){
        this.monde=monde;
        this.etape=etape;
        this.labl=new Label(this.etape.getnomEtape()+":" );
        this.setStyle("-fx-border-color: blue; -fx-background-color: transparent;-fx-alignment: center;-fx-background-insets: 0 0 -1 0, 0, 1, 2;-fx-border-radius: 5; -fx-background-radius: 3px, 3px, 2px, 1px");
        this.setId(etape.getIdentifiant());
        this.setOnMouseClicked(new EcouteurSelection(monde,this));
        this.setOnDragDetected(new EcouteurSetOnDragDetected(monde,this));

    }


    @Override
    public void reagir() {

    }

    /**
     * @return getter etape
     */
    public EtapeIG getEtape() {
        return etape;
    }



   public HBox getHBox(){
        return null;
   }

   public ArrayList<HBox> getHboxes(){
        return null;
   }

   public void setClient(VueClientIG vue){

   }

}

