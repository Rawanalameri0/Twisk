package twisk.vues;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import twisk.Ecouteurs.EcouteurSelection;
import twisk.Ecouteurs.EcouteurSetOnDragDetected;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

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
        this.labl=new Label(this.etape.getnomEtape()+":" + " "+ this.etape.getDelai());
        this.setStyle("-fx-border-color: blue; -fx-background-color: white;-fx-alignment: center;-fx-background-insets: 0 0 -1 0, 0, 1, 2;-fx-border-radius: 5; -fx-background-radius: 3px, 3px, 2px, 1px");
        this.Entree_Sortie();
        this.getChildren().add(labl);
        this.setId(etape.getIdentifiant());
        this.setOnMouseClicked(new EcouteurSelection(monde,this));
        this.setOnDragDetected(new EcouteurSetOnDragDetected(monde,this));
        if(this.etape.isSelect() && !this.etape.isEntree()&& !this.etape.isSortie()){
            this.setStyle("-fx-background-color: #1968b8");
        }


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

   /* public void setEtape(EtapeIG etape) {
        this.etape = etape;
    }*/

    /**
     * verifier si cest une entree ou sortie et change le couleur
     */
    public void Entree_Sortie(){
        if(!this.etape.isEntree() && this.etape.isSortie()){
        this.setStyle("-fx-border-color: #0059FF; -fx-background-color: #cebbf4;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
     }
        if(this.etape.isEntree() && !this.etape.isSortie()){
        this.setStyle("-fx-border-color: #0059FF; -fx-background-color: #70638a;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
        }
   }
}

