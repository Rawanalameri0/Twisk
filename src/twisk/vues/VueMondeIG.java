package twisk.vues;

import javafx.application.Platform;
import javafx.scene.layout.*;
import twisk.ecouteurs.*;
import twisk.mondeIG.*;
import twisk.simulation.Client;
import java.util.Iterator;

/**
 * Class VueMondeIG
 */
public class VueMondeIG extends Pane implements Observateur {
    private MondeIG monde;
    private SujetObserve observe;

    /**
     * La constructeur Monde
     * @param monde
     */
    public VueMondeIG(MondeIG monde){
        this.monde=monde;
        VueEtapeIG vue;
        for (EtapeIG etape:monde){
            if(!etape.estUnGuichet()) {
                vue = new VueActiviteIG(monde, etape);
            }
            else {
                vue = new VueGuichetIG(monde, etape);
            }
            this.getChildren().add(vue);
            for (PointDeControleIG pt:etape){
                VuePointDeControleIG vuept=new VuePointDeControleIG(monde,etape,pt);
                this.getChildren().add(vuept);
            }

        }
        this.setOnDragDropped(new EcouteurSetOnDragDropped(monde,this));
        this.setOnDragOver(new EcouteurSetOnDragOver(this.monde,this));
        this.monde.ajouterObservateur(this);
    }

    public void clientDeChaqueEtape(VueEtapeIG v,EtapeIG etape){
        if (monde.isStart()){
            //System.out.println(monde.clients());
            for (Client cl: monde.clients()){
                if (monde.getCorrespondanceEtapes().get(etape).equals(cl.getEtape())){
                    VueClientIG vue = new VueClientIG(monde,cl);
                    System.out.println("x "+vue.getCenterX()+"y "+vue.getCenterY());
                    v.setClient(vue);
                }
            }
        }
    }


    @Override
    public void reagir() {
        Runnable command = () -> {
                this.getChildren().clear();
                Iterator<ArcIG> it = monde.iteratorarc();
                while(it.hasNext()){
                    ArcIG arcIG = it.next();
                    VueArcIG vuearcIG = new VueArcIG(monde,arcIG);
                    this.getChildren().add(vuearcIG);
                }
                VueEtapeIG vue;
                for (EtapeIG etape:monde){
                    if(!etape.estUnGuichet()) {
                        vue = new VueActiviteIG(monde, etape);
                        clientDeChaqueEtape(vue,etape);
                    }
                    else {
                        vue = new VueGuichetIG(monde, etape);
                        //clientDeChaqueEtape(vue.hBox,etape);
                    }
                    this.getChildren().add(vue);
                    for (PointDeControleIG pt:etape){
                        VuePointDeControleIG vuept=new VuePointDeControleIG(monde,etape,pt);
                        this.getChildren().add(vuept);
                    }
                }
                /*
                if (monde.isStart()){
                    System.out.println(monde.clients());
                    for (Client client: monde.clients()){
                        VueClientIG clientIG = new VueClientIG(monde,client);
                        this.getChildren().add(clientIG);
                    }
                }
                 */
        };
        if (Platform.isFxApplicationThread()){
            command.run();
        }else {
            Platform.runLater(command);
        }
    }
}
