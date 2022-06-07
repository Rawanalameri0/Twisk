package twisk.vues;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private HBox hBox,hBox1;
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
        hBox1= new HBox();
        this.setPadding(new Insets(2, 2, 2, 2));
        hBox.setStyle("-fx-border-color: #006DF0; -fx-background-color: white;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
        hBox.setPrefWidth(10);
        hBox.setPrefHeight(40);
        hBox1.getChildren().addAll(labl);
        this.setPadding(new Insets(8,6,8,6));
        int x = this.etape.getPosX();
        int y = this.etape.getPosY();
        this.relocate(x,y);
        TailleComposants taille=TailleComposants.getInstance();
        this.setPrefSize(taille.getLargeurActivite(),taille.getHauteurActivite());
        if(this.etape.isSelect()){
            this.setStyle("-fx-border-color: #1968b8; -fx-background-color: #cebbf4;-fx-border-radius: 5; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
        }
        else if(!etape.isEntree() && !etape.isSortie() && !etape.isSelect()){
            this.setStyle("-fx-border-color: #0059FF; -fx-background-color:transparent ;-fx-border-radius: 5; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
        }
        hBox1.setAlignment(Pos.CENTER);
        Entree_Sortie();
        this.getChildren().addAll(hBox1,hBox);
    }



    @Override
    public void reagir() {

    }
    public void Entree_Sortie(){
        if( this.etape.isSortie()){
            Circle c = new Circle(10);
            Image img= new Image(getClass().getResourceAsStream(("/images/sortie.png")),20,20,true,true);
            c.setFill(new ImagePattern(img));
            hBox1.getChildren().clear();
            hBox1.getChildren().addAll(c,labl);
        }
        if(this.etape.isEntree() ){
            Circle c = new Circle(10);
            Image img= new Image(getClass().getResourceAsStream(("/images/entrer.png")),20,20,true,true);
            c.setFill(new ImagePattern(img));
            hBox1.getChildren().clear();
            hBox1.getChildren().addAll(c,labl);
        }
        if (this.etape.isEntree() && this.etape.isSortie()){
            Circle c = new Circle(7);
            Circle c1 = new Circle(7);
            Image img= new Image(getClass().getResourceAsStream(("/images/sortie.png")),40,40,true,true);
            Image img1= new Image(getClass().getResourceAsStream(("/images/entrer.png")),40,40,true,true);
            c.setFill(new ImagePattern(img));
            c1.setFill(new ImagePattern(img1));
            hBox1.getChildren().clear();
            hBox1.getChildren().addAll(c,c1,labl);
        }
    }

    @Override
    public HBox getHBox() {
        return hBox;
    }

    @Override
    public void setClient(VueClientIG vue){
        this.getChildren().clear();
        hBox.getChildren().add(vue);
        this.getChildren().addAll(hBox1,hBox);
        monde.notifierObservateurs();
    }
}
