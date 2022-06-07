package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;
import twisk.outils.ThreadsManager;

public class EcouteurArreterSimulation implements EventHandler<ActionEvent> {
    private MondeIG monde;

    public EcouteurArreterSimulation(MondeIG monde){
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ThreadsManager.getInstance().detruireTout();
        monde.setStart(false);
        monde.notifierObservateurs();
    }
}
