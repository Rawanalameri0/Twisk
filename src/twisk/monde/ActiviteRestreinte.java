package twisk.monde;

/**
 * Classe ActiviteRestreinte
 * @author IKHRICHI SOUMAYA AL-AMERI RAWAN
 */
public class ActiviteRestreinte extends Activite{

    /**
     * Constructeur d'ActiviteRestreinte
     * @param nom le nom del'activité restreinte
     */
    public ActiviteRestreinte(String nom){
        super(nom);
    }

    /**
     * Constructeur d'ActiviteRestreinte
     * @param nom le nom del'activité restreinte
     * @param t le temps (en seconde)
     * @param e l'ecart temps (en seconde)
     */
    public ActiviteRestreinte(String nom,int t,int e){
        super(nom,t,e);
    }


    /**
     * @return la chaine de caractère décrivant l'activité restreinte
     */
    @Override
    public String toString(){
        return super.toString();
    }

    /**
     * @return une chaine de caractères de la fonction en C delai() avec le temps et l'ecart temps
     */
    public String toC(){
        return "  transfert("+this.getNom()+this.getNumero()+","+this.gestSucc.getSuccesseur().getNom()+this.gestSucc.getSuccesseur().getNumero()+");\n" +
                this.gestSucc.getSuccesseur().toC();

    }

}
