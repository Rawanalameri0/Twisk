package twisk.exceptions;

/**
 * Classe TwiskException
 * @author AL-AMERI RAWAN
 */
public class TwiskException extends Exception{
    /**
     * La constructeur TwiskException qui represente toutes les twisk.exceptions dans le projet
     * @param erreur le message d'erreur
     */
    public TwiskException(String erreur){
        super(erreur);
    }
}
