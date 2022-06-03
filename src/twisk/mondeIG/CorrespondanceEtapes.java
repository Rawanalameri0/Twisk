package twisk.mondeIG;

import twisk.monde.Etape;

import java.util.HashMap;

public class CorrespondanceEtapes {
    private HashMap<EtapeIG,Etape> etapeIGLinkToEtape;

    public CorrespondanceEtapes(){
        etapeIGLinkToEtape = new HashMap<>();
    }

    public void ajouter(EtapeIG etig,Etape et){
        etapeIGLinkToEtape.put(etig,et);
    }

    public Etape get(EtapeIG e){
        return etapeIGLinkToEtape.get(e);
    }
}
