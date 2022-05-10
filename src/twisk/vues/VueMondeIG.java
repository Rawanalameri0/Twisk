package twisk.vues;

import javafx.scene.layout.Pane;
import twisk.Ecouteurs.EcouteurSetOnDragDropped;
import twisk.Ecouteurs.EcouteurSetOnDragOver;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

import java.util.Iterator;

/**
 * Class VueMondeIG
 */
public class VueMondeIG extends Pane implements Observateur {
    private MondeIG monde;

    /**
     * La constructeur Monde
     * @param monde
     */
    public VueMondeIG(MondeIG monde){
        this.monde=monde;
        for (EtapeIG etape:monde){
            VueEtapeIG vue = new VueActiviteIG(monde,etape);
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


    @Override
    public void reagir() {
        this.getChildren().clear();
        Iterator<ArcIG> it = monde.iteratorarc();
        while(it.hasNext()){
            ArcIG arcIG = it.next();
            VueArcIG vuearcIG = new VueArcIG(monde,arcIG);
            this.getChildren().add(vuearcIG);
        }

        for (EtapeIG etape:monde){
            VueEtapeIG vue = new VueActiviteIG(monde,etape);
            this.getChildren().add(vue);
            for (PointDeControleIG pt:etape){
                VuePointDeControleIG vuept=new VuePointDeControleIG(monde,etape,pt);
                this.getChildren().add(vuept);
            }
        }
    }
}
