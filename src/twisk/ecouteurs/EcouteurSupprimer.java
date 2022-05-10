package twisk.ecouteurs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

/**
 * Class EcouteurSupprimer
 */
public class EcouteurSupprimer implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * supprime toutes les activités sélectionnées
     * @param monde
     */
    public EcouteurSupprimer(MondeIG monde){
        this.monde=monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.monde.removearcs();
        this.monde.removeEtapes();
        monde.notifierObservateurs();
        }


}
