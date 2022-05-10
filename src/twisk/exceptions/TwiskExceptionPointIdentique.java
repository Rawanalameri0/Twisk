package twisk.exceptions;
/**
 * Classe TwiskExceptionPointIdentique
 * @author AL-AMERI RAWAN
 */
public class TwiskExceptionPointIdentique extends TwiskException{
    /**
     * La constructeur TwiskExceptionPointIdentique  qui represente s'il y une erreur pendant la creation d'un arc
     * @param erreur le message d'erreur
     */
    public TwiskExceptionPointIdentique(String erreur) {
        super(erreur);
    }
}
