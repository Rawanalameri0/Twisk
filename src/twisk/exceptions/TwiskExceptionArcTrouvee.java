package twisk.exceptions;
/**
 * Classe TwiskExceptionArcTrouvee
 * @author AL-AMERI RAWAN
 */
public class TwiskExceptionArcTrouvee extends TwiskException {
    /**
     * La constructeur TwiskExceptionArcTrouvee qui represente s'il y une erreur pendant la creation d'un arc
     * @param erreur le message d'erreur
     */
    public TwiskExceptionArcTrouvee(String erreur) {
        super(erreur);
    }
}
