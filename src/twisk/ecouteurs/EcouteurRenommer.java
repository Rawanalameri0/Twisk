package twisk.ecouteurs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.MondeIG;
import twisk.vues.Observateur;
import java.util.Optional;

/**
 * Une classe EcouteurRenommer
 */
public class EcouteurRenommer implements EventHandler<ActionEvent>,Observateur {
    private MondeIG monde;

    /**
     * Une saisie du nouveau nom de l'activité
     * @param monde
     */
    public EcouteurRenommer(MondeIG monde){
        this.monde=monde;

    }
    @Override
    public void handle(ActionEvent actionEvent) {
        if (monde.nbEtapesSelectionnes() == 1) {
            TextInputDialog textInputDialog = new TextInputDialog("renommer");
            textInputDialog.setHeaderText("Saisie du nouveau nom de l'étape");
            Optional<String> res = textInputDialog.showAndWait();
            res.ifPresent(titre -> {
                monde.renommer(titre);
            });
            this.monde.notifierObservateurs();
        }
    }




    @Override
    public void reagir() {

    }


}
