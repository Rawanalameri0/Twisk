package twisk.vues;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.TilePane;
import twisk.ecouteurs.*;
import twisk.exceptions.MondeException;
import twisk.mondeIG.MondeIG;
import twisk.outils.ThreadsManager;


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
        Image image1 = new Image(getClass().getResourceAsStream(("/images/plus1.png")));
        ImageView icone1 = new ImageView(image1);
        ajoutActivite.setGraphic(icone1);
        icone1.setFitHeight(40);
        icone1.setFitWidth(40);
        ajoutGuichet.setStyle("-fx-border-color: transparent ; -fx-background-color: transparent");
        Image image2 = new Image(getClass().getResourceAsStream(("/images/plus2.png")));
        ImageView icone2 = new ImageView(image2);
        ajoutGuichet.setGraphic(icone2);
        icone2.setFitHeight(40);
        icone2.setFitWidth(40);
        simuler.setStyle("-fx-border-color: transparent ; -fx-background-color: transparent");
        Image image3 = new Image(getClass().getResourceAsStream(("/images/start.png")));
        ImageView icone3 = new ImageView(image3);
        simuler.setGraphic(icone3);
        icone3.setFitHeight(40);
        icone3.setFitWidth(40);
        this.simuler.setTooltip(new Tooltip("Lancez la simulation"));
        this.ajoutActivite.setTooltip( new Tooltip("Ajoutez une activite"));
        this.ajoutGuichet.setTooltip(new Tooltip("Ajoutez un guichet"));
        ajoutActivite.setOnAction(new EcouteurAjoutActivite(this.monde));
        ajoutGuichet.setOnAction(new EcouteurAjoutGuichet(this.monde));
        simuler.setOnAction(new EcouteurLancerLaSimulation(this.monde));
        this.setHgap(2);
        this.setVgap(2);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(ajoutActivite,ajoutGuichet,simuler);
        this.monde.ajouterObservateur(this);
        reagir();
    }

    @Override
    public void reagir() {
        Runnable c = () ->{
            this.getChildren().clear();
            //if (monde.getSimulate() != null) {
                if (monde.isStart()) {
                    ajoutActivite.setDisable(true);
                    ajoutGuichet.setDisable(true);
                    simuler.setStyle("-fx-border-color: transparent ; -fx-background-color: transparent");
                    Image image3 = new Image(getClass().getResourceAsStream(("/images/pause.png")), 40, 40, true, true);
                    ImageView icone3 = new ImageView(image3);
                    simuler.setGraphic(icone3);
                    simuler.setOnAction(actionEvent -> {
                        ThreadsManager.getInstance().detruireTout();
                        monde.setStart(false);
                        monde.notifierObservateurs();
                    });
                }else {
                    ajoutGuichet.setDisable(false);
                    ajoutActivite.setDisable(false);
                    simuler.setStyle("-fx-border-color: transparent ; -fx-background-color: transparent");
                    Image image3 = new Image(getClass().getResourceAsStream(("/images/start.png")), 40, 40, true, true);
                    ImageView icone3 = new ImageView(image3);
                    simuler.setGraphic(icone3);
                    simuler.setOnAction(new EcouteurLancerLaSimulation(monde));
                }
            //}
            this.getChildren().addAll(ajoutActivite,ajoutGuichet,simuler);
        };
        if (Platform.isFxApplicationThread())
            c.run();
        else
            Platform.runLater(c);
    }
}
