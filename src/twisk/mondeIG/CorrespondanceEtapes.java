package twisk.mondeIG;

import twisk.monde.Etape;

import java.util.HashMap;

public class CorrespondanceEtapes {
    private HashMap<EtapeIG,Etape> etapeIGLinkToEtape;
    private HashMap<Etape,EtapeIG> etapeLinkEtapeIG;

    /**
     * Constructeur
     */
    public CorrespondanceEtapes(){
        etapeIGLinkToEtape = new HashMap<>();
        etapeLinkEtapeIG = new HashMap<>();
    }

    /**
     * ajoute l'étape correspondante à l'étapeIG
     * @param etig
     * @param et
     */
    public void ajouter(EtapeIG etig,Etape et){
        etapeIGLinkToEtape.put(etig,et);
        etapeLinkEtapeIG.put(et,etig);
    }

    /**
     * @param e etapeIG
     * @return l'étape correspondante à l'étapeIG
     */
    public Etape get(EtapeIG e){
        return etapeIGLinkToEtape.get(e);
    }

    /**
     * @param e étape
     * @return l'étapeIG correspondante à l'étape
     */
    public EtapeIG getEtapeIG(Etape e){
        return etapeLinkEtapeIG.get(e);
    }
}
