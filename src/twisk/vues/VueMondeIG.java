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
    private int pos;

    /**
     * La constructeur Monde
     * @param monde
     */
    public VueMondeIG(MondeIG monde){
        this.monde=monde;
        pos =  10;
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
        if (monde.getSimulate() != null) {
            if (monde.isStart()) {
                Iterator<Client> clientIterator = monde.clients();
                if (clientIterator == null)
                    return;
                while (clientIterator.hasNext()) {
                    Client client = clientIterator.next();

                    if (monde.getCorrespondanceEtapes().get(etape).equals(client.getEtape())) {
                        VueClientIG vue = new VueClientIG(monde, client);
                        vue.setPos(pos);
                        v.setClient(vue);
                        pos+=10;
                    }
                }
            }
        }
    }


    @Override
    public void reagir() {
        Pane vueMonde = this;
        Runnable command = new Runnable() {
            @Override
            public void run() {
                vueMonde.getChildren().clear();
                Iterator<ArcIG> it = monde.iteratorarc();
                while (it.hasNext()) {
                    ArcIG arcIG = it.next();
                    VueArcIG vuearcIG = new VueArcIG(monde, arcIG);
                    vueMonde.getChildren().add(vuearcIG);
                }
                VueEtapeIG vue;
                for (EtapeIG etape : monde) {
                    if (!etape.estUnGuichet()) {
                        vue = new VueActiviteIG(monde, etape);
                        //clientDeChaqueEtape(vue,etape);
                    } else {
                        vue = new VueGuichetIG(monde, etape);
                    }
                    vueMonde.getChildren().add(vue);
                    for (PointDeControleIG pt : etape) {
                        VuePointDeControleIG vuept = new VuePointDeControleIG(monde, etape, pt);
                        vueMonde.getChildren().add(vuept);
                    }
                }

                if (monde.isStart()) {
                    Iterator<Client> clientIterator = monde.clients();
                    if (clientIterator == null)
                        return;
                    while (clientIterator.hasNext()) {
                        Client client = clientIterator.next();
                        VueClientIG clientIG = new VueClientIG(monde, client);
                        clientIG.setPos(pos);
                        vueMonde.getChildren().add(clientIG);
                        pos += 10;
                    }
                }
            }
        };
        if (Platform.isFxApplicationThread()){
            command.run();
        }else {
            Platform.runLater(command);
        }
    }
}
