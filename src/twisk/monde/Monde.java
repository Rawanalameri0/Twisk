package twisk.monde;

import java.util.Iterator;

/**
 * Classe Monde
 * @author IKHRICHI SOUMAYA AL-AMERI RAWAN
 */
public class Monde implements Iterable<Etape> {
    private GestionnaireEtapes gestEtapes;
    private SasSortie sasSortie;
    private SasEntree sasEntree;

    /**
     * La constructeur du Monde
     */
    public Monde() {
        gestEtapes = new GestionnaireEtapes();
        sasEntree = new SasEntree();
        sasSortie = new SasSortie();
        gestEtapes.ajouter(sasEntree);
        gestEtapes.ajouter(sasSortie);
    }

    /**
     * Ajoute les étapes donné en paramètre comme successeurs au sasEntree
     * @param etapes une collection des etapes
     */
    public void aCommeEntree(Etape... etapes) {
        this.sasEntree.ajouterSuccesseur(etapes);
    }

    /**
     * Ajoute les étapes donné en paramètre comme successeurs au sasSortie
     * @param etapes une collection des etapes
     */
    public void aCommeSortie(Etape... etapes) {
        for (Etape e : etapes) {
            e.ajouterSuccesseur(sasSortie);
        }

    }

    /**
     * @return le nombre d'entrées
     */
    public int NbEntrees() {
        return sasEntree.nbSuccesseurs();
    }

    /**
     * Ajouter les etapes dans le monde
     * @param etapes
     */
    public void ajouter(Etape... etapes) {
        this.gestEtapes.ajouter(etapes);
    }

    /**
     * @return le nombre d'etapes
     */
    public int nbEtapes() {
        return gestEtapes.nbEtapes();
    }

    /**
     * @return le nombre de guchiet dans le monde
     */
    public int nbGuichets() {
        int cpt = 0;
        for (Etape e : this.gestEtapes) {
            if (e.estUnGuichet()) {
                cpt++;
            }
        }
        return cpt;
    }

    /**
     * @return iterateur d'etape
     */
    @Override
    public Iterator<Etape> iterator() {
        return this.gestEtapes.iterator();
    }

    /**
     * @return une chaine de caractères qui décricre les étapes du monde
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Etape e : gestEtapes) {
            s.append(e.toString() + "\n");
        }
        return new String(s);
    }

    /**
     * @return le code C en chaine de caractere
     */
    public String toC() {
        StringBuilder s = new StringBuilder();

        s.append("#include \"def.h\"\n");
        for (Etape e : gestEtapes) {
            s.append("#define etape" +e.getNumero() +" " + e.getNumero() + "\n");
            if (e.estUnGuichet()){
                s.append("#define NbSemaGuichet" + e.getNumeroGuichet() + " " + e.getNumeroGuichet() + "\n");
            }
        }
        s.append("\nvoid simulation(int ids){");
        s.append(sasEntree.toC());
        s.append("\n}\n");
        return s.toString();
    }

    /**
     * @return la table de jetons
     */
    public int[] getTabJetons(){
        int[] res=new int[nbGuichets()];
        int i=0;
        for (Etape e:this){
            if (e.estUnGuichet()){
                res[i]=e.getNbjetons();
                i++;
            }
        }
        return res;
    }

    /**
     * @param index
     * @return l'etape donné en paramètre
     */
    public String getNomDeEtape(int index){
        return this.gestEtapes.getNomEtape(index);
    }
}
