package twisk.vues;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
            if(!etape.isGuichet()) {
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

    public void clientDeChaqueEtape(HBox box,EtapeIG etape){
        if (monde.getSimulate() != null){
            if (monde.isStart()){
                System.out.println("je commence la simulation");
                for (Client cl: monde.clients()){
                    if (monde.getCorrespondanceEtapes().get(etape).getNumero()== cl.getNumeroClient()){
                        VueClientIG vue = new VueClientIG(monde,cl);
                        box.getChildren().add(vue);
                    }
                }
                System.out.println(monde.clients().toString());
            }
        }
    }


    @Override
    public void reagir() {
        this.getChildren().clear();
        Iterator<ArcIG> it = monde.iteratorarc();
        while(it.hasNext()){
            ArcIG arcIG = it.next();
            VueArcIG vuearcIG = new VueArcIG(monde,arcIG);
            this.getChildren().add(vuearcIG);
        }
        VueEtapeIG vue;
        for (EtapeIG etape:monde){
            if(!etape.isGuichet()) {
                vue = new VueActiviteIG(monde, etape);
                clientDeChaqueEtape(vue.getHBox(),etape);
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
    }
}
