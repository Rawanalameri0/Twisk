package twisk.outils;

public class TailleComposants {
    private  static TailleComposants instance = new TailleComposants();
    private int largeur = 200;
    private int hauteur = 85;

    private TailleComposants(){
    }
    public static TailleComposants getInstance(){
        return instance;
    }
    public int getLargeurActivite(){
        return largeur;
    }
    public int getHauteurActivite(){
        return hauteur;
    }
    public int getLargeurGuichet(){
        return 200;
    }

    public int getHauteurGuichet() {
        return 50;
    }
}
