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
    public int getLargeur(){
        return largeur;
    }
    public int getHauteur(){
        return hauteur;
    }
}
