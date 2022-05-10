package twisk.mondeIG;

import twisk.exceptions.*;
import twisk.outils.*;
import java.util.*;

/**
 * Classe MondeIG
 */
public class MondeIG extends SujetObserve implements Iterable<EtapeIG> {
    private HashMap<String,EtapeIG> Hetapes;
    private int i=1;
    private ArrayList<ArcIG> arcs = new ArrayList<>();
    private PointDeControleIG ptchoisi;
    private ArrayList<EtapeIG> etapesSelectionee = new ArrayList<>();
    private ArrayList<EtapeIG> entrees = new ArrayList<>();
    private ArrayList <EtapeIG> sorties = new ArrayList<>();


    /**
     * la constructeur MondeIG
     */
    public MondeIG (){
        this.Hetapes= new HashMap<>();
        EtapeIG A = new ActiviteIG("Activite"+i,"0",40,40);
        this.Hetapes.put(A.getIdentifiant(),A);
    }

    /**
     * ajouter une activite
     * @param type
     */
    public void ajouter(String type) {
        TailleComposants taille = TailleComposants.getInstance();
        FabriqueIdentifiant FI = FabriqueIdentifiant.getInstance();
        if(type.equals("Activite")) {
            i = i+1;
            EtapeIG e= new ActiviteIG(type+i, FI.getidentifiantEtape()+1, taille.getLargeurActivite(),taille.getHauteurActivite() );
            this.Hetapes.put(e.getIdentifiant(), e);
            notifierObservateurs();
        }
        else if (type.equals("Guichet")){
            EtapeIG et= new GuichetIG(type,FI.getidentifiantEtape()+1,taille.getLargeurGuichet(),taille.getHauteurGuichet());
            this.Hetapes.put(et.getIdentifiant(),et);
            notifierObservateurs();
        }
    }

    /**
     * @return iterator d'etapes
     */
    @Override
    public Iterator<EtapeIG> iterator() {
        return Hetapes.values().iterator();
    }

    /**
     * @return getter de hashmap etapes
     */
    public int getNombresEtapes() {
        return Hetapes.size();
    }

    /**
     * permet d’ajouter un arc, créé à partir des deux points de contrôle passés en paramètre.
     * @param pt1
     * @param pt2
     * @throws TwiskException
     */
    public void ajouter (PointDeControleIG pt1,PointDeControleIG pt2)throws TwiskException {
        if(pt1.getEtape().equals(pt2.getEtape())){
            throw new TwiskExceptionEtapeIdentique("Les points de memes etapes; impossible de créer l'arc");
        }
        for(ArcIG arcIG:arcs){
           if(arcIG.getpt1().getY()== pt1.getY() && arcIG.getpt1().getX()== pt1.getX()&& arcIG.getpt2().getY()== pt2.getY()&&arcIG.getpt2().getX()== pt2.getX()){
               throw new TwiskExceptionArcTrouvee("Un arc existe; impossible d'ajouter d'un arc");
           }
        }
        if(pt1.getX()==pt2.getX() && pt1.getY()==pt2.getY()){
            throw new TwiskExceptionEtapeIdentique("erreur");
        }


        this.arcs.add(new ArcIG(pt1,pt2));
        notifierObservateurs();
    }

    /**
     * creation d'arc
     * @param pt
     * @throws TwiskException
     */
    public void creerarc (PointDeControleIG pt) throws TwiskException{
        if (this.ptchoisi==null){
            this.ptchoisi=pt;
        }
        else{
            ajouter(ptchoisi,pt);
            this.notifierObservateurs();
            this.ptchoisi=null;
        }

    }

    /**
     * @return iterator d'arc
     */
    public Iterator <ArcIG> iteratorarc(){
        return arcs.iterator();
    }

    /**
     * ajouter l'etape selectionée
     * @param etape
     */
    public void addEtapeSelect(EtapeIG etape){
        if(!etapesSelectionee.contains(etape)){
            etapesSelectionee.add(etape);
            etape.setSelect(true);
        }else{
            etape.setSelect(false);
            etapesSelectionee.remove(etape);
        }
    }

    /**
     * supprime les etapes selectionée
     */
    public void removeEtapes() {
        for (EtapeIG etape : etapesSelectionee) {
            this.Hetapes.remove(etape.getIdentifiant());
        }
    }

    /**
     * supprime les arcs selectionée
     */
    public void removearcs() {
        Iterator<ArcIG> arcIGIterator = iteratorarc();
        while (arcIGIterator.hasNext()){
            ArcIG arc = arcIGIterator.next();
            if(arc.getpt1().getEtape().isSelect() || arc.getpt2().getEtape().isSelect()|| arc.isSelect()){
                arcIGIterator.remove();
            }
        }

    }

    /**
     * ajouter les sorties
     */
    public void ajoutsortie(){
        for (EtapeIG etape : etapesSelectionee){
            if(!entrees.contains(etape)){
                etape.setSortie(true);
                etape.setSelect(false);
                this.sorties.add(etape);
            }else {
                sorties.remove(etape);
            }
        }
    }

    /**
     * ajouter les entrées
     */
    public void ajoutentree(){
        for (EtapeIG etape : etapesSelectionee){
            if(!entrees.contains(etape)){
                etape.setEntree(true);
                etape.setSelect(false);
                this.entrees.add(etape);
            }else {
                entrees.remove(etape);
            }

        }
    }


    public void setDelai(int t){
        this.etapesSelectionee.get(0).setDelai(t);
    }


    public void setEcrat(int i){
        this.etapesSelectionee.get(0).setEcart(i);
    }

    public void renommer(String s){
        this.etapesSelectionee.get(0).setNom(s);
    }

    public void setNbJetons(int nbJetons){
        this.etapesSelectionee.get(0).setNbJetons(nbJetons);
    }
}
