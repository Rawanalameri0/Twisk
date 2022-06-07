package twisk.mondeIG;

import javafx.concurrent.Task;
import twisk.exceptions.*;
import twisk.monde.*;
import twisk.outils.*;
import twisk.simulation.*;
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
    private ArrayList<Client> clients;
    private PointDeControleIG ptchoisi;
    private ArrayList<EtapeIG> etapesSelectionee,entrees,sorties;
    private CorrespondanceEtapes correspondanceEtapes;
    private transient Object simulate ;
    private transient Class<?> simulation;
    private boolean start;
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
        start=false;
        nbClients = 6;
    }

    /**
     * verifie le monde de l'interface graphique
     * @throws MondeException
     */
    private void verifierMondeIG() throws MondeException{
        Iterator<EtapeIG> iter= iterator();
        while (iter.hasNext()){
            EtapeIG e=iter.next();
            if (e.estUnGuichet() && e.NbSuccesseur()>1)
                throw new MondeException("Le guichet a plus d'un successeur");
            if ((!e.isSortie() && e.NbSuccesseur()==0))
                throw new MondeException("Il existe pas un chemin qui mène vers la sortie");
            if (e.isSortie() && e.NbSuccesseur()!=0)
                throw new MondeException("La sortie ne peut pas avoir des successeurs");
            if (e.estUnGuichet() && e.isSortie())
                throw new MondeException("La sortie ne peut pas être un guichet");
            if (e.isEntree() && e.getSuccesseur(0).isEntree())
                throw new MondeException("Une entrée ne peut pas avoir son successeur aussi comme entrée");
            if (e.estUnGuichet())
                e.getSuccesseur(0).setActiviteRestreinte(true);
        }
    }

    /**
     * @return crée le monde depuis le mondeIG
     */
    private Monde creerMonde(){
        correspondanceEtapes = new CorrespondanceEtapes();
        Monde monde=new Monde();
        for (EtapeIG etapeIG: Hetapes.values()) {
            if (etapeIG.estUnGuichet())
                correspondanceEtapes.ajouter(etapeIG,new Guichet(etapeIG.getnomEtape(),etapeIG.getJetons()));
            if (etapeIG.estUneActivite() && !etapeIG.estUneActiviteRestreinte())
                correspondanceEtapes.ajouter(etapeIG,new Activite(etapeIG.getnomEtape(),etapeIG.getTemps(),etapeIG.getEcart()));
            if (etapeIG.estUneActiviteRestreinte())
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

    /**
     * verifie le monde et crée le monde ensuite lance la simulation
     * @throws MondeException
     */
    public void simuler() throws MondeException{
        verifierMondeIG();
        Monde monde=creerMonde();
        ClassLoaderPerso classloader = new ClassLoaderPerso(MondeIG.class.getClassLoader());
        try {
            simulation = classloader.loadClass("twisk.simulation.Simulation");
            simulate= simulation.getDeclaredConstructor().newInstance();
            Method setnbclients = simulation.getDeclaredMethod("setNbClients", int.class);
            setnbclients.invoke(simulate,getNbClients());
            Method ajObserv = simulation.getDeclaredMethod("ajouterObservateur",twisk.vues.Observateur.class);
            ajObserv.invoke(simulate,this);
            Method simuler = simulation.getDeclaredMethod("simuler",twisk.monde.Monde.class);
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        setStart(true);
                        simuler.invoke(simulate,monde);
                        notifierObservateurs();
                        Thread.sleep(20);
                        setStart(false);
                        notifierObservateurs();
                    }catch (InterruptedException e){
                        setStart(false);
                    }
                    return null;
                }
            };
            ThreadsManager.getInstance().lancer(task);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
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
        }
        else if (type.equals("Guichet")){
            EtapeIG et= new GuichetIG(type,FI.getidentifiantEtape()+1,taille.getLargeurGuichet(),taille.getHauteurGuichet());
            this.Hetapes.put(et.getIdentifiant(),et);
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
        if (pt1.getEtape().estUnGuichet() && pt2.getEtape().estUnGuichet())
            throw new TwiskException("Un arc ne peut pas être créer entre deux guichet");

        this.arcs.add(new ArcIG(pt1,pt2));
        notifierObservateurs();
    }

    /**
     * creation d'arc
     * @param pt
     * @throws TwiskException twisk exeption lors de la création des arcs
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
            this.entrees.remove(etape);
            this.sorties.remove(etape);
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

    /**
     * modifier le delai de l'activité sélectionnée
     * @param t delai
     */
    public void setDelai(int t){
        this.etapesSelectionee.get(0).setDelai(t);
    }

    /**
     * modifie l'écart-temps de l'activité sélectionnée
     * @param i écart temps
     */
    public void setEcrat(int i){
        this.etapesSelectionee.get(0).setEcart(i);
    }

    /**
     * renommer l'étape sélectionnée
     * @param s
     */
    public void renommer(String s){
        this.etapesSelectionee.get(0).setNom(s);
    }

    /**
     * modifie le nombre de jetons du guichet sélectionnée
     * @param nbJetons
     */
    public void setNbJetons(int nbJetons){
        this.etapesSelectionee.get(0).setNbJetons(nbJetons);
    }

    /**
     * notifie les obsevateurs
     */
    @Override
    public void reagir() {
        notifierObservateurs();
    }

    /**
     * @return le nombre de clients
     */
    public int getNbClients() {
        return nbClients;
    }

    /**
     * @return la liste des clients
     */
    public ArrayList<Client> clients(){
        Method gestClient;
        try {
            gestClient = simulation.getDeclaredMethod("getGestClients");
            ArrayList<Client> LesClients = (ArrayList<Client>) gestClient.invoke(simulate);
            return  Objects.requireNonNull(LesClients);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return le nombre des étapes sélectionnées
     */
    public int nbEtapesSelectionnes(){
        return etapesSelectionee.size();
    }

    /**
     * désactive la sélection des étapes sélectionnées
     */
    public void desactiverSelection(){
        for (EtapeIG e: etapesSelectionee)
            e.setSelect(false);
        etapesSelectionee.clear();
    }

    /**
     * @return vrai si la simulation a commencé est faux sinon
     */
    public boolean isStart(){
        return start;
    }

    /**
     * modifie le boolean start
     * @param start
     */
    public void setStart(boolean start) {
        this.start = start;
    }

    /**
     * @return l'objet de la simulation
     */
    public Object getSimulate() {
        return simulate;
    }

    /**
     * @return l'objet correspondanceEtapes
     */
    public CorrespondanceEtapes getCorrespondanceEtapes() {
        return correspondanceEtapes;
    }
}
