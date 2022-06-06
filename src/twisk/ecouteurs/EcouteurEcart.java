package twisk.ecouteurs;
import javafx.animation.PauseTransition;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import twisk.exceptions.TwiskExceptionSaisieNonValide;
import twisk.mondeIG.MondeIG;

import java.io.PrintWriter;
import java.io.StringWriter;
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
            boolean estNombre=true;
            for(int i=0;i<n.length();i++){
                if(!Character.isDigit(n.charAt(i))){
                    estNombre=false;
                }
            }
            if(estNombre && Integer.parseInt(n) > 0){
                this.monde.setEcrat(Integer.parseInt(n));
            }else{
                try {
                    throw new TwiskExceptionSaisieNonValide("Ecart doit etre un entier");
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
