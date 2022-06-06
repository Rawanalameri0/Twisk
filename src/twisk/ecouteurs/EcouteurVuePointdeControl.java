package twisk.ecouteurs;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import twisk.exceptions.TwiskException;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import twisk.mondeIG.MondeIG;
import twisk.vues.VuePointDeControleIG;

import java.io.PrintWriter;
import java.io.StringWriter;


public class EcouteurVuePointdeControl implements EventHandler<MouseEvent> {
    private MondeIG monde;
    private VuePointDeControleIG pt;




    public EcouteurVuePointdeControl(MondeIG monde, VuePointDeControleIG pt) {
        this.monde=monde;
        this.pt=pt;
    }


    @Override
    public void handle(MouseEvent me) {
        try {
            monde.creerarc(pt.getPtdecontrole());
        } catch (TwiskException twiskException) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            VBox dialogPaneContent=new VBox();
            alert.setTitle("Twisk Exceptions");
            alert.setHeaderText("Erreur lors de la création des arcs");
            Label l=new Label("Stack Trace");
            TextArea text=new TextArea();
            text.setText(setTextStackTrace(twiskException));
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
    private String setTextStackTrace(TwiskException e){
        StringWriter s=new StringWriter();
        PrintWriter p=new PrintWriter(s);
        e.printStackTrace(p);
        return s.toString();
    }
}