package twisk.ecouteurs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.MondeIG;
import java.util.Optional;
/**
 * Classe EcouteurEcart
 * @author AL-AMERI RAWAN
 */
public class EcouteurEcart implements EventHandler<ActionEvent> {
    private MondeIG monde;


    /**
     * La constructeur EcouteurEcart qui permettre de choisir le ecrat-temps d'une activite
     * @param monde
     */
    public EcouteurEcart(MondeIG monde){
        this.monde=monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog textInputDialog = new TextInputDialog("Ecrat-temps");
        textInputDialog.setTitle("Choisissez un ecrat-temps");
        Optional<String> res = textInputDialog.showAndWait();
        res.ifPresent(n -> {
            monde.setEcrat(Integer.parseInt(n));

        });

        this.monde.notifierObservateurs();
    }
}
