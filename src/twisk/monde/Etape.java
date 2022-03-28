package twisk.monde;

import twisk.outils.FabriqueNumero;
import java.util.Iterator;
import java.util.Locale;

/**
 * Classe abstraite Etape
 * @author IKHRICHI SOUMAYA AL-AMERI RAWAN
 */
public abstract class Etape implements Iterable<Etape>{
    protected String nom;
    protected GestionnaireSuccesseurs gestSucc;
    protected int numero= FabriqueNumero.getInstance().getNumeroEtape();

    /**
     * Constructeur de la super-classe
     * @param nom le nom de l'étape
     */
    public Etape(String nom){
        this.nom=nom;
        this.gestSucc= new GestionnaireSuccesseurs();
    }

    /**
     * @return le numero de l'etape
     */
    public int getNumero() {
        return numero;
    }

    /**
     * ajoute les etapes donnés en paramètre comme successeurs à l'instance
     * @param e
     */
    public void ajouterSuccesseur(Etape... e){
        this.gestSucc.ajouter(e);
    }

    /**
     * @return le nombre de successeuurs
     */
    public int nbSuccesseurs(){
        return this.gestSucc.nbEtapes();
    }

    /**
     * @return vrai si l'etape est une activté et faux sinon
     */
    public boolean estUneActivite(){
        return  false;
    }

    /**
     * @return vrai si l'etape est un guichet et faux sinon
     */
    public boolean estUnGuichet(){
        return  false;
    }

    /**
     * @return l'iterateur du gestionnaire du successeurs
     */
    @Override
    public Iterator<Etape> iterator(){
        return gestSucc.iterator();
    }

    /**
     * @return la chaine de caractère contenant les informations de l'étape
     */
    public String toString(){

        return this.nom +" :"+this.gestSucc.toString();
    }

    /**
     * @return le nom de l'étape corrigé
     */
    public String getNom() {
        return ValiderLeNom(this.nom);
    }

    /**
     * @return le codeC correspondant au type de l'étape
     */
    public abstract String toC();

    /**
     * @return le numéro sémaphore du guichet
     */
    public int getNumeroGuichet(){
        return 0;
    }

    /**
     * @return le nonbre de jetons du guichet
     */
    public int getNbjetons(){
        return 0;
    }

    /**
     * elimine les caractères spéciaux
     * @param nom le nom de l'étape
     * @return le nom après la correction
     */
    public String ValiderLeNom(String nom){
        nom=nom.replaceAll("[\\s\\p{Punct}]","_");
        nom=nom.replaceAll("é","e");
        nom=nom.replaceAll("è","e");
        nom=nom.replaceAll("ù","u");
        nom=nom.replaceAll("ê","e");
        nom=nom.replaceAll("â","a");
        nom=nom.replaceAll("î","i");
        nom=nom.replaceAll("à","a");
        nom=nom.replaceAll("ô","o");
        nom=nom.replaceAll("æ","ae");
        nom=nom.replaceAll("ç","c");
        nom=nom.replaceAll("ì","i");
        nom=nom.replaceAll("ñ","n");
        nom=nom.replaceAll("=","_");
        nom=nom.replaceAll("ú","u");
        nom=nom.replaceAll("å","a");
        nom=nom.replaceAll("ã","a");
        nom=nom.replaceAll("ß","b");
        nom=nom.replaceAll("þ","p");
        nom=nom.replaceAll("ë","e");
        nom=nom.replaceAll("ï","i");
        nom=nom.replaceAll("ý","y");
        nom=nom.replaceAll("š","s");
        nom=nom.replaceAll("°","_");
        nom=nom.replaceAll("ð","_");
        nom=nom.replaceAll("µ","u");
        nom=nom.replaceAll("%","_");
        nom=nom.replaceAll("&","_");
        nom=nom.replaceAll("~","_");
        nom=nom.replaceAll("`","_");
        nom=nom.replaceAll("á","a");
        nom=nom.replaceAll("µ","u");
        nom=nom.replaceAll("µ","u");
        nom=nom.replaceAll("€","e");
        nom=nom.replaceAll("£","e");
        nom=nom.replaceAll("¤","o");
        nom=nom.replaceAll("²","2");
        nom=nom.replaceAll("<","_");
        nom=nom.replaceAll(">","_");
        nom=nom.replaceAll("§","e");
        nom=nom.replaceAll("¨","_");
        nom=nom.replaceAll("#","_");
        return nom;
    }


    public String NameEtape(){
        return nom;
    }
}
