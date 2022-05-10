package twisk.ecouteurs;
import twisk.exceptions.TwiskException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.exceptions.TwiskExceptionDelai;
import twisk.mondeIG.MondeIG;
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
                        Boolean estNombre=true;
                        for(int i=0;i<n.length();i++){
                            if(!Character.isDigit(n.charAt(i))){
                                estNombre=false;
                            }
                        }
                        if(estNombre==true){
                            this.monde.setDelai(Integer.valueOf(n));
                        }else{
                            try {
                                throw new TwiskExceptionDelai("Delai doit etre un entier");
                            } catch (TwiskExceptionDelai e) {
                                e.printStackTrace();
                            }
                        }
                    });
                      

                    this.monde.notifierObservateurs();
                    
                }
    }
