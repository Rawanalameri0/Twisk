package twisk.outils;

public class TailleComposants {
    private  static TailleComposants instance = new TailleComposants();
    private int largeur = 220;
    private int hauteur = 85;

    /**
     * Constructeur
     */
    private TailleComposants(){
    }

    /**
     * @return l'instance de TailleComposants
     */
    public static TailleComposants getInstance(){
        return instance;
    }

    /**
     * @return la largeur de l'activité
     */
    public int getLargeurActivite(){
        return largeur;
    }

    /**
     * @return la hauteur de l'activité
     */
    public int getHauteurActivite(){
        return hauteur;
    }

    /**
     * @return la largeur du guichet
     */
    public int getLargeurGuichet(){
        return 200;
    }

    /**
     * @return la hauteur du guichet
     */
    public int getHauteurGuichet() {
        return 50;
    }
}
