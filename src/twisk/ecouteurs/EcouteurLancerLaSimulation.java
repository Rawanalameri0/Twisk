package twisk.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import twisk.exceptions.MondeException;
import twisk.mondeIG.MondeIG;

public class EcouteurLancerLaSimulation implements EventHandler<ActionEvent> {
    private MondeIG monde;

    public EcouteurLancerLaSimulation(MondeIG monde){
        this.monde=monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            monde.simuler();
        } catch (MondeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Erreur pendant creation d'arc");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(10));
            pauseTransition.setOnFinished(action -> alert.close());
            pauseTransition.play();
        }
    }
}
