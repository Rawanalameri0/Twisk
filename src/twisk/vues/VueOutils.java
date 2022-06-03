package twisk.vues;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import twisk.ecouteurs.EcouteurAjoutActivite;
import twisk.ecouteurs.EcouteurAjoutGuichet;
import twisk.ecouteurs.EcouteurLancerLaSimulation;
import twisk.mondeIG.MondeIG;


/**
 * Classe VueOutils
 */
public class VueOutils extends TilePane implements Observateur {
    private MondeIG monde;
    private Button ajoutActivite;
    private Button ajoutGuichet;
    private Button simuler;

    /**
     * La constructeur VueOutils
     * @param monde
     */
    public VueOutils(MondeIG monde){
        this.monde=monde;
        this.ajoutActivite = new Button(" ");
        this.ajoutGuichet = new Button(" ");
        this.simuler = new Button("");
        ajoutActivite.setStyle("-fx-border-color: transparent ; -fx-background-color: transparent");
        Image image1 = new Image(getClass().getResourceAsStream(("/images/plus.png")));
        ImageView icone1 = new ImageView(image1);
        ajoutActivite.setGraphic(icone1);
        icone1.setFitHeight(32);
        icone1.setFitWidth(32);
        ajoutGuichet.setStyle("-fx-border-color: transparent ; -fx-background-color: transparent");
        Image image2 = new Image(getClass().getResourceAsStream(("/images/plus2.png")));
        ImageView icone2 = new ImageView(image2);
        ajoutGuichet.setGraphic(icone2);
        icone2.setFitHeight(32);
        icone2.setFitWidth(32);
        simuler.setStyle("-fx-border-color: transparent ; -fx-background-color: transparent");
        Image image3 = new Image(getClass().getResourceAsStream(("/images/start.png")));
        ImageView icone3 = new ImageView(image3);
        simuler.setGraphic(icone3);
        icone3.setFitHeight(32);
        icone3.setFitWidth(32);
        this.ajoutActivite.setTooltip( new Tooltip("Ajoutez une activite"));
        this.ajoutGuichet.setTooltip(new Tooltip("Ajoutez un guichet"));
        this.simuler.setTooltip(new Tooltip("Lancez la simulation"));
        ajoutActivite.setOnAction(new EcouteurAjoutActivite(this.monde));
        ajoutGuichet.setOnAction(new EcouteurAjoutGuichet(this.monde));
        simuler.setOnAction(new EcouteurLancerLaSimulation(this.monde));
        this.setHgap(10);
        this.setVgap(10);
        this.getChildren().addAll(ajoutActivite,ajoutGuichet,simuler);
        this.monde.ajouterObservateur(this);
    }

    @Override
    public void reagir() {

    }
}
