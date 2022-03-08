package twisk.monde;

public class SasEntree extends Activite{
    public SasEntree(){
        super("entrée" );
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toC(){
        return "\n entrer("+this.getNom()+");\n" +
                "delai(4,2);\n" +
                "transfert("+this.getNom()+","+this.gestSucc.getSuccesseur()+");\n" +
                "delai(4,2);";
    }

    @Override
    public String getNom(){
        return "sasEntree";
    }
}
