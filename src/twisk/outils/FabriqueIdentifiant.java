package twisk.outils;

public class FabriqueIdentifiant {
    private static FabriqueIdentifiant instance = new FabriqueIdentifiant();
    private int noEtape;

    /**
     * Constructeur
     */
    private FabriqueIdentifiant()
    {

        this.noEtape=0;
    }

    /**
     * @return l'instance de FabriqueIdentifiant
     */
    public static FabriqueIdentifiant getInstance(){

        return instance;
    }

    /**
     * @return l'identifiant de l'étape
     */
    public String getidentifiantEtape(){

        return String.valueOf(noEtape++);
    }
}
