package twisk.ecouteurs;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import twisk.exceptions.TwiskException;
import twisk.exceptions.TwiskExceptionSaisieNonValide;
import twisk.mondeIG.MondeIG;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Classe EcouteurSortie
 */
public class EcouteurSortie implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * permet de désigner des activités
      sélectionnées comme des sorties ou de les supprimer si elles sont déjà enregistrées comme sorties
     * @param monde
     */
    public EcouteurSortie(MondeIG monde){
        this.monde=monde;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            this.monde.ajoutsortie();
        } catch (TwiskException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            VBox dialogPaneContent=new VBox();
            alert.setTitle("Twisk Exceptions");
            Label l=new Label("Stack Trace");
            TextArea textArea=new TextArea();
            textArea.setText(setTextStackTrace(e));
            dialogPaneContent.getChildren().addAll(l,textArea);
            alert.getDialogPane().setContent(dialogPaneContent);
            alert.show();
            PauseTransition pause=new PauseTransition(Duration.seconds(5));
            pause.play();
            pause.setOnFinished(fin->alert.close());
        }
        this.monde.notifierObservateurs();
    }

    private String setTextStackTrace(TwiskException e){
        StringWriter s=new StringWriter();
        PrintWriter p=new PrintWriter(s);
        e.printStackTrace(p);
        return s.toString();
    }
}
