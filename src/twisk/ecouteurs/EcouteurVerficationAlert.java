package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.MondeIG;

public class EcouteurVerficationAlert implements EventHandler<MouseEvent> {
    private MondeIG monde;

    public EcouteurVerficationAlert(MondeIG monde){
        this.monde=monde;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}