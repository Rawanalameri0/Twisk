package twisk.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import twisk.mondeIG.*;
import twisk.outils.TailleComposants;
import java.util.ArrayList;

public class VueGuichetIG extends VueEtapeIG implements Observateur{
    private Label labl;
    private ArrayList<HBox> hbox;
    private HBox hBox1;
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
        hBox1=new HBox();
        hBox1.getChildren().add(labl);
        hBox1.setAlignment(Pos.CENTER);
        hbox=new ArrayList<>();
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
        if(this.etape.isEntree() ){
            Circle c = new Circle(10);
            Image img= new Image(getClass().getResourceAsStream(("/images/entrer.png")),40,40,true,true);
            c.setFill(new ImagePattern(img));
            hBox1.getChildren().clear();
            hBox1.getChildren().addAll(c,labl);
        }
        if( this.etape.isSortie()){
            Circle c = new Circle(10);
            Image img= new Image(getClass().getResourceAsStream(("/images/sortie.png")),40,40,true,true);
            c.setFill(new ImagePattern(img));
            hBox1.getChildren().clear();
            hBox1.getChildren().addAll(c,labl);
        }
        if(this.etape.isSelect()){
            this.setStyle("-fx-border-color: #1968b8; -fx-background-color: #cebbf4;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-border-radius: 5; -fx-background-radius: 3px, 3px, 2px, 1px;");
        }
        else if(!etape.isEntree() && !etape.isSortie() && !etape.isSelect()){
            this.setStyle("-fx-border-color: #0059FF; -fx-background-color:white ;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-border-radius: 5; -fx-background-radius: 3px, 3px, 2px, 1px;");
        }
        this.getChildren().addAll(hBox1,hBox);
    }

    public ArrayList<HBox> getHBoxes() {
        return hbox;
    }

}
