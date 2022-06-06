package twisk.ecouteurs;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import twisk.exceptions.MondeException;
import twisk.exceptions.TwiskExceptionSaisieNonValide;
import twisk.mondeIG.MondeIG;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
/**
 * Classe EcouteurDelai
 * @author AL-AMERI RAWAN
 */
public class EcouteurDelai implements EventHandler<ActionEvent> {
    private MondeIG monde;


    /**
     * La constructeur EcouteurDelai qui permettre de choisir le delai d'une activite
     * @param monde
     */
    public EcouteurDelai(MondeIG monde){
        this.monde=monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog textInputDialog = new TextInputDialog("Delai");
        textInputDialog.setTitle("Choisissez un delai");
        Optional<String> res = textInputDialog.showAndWait();
        res.ifPresent(n -> {
            boolean estNombre=true;
            for(int i=0;i<n.length();i++){
                if(!Character.isDigit(n.charAt(i))){
                    estNombre=false;
                }
            }
            if(estNombre && Integer.parseInt(n) > 0){
                this.monde.setDelai(Integer.parseInt(n));
            }else{
                try {
                    throw new TwiskExceptionSaisieNonValide("Delai doit etre un entier");
                } catch (TwiskExceptionSaisieNonValide e) {
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
            }
        });
        this.monde.notifierObservateurs();
    }
    private String setTextStackTrace(TwiskExceptionSaisieNonValide e){
        StringWriter s=new StringWriter();
        PrintWriter p=new PrintWriter(s);
        e.printStackTrace(p);
        return s.toString();
    }
}
