package twisk.mondeIG;

import twisk.exceptions.*;
import twisk.monde.*;
import twisk.outils.*;
import twisk.simulation.Simulation;

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
    private CorrespondanceEtapes correspondanceEtapes;

    /**
     * la constructeur MondeIG
     */
    public MondeIG (){
        this.Hetapes= new HashMap<>();
        EtapeIG A = new ActiviteIG("Activite"+i,"0",TailleComposants.getInstance().getLargeurActivite(),TailleComposants.getInstance().getHauteurActivite());
        this.Hetapes.put(A.getIdentifiant(),A);
    }

    private void verifierMondeIG() throws MondeException{
        Iterator<EtapeIG> iter= iterator();
        while (iter.hasNext()){
            EtapeIG e=iter.next();
            if (e.isGuichet() && e.NbSuccesseur()>1)
                throw new MondeException("Le guichet a plus d'un successeur");
            if (!e.isSortie() && e.NbSuccesseur()==0)
                throw new MondeException("Il existe pas un chemin qui mène vers la sortie");
            if (e.isGuichet())
                e.getSuccesseur(0).setActiviteRestreinte(true);
            if (e.isSortie() && e.NbSuccesseur()!=0)
                throw new MondeException("La sortie ne peut pas avoir des successeurs");
        }
    }

    private Monde creerMonde(){
        correspondanceEtapes = new CorrespondanceEtapes();
        Monde monde=new Monde();
        for (EtapeIG etapeIG: Hetapes.values()) {
            if (etapeIG.isGuichet())
                correspondanceEtapes.ajouter(etapeIG,new Guichet(etapeIG.getnomEtape(),etapeIG.getJetons()));
            if (etapeIG.isActivite() && !etapeIG.isActiviteRestreinte())
                correspondanceEtapes.ajouter(etapeIG,new Activite(etapeIG.getnomEtape()));
            if (etapeIG.isActiviteRestreinte())
                correspondanceEtapes.ajouter(etapeIG,new ActiviteRestreinte(etapeIG.getnomEtape()));
        }
        for (EtapeIG etapeIG: Hetapes.values()) {
            Etape et = correspondanceEtapes.get(etapeIG);
            for (int i=0;i< etapeIG.NbSuccesseur();i++){
                et.ajouterSuccesseur(correspondanceEtapes.get(etapeIG.getSuccesseur(i)));
            }
            if (etapeIG.isEntree())
                monde.aCommeEntree(et);
            if (etapeIG.isSortie())
                monde.aCommeSortie(et);
            monde.ajouter(et);
        }
        return monde;
    }

    public void simuler() throws MondeException{
        verifierMondeIG();
        Monde monde=creerMonde();
        Simulation simulation= new Simulation();
        simulation.setNbClients(5);
        simulation.simuler(monde);
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
        if(pt1.getEtape().getIdentifiant().equals(pt2.getEtape().getIdentifiant())){
            throw new TwiskExceptionEtapeIdentique("Les points de memes etapes; impossible de créer l'arc");
        }
        if (pt1.getEtape().successeurExist(pt2.getEtape()) || pt2.getEtape().successeurExist(pt1.getEtape())){
               throw new TwiskExceptionArcTrouvee("Un arc existe; impossible d'ajouter d'un arc");
        }
        if(pt1.getX()==pt2.getX() && pt1.getY()==pt2.getY()){
            throw new TwiskExceptionEtapeIdentique("erreur");
        }
        if (pt1.getEtape().isGuichet() && pt2.getEtape().isGuichet())
            throw new TwiskException("Un arc ne peut pas être créer entre deux guichet");

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
            ptchoisi.getEtape().ajouterSucesseurs(pt.getEtape());
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
        etapesSelectionee.clear();
    }

    /**
     * supprime les arcs selectionée
     */
    public void removearcs() {
        Iterator<ArcIG> arcIGIterator = iteratorarc();
        while (arcIGIterator.hasNext()){
            ArcIG arc = arcIGIterator.next();
            if(arc.getpt1().getEtape().isSelect() || arc.getpt2().getEtape().isSelect()|| arc.isSelect()){
                arc.getpt1().getEtape().supprimerSuccesseur(arc.getpt2().getEtape());
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
