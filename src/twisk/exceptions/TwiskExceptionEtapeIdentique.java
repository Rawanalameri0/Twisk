package twisk.exceptions;
/**
 * Classe TwiskExceptionEtapeIdentique
 * @author AL-AMERI RAWAN
 */
public class TwiskExceptionEtapeIdentique extends TwiskException {
    /**
     * La constructeur TwiskExceptionEtapeIdentique qui represente s'il y une erreur pendant la creation d'un arc
     * @param erreur le message d'erreur
     */
    public TwiskExceptionEtapeIdentique(String erreur) {
        super(erreur);
    }
}
