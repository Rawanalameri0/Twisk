package twisk.vues;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import twisk.ecouteurs.EcouteurAjout;
import twisk.mondeIG.MondeIG;


/**
 * Classe VueOutils
 */
public class VueOutils extends TilePane implements Observateur {
    private MondeIG monde;
    private Button ajout;

    /**
     * La constructeur VueOutils
     * @param monde
     */
    public VueOutils(MondeIG monde){
        this.monde=monde;
        this.ajout = new Button(" ");
        ajout.setStyle("-fx-border-color: transparent ; -fx-background-color: transparent");
        Image image = new Image(getClass().getResourceAsStream(("/images/plus.png")));
        ImageView icone = new ImageView(image);
        ajout.setGraphic(icone);
        icone.setFitHeight(32);
        icone.setFitWidth(32);
        this.ajout.setTooltip( new Tooltip("Ajoutez une activite"));
        ajout.setOnAction(new EcouteurAjout(monde));
        this.getChildren().add(ajout);
        this.monde.ajouterObservateur(this);
    }

    @Override
    public void reagir() {

    }
}
