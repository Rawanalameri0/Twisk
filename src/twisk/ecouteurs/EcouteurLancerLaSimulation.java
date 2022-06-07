package twisk.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import twisk.exceptions.MondeException;
import twisk.mondeIG.MondeIG;
import java.io.PrintWriter;
import java.io.StringWriter;

public class EcouteurLancerLaSimulation implements EventHandler<ActionEvent> {
    private MondeIG monde;

    public EcouteurLancerLaSimulation(MondeIG monde){
        this.monde=monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            monde.setStart(true);
            monde.simuler();
            monde.setStart(false);
            monde.notifierObservateurs();
        } catch (MondeException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            VBox dialogPaneContent=new VBox();
            alert.setTitle("Monde Exceptions");
            alert.setHeaderText("Erreur lors de la création du monde");
            Label l=new Label("Stack Trace");
            TextArea text=new TextArea();
            text.setText(setTextStackTrace(e));
            dialogPaneContent.getChildren().addAll(l,text);
            alert.getDialogPane().setContent(dialogPaneContent);
            alert.show();
            PauseTransition pause=new PauseTransition(Duration.seconds(5));
            pause.play();
            pause.setOnFinished(fin->alert.close());
        }
    }

    /**
     * @param e exception
     * @return renvoie les details de l'exception en chaîne de caractères
     */
    private String setTextStackTrace(MondeException e){
        StringWriter s=new StringWriter();
        PrintWriter p=new PrintWriter(s);
        e.printStackTrace(p);
        return s.toString();
    }
}
