package twisk.Ecouteurs;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;
/**
 * Classe EcouteurQuitter
 * @author AL-AMERI RAWAN
 */
public class EcouteurQuitter implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * La constructeur EcouteurQuitter qui ferme l’application
     * @param monde
     */
    public EcouteurQuitter(MondeIG monde){
        this.monde=monde;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Platform.exit();

    }
}
