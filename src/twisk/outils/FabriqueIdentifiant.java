package twisk.outils;

public class FabriqueIdentifiant {
    private static FabriqueIdentifiant instance = new FabriqueIdentifiant();
    private int noEtape;

    private FabriqueIdentifiant()
    {

        this.noEtape=0;
    }

    public static FabriqueIdentifiant getInstance(){

        return instance;
    }

    public String getidentifiantEtape(){

        return String.valueOf(noEtape++);
    }
}
