package twisk.Ecouteurs;

import twisk.exceptions.TwiskException;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import twisk.mondeIG.MondeIG;
import twisk.vues.VuePointDeControleIG;


public class EcouteurVuePointdeControl implements EventHandler<MouseEvent> {
    private MondeIG monde;
    private VuePointDeControleIG pt;




    public EcouteurVuePointdeControl(MondeIG monde, VuePointDeControleIG pt) {
        this.monde=monde;
        this.pt=pt;
    }


    @Override
    public void handle(MouseEvent me) {
        try {
            monde.creerarc(pt.getPtdecontrole());
        } catch (TwiskException twiskException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(twiskException.getMessage());
            alert.setHeaderText("Erreur pendant creation d'arc");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(10));
            pauseTransition.setOnFinished(actionEvent -> alert.close());
            pauseTransition.play();
        }
    }

}