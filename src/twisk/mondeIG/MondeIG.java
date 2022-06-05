package twisk.mondeIG;

import twisk.ClientTwisk;
import twisk.exceptions.*;
import twisk.monde.*;
import twisk.outils.*;
import twisk.simulation.Client;
import twisk.simulation.GestionnairesClients;
import twisk.simulation.Simulation;
import twisk.vues.Observateur;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Classe MondeIG
 */
public class MondeIG extends SujetObserve implements Iterable<EtapeIG> , Observateur {
    private HashMap<String,EtapeIG> Hetapes;
    private int i=1,nbClients;
    private ArrayList<ArcIG> arcs;
    private PointDeControleIG ptchoisi;
    private ArrayList<EtapeIG> etapesSelectionee,entrees,sorties;
    private CorrespondanceEtapes correspondanceEtapes;
    private GestionnairesClients clients;
    private SujetObserve observe;
    private boolean end;
    /**
     * la constructeur MondeIG
     */
    public MondeIG (){
        this.Hetapes= new HashMap<>();
        EtapeIG A = new ActiviteIG("Activite"+i,"0",TailleComposants.getInstance().getLargeurActivite(),TailleComposants.getInstance().getHauteurActivite());
        this.Hetapes.put(A.getIdentifiant(),A);
        arcs = new ArrayList<>();
        etapesSelectionee = new ArrayList<>();
        entrees = new ArrayList<>();
        sorties = new ArrayList<>();
        nbClients = 6;
        end =true;
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
            if (e.isGuichet() && e.isSortie())
                throw new MondeException("La sortie ne peut pas être un guichet");
        }
    }

    private Monde creerMonde(){
        correspondanceEtapes = new CorrespondanceEtapes();
        Monde monde=new Monde();
        for (EtapeIG etapeIG: Hetapes.values()) {
            if (etapeIG.isGuichet())
                correspondanceEtapes.ajouter(etapeIG,new Guichet(etapeIG.getnomEtape(),etapeIG.getJetons()));
            if (etapeIG.isActivite() && !etapeIG.isActiviteRestreinte())
                correspondanceEtapes.ajouter(etapeIG,new Activite(etapeIG.getnomEtape(),etapeIG.getTemps(),etapeIG.getEcart()));
            if (etapeIG.isActiviteRestreinte())
                correspondanceEtapes.ajouter(etapeIG,new ActiviteRestreinte(etapeIG.getnomEtape(),etapeIG.getTemps(),etapeIG.getEcart()));
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
        try {
            ClassLoaderPerso classloader = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
            Class<?> simulation = classloader.loadClass("twisk.simulation.Simulation");
            Object simul= simulation.getDeclaredConstructor().newInstance();
            Method setnbclients = simulation.getDeclaredMethod("setNbClients", int.class);
            Method simuler = simulation.getDeclaredMethod("simuler",twisk.monde.Monde.class);
            Method gestClient = simulation.getDeclaredMethod("getGestClients");
            Method isEnd = simulation.getDeclaredMethod("isEnd");
            setnbclients.invoke(simul,getNbClients());
            simuler.invoke(simul,monde);
            clients = (GestionnairesClients) gestClient.invoke(simul);
            end =(boolean) isEnd.invoke(simul);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
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
            if(!sorties.contains(etape)){
                etape.setSortie(true);
                etape.setSelect(false);
                this.sorties.add(etape);
            }else {
                etape.setSortie(false);
                etape.setSelect(false);
                sorties.remove(etape);
            }
        }
        etapesSelectionee.clear();
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
                etape.setEntree(false);
                etape.setSelect(false);
                entrees.remove(etape);
            }
        }
        etapesSelectionee.clear();
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

    @Override
    public void reagir() {
        notifierObservateurs();
    }

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

    public int getNbClients() {
        return nbClients;
    }

    public ArrayList<Client> clients(){
        return  clients.getClients();
    }

    public boolean isEnd() {
        return end;
    }

    public int nbEtapesSelectionnes(){
        return etapesSelectionee.size();
    }

    public void desactiverSelection(){
        for (EtapeIG e: etapesSelectionee)
            e.setSelect(false);
        etapesSelectionee.clear();
    }
}
