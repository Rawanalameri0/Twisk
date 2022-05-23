package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

public class EcouteurNbJetons implements EventHandler<ActionEvent> {
    private MondeIG monde;
    public EcouteurNbJetons(MondeIG monde){
        this.monde=monde;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog textInputDialog = new TextInputDialog("Nombre de Jetons");
        textInputDialog.setTitle("Choisissez un nombre de jetons");
        Optional<String> res = textInputDialog.showAndWait();
        res.ifPresent(n -> {
            monde.setNbJetons(Integer.parseInt(n));

        });

        this.monde.notifierObservateurs();
    }
}

