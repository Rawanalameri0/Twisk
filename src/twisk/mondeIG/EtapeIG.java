package twisk.mondeIG;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Class abstract EtapeIG
 */
public abstract class EtapeIG implements Iterable<PointDeControleIG> {
    protected String nom,identifiant;
    protected int posX,posY,largeur,hauteur;
    protected ArrayList<PointDeControleIG> tabpts;
    protected ArrayList<EtapeIG> successeurs;
    protected boolean select,entree,sortie,activiteRestreinte;
    /**
     *La constructeur EtapeIG
     * @param nom
     * @param idf
     * @param larg
     * @param haut
     */
    public EtapeIG(String nom, String idf, int larg, int haut){
        this.nom = nom;
        this.identifiant=idf;
        this.largeur=larg;
        this.hauteur= haut;
        Random rand = new Random();
        tabpts= new ArrayList<>(4);
        this.posX=rand.nextInt(400);
        this.posY= rand.nextInt(400);
        this.initialiserPointsDeControle();
        this.select=false;
        entree=false;
        sortie=false;
        successeurs = new ArrayList<>(10);

    }


    /**
     * @return getter de identifiant
     */
    public String getIdentifiant() {
       return identifiant;
    }

    /**
     * @return getter de le nom de etape
     */
    public String getnomEtape() {
       return nom;
    }

    /**
     * @return getter de delai
     */
    public  String getDelai(){
       return null;
    }

    /**
     * @return getter la position x
     */
    public int getPosX(){
       return posX;
    }

    /**
     * @return getter la position y
     */
    public int getPosY(){
       return posY;
    }

    /**
     * @return l'iterator de point de controle
     */
    @Override
    public Iterator<PointDeControleIG> iterator() {
        return tabpts.iterator();
    }

    /**
     * setter de select
     * @param select
     */
    public void setSelect(boolean select) {
        this.select = select;
    }

    /**
     * @return getter de select
     */
    public boolean isSelect() {
        return select;
    }

    /**
     * supprime les tous les points de controle
     */
    public void supprime(){
       tabpts.clear();
    }

    /**
     * setter nom
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * setter de la position x
     * @param posX
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * setter de la position y
     * @param posY
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * @return getter de la sortie
     */
    public boolean isSortie() {
        return sortie;
    }

    /**
     * @return getter de la entree
     */
    public boolean isEntree() {
        return entree;
    }

    /**
     * setter de la entree
     * @param entree
     */
    public void setEntree(boolean entree) {
        this.entree = entree;
    }

    /**
     * setter de la sortie
     * @param sortie
     */
    public void setSortie(boolean sortie) {
        this.sortie = sortie;
    }

    /**
     * recreer tous les point de controle
     */
    public void refaitptdecontrole(){
        tabpts.clear();
        initialiserPointsDeControle();
    }

    /**
     * initialisation des points de contr??les de l'??tape
     */
    public void initialiserPointsDeControle(){
        if (this.estUnGuichet()){
            tabpts.add(0,new PointDeControleIG("0",this, posX,posY+hauteur/2));
            tabpts.add(1,new PointDeControleIG("1",this, posX+largeur,posY+hauteur/2));
        }else {
            tabpts.add(0,new PointDeControleIG("0",this, posX,posY+hauteur/2));
            tabpts.add(1,new PointDeControleIG("1",this, posX+largeur/2,posY));
            tabpts.add(2,new PointDeControleIG("2",this, posX+largeur,posY+hauteur/2));
            tabpts.add(3,new PointDeControleIG("3",this, posX+largeur/2,posY+hauteur));
        }
    }

    /**
     * setter de delai
     * @param delai
     */
    public void setDelai(int delai) {

    }

    /**
     * setter de ecrat
     * @param ecrat
     */
    public void setEcart(int ecrat) {

    }

    /**
     * setter de nombre de jetons
     * @param nbJetons
     */
    public void setNbJetons(int nbJetons){

    }

    /**
     * @return le nombre de jetons de l'??tape si elle est un guichet
     */
    public int getJetons(){
        return 0;
    }

    /**
     * @return vrai si l'etapeIG est un guichet
     */
    public boolean estUnGuichet(){
        return false;
    }

    /**
     * ajoute l'??tape donn?? en param??tre comme successeur
     * @param e l'??tape successeur
     */
    public void ajouterSucesseurs(EtapeIG e){
        successeurs.add(e);
    }

    /**
     * supprimer le successeur
     * @param e l'??tape successeur
     */
    public void supprimerSuccesseur(EtapeIG e){
        successeurs.remove(e);
    }

    /**
     * @param e ??tape ?? chercher
     * @return vrai si l'??tape est dans les successeurs
     */
    public boolean successeurExist(EtapeIG e){
        return successeurs.contains(e);
    }

    /**
     * @return le nombre de successeurs
     */
    public int NbSuccesseur() {
        return successeurs.size();
    }

    /**
     * @return vrai si l'activit?? est une activit?? restreinte et faux sinon
     */
    public boolean estUneActiviteRestreinte() {
        return false;
    }

    /**
     * @param index
     * @return le successeur qui correspond ?? l'index
     */
    public EtapeIG getSuccesseur(int index){
        return successeurs.get(index);
    }

    /**
     * @return vrai si l'??tape est une activit?? et faux sinon
     */
    public boolean estUneActivite(){
        return false;
    }

    /**
     * modifier le bool??ean de l'activit?? restreinte
     * @param activiteRestreinte
     */
    public void setActiviteRestreinte(boolean activiteRestreinte) {
        this.activiteRestreinte = activiteRestreinte;
    }

    /**
     * @return le temps de l'activit??
     */
    public int getTemps(){
        return 0;
    }

    /**
     * @return l'??cart temps de l'activit??
     */
    public int getEcart(){
        return 0;
    }

    /**
     * @return la hauteur de l'??tapeIG
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * @return la largeur de l'??tapeIG
     */
    public int getLargeur() {
        return largeur;
    }
}
