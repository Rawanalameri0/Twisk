package twisk.vues;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
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
        TailleComposants taille=TailleComposants.getInstance();
        this.setPrefSize(taille.getLargeurActivite(),taille.getHauteurActivite());
        Entree_Sortie();

    }



    @Override
    public void reagir() {

    }
    public void Entree_Sortie(){
        if( this.etape.isSortie()){
            HBox h = new HBox();
            Circle c = new Circle(10);
            Image img= new Image(getClass().getResourceAsStream(("/images/sortie.png")),40,40,true,true);
            c.setFill(new ImagePattern(img));
            h.getChildren().addAll(c,labl);
            this.getChildren().addAll(h,hBox);

        }
        if(this.etape.isEntree() ){
            HBox h = new HBox();
            Circle c = new Circle(10);
            Image img= new Image(getClass().getResourceAsStream(("/images/entrer.png")),40,40,true,true);
            c.setFill(new ImagePattern(img));
            h.getChildren().addAll(c,labl);
            this.getChildren().addAll(h,hBox);
        }
        if(this.etape.isSelect()){
            this.setStyle("-fx-border-color: #1968b8; -fx-background-color: #cebbf4;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
            this.getChildren().addAll(labl,hBox);
        }
        else if(!etape.isEntree() && !etape.isSortie() && !etape.isSelect()){
            this.setStyle("-fx-border-color: #0059FF; -fx-background-color:white ;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
            this.getChildren().addAll(labl,hBox);
        }

    }

    @Override
    public HBox getHBox() {
        return hBox;
    }
}
