package twisk.monde;

public class SasEntree extends Activite{
    public SasEntree(){
        super("sasEntree" );
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toC(){
        return "\nentrer("+this.getNom()+");\n" +
                "delai(4,2);\n" +
                "transfert("+this.getNom()+","+this.gestSucc.getNomSuccesseur()+");\n"+
                this.gestSucc.getSuccesseur().toC();
    }
}
