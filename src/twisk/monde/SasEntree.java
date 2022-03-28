package twisk.monde;
/**
 * Classe SasEntree
 * @author IKHRICHI SOUMAYA AL-AMERI RAWAN
 */

public class SasEntree extends Activite{

    /**
     * La constructeur du SasEntree
     */
    public SasEntree(){
        super("Entree" );
    }

    /**
     * @return String de SasEntree
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * @return le code C en chaine de caractere
     */
    @Override
    public String toC(){
        return "\n   entrer("+this.getNom()+this.getNumero()+");\n" +
                "   delai(4,2);\n" +
                "   transfert("+this.getNom()+this.getNumero()+","+this.gestSucc.getSuccesseur().getNom()+this.gestSucc.getSuccesseur().getNumero()+");"+
                this.gestSucc.getSuccesseur().toC();
    }
}
