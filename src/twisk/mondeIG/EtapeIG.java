package twisk.mondeIG;

import twisk.outils.TailleComposants;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * Class abstract EtapeIG
 */
public abstract class EtapeIG implements Iterable<PointDeControleIG> {
    protected String nom;
    protected String identifiant;
    protected int posX;
    protected int posY;
    protected int largeur;
    protected int hauteur;
    protected PointDeControleIG[] tabpts = new PointDeControleIG[4];
    protected boolean select;
    protected boolean entree;
    protected boolean sortie;
    protected int delai;
    protected int ecrat;

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
       TailleComposants tailleComposants = TailleComposants.getInstance();
        this.largeur=tailleComposants.getLargeur();
        this.hauteur= tailleComposants.getHauteur();
        Random rand = new Random();
        this.posX=rand.nextInt(400);
        this.posY= rand.nextInt(400);
        tabpts[0]=new PointDeControleIG("0",this, posX,posY+hauteur/2);
        tabpts[1]=new PointDeControleIG("1",this, posX+largeur/2,posY);
        tabpts[2]=new PointDeControleIG("2",this, posX+largeur,posY+hauteur/2);
        tabpts[3]=new PointDeControleIG("3",this, posX+largeur/2,posY+hauteur);
        this.select=false;
        this.delai=1;

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
       return ""+delai+ " ± "+ecrat+" temps";
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
        return Arrays.asList(tabpts).iterator();
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
       tabpts= null;
    }

    /**
     * setter nom
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return getter de la table des point de controle
     */
    public PointDeControleIG[] getTabpts() {
        return tabpts;
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
        tabpts[0]=new PointDeControleIG("0",this, posX,posY+hauteur/2);
        tabpts[1]=new PointDeControleIG("1",this, posX+largeur/2,posY);
        tabpts[2]=new PointDeControleIG("2",this, posX+largeur,posY+hauteur/2);
        tabpts[3]=new PointDeControleIG("3",this, posX+largeur/2,posY+hauteur);
    }

    /**
     * setter de delai
     * @param delai
     */
    public void setDelai(int delai) {
        this.delai=delai;
    }

    /**
     * setter de ecrat
     * @param ecrat
     */
    public void setEcrat(int ecrat) {
        this.ecrat = ecrat;
    }

}
