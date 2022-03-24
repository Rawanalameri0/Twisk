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
        return "\n   entrer(etape"+this.getNumero()+");\n" +
                "   delai(4,2);\n" +
                "   transfert(etape"+this.getNumero()+",etape"+this.gestSucc.getSuccesseur().getNumero()+");"+
                this.gestSucc.getSuccesseur().toC();
    }
}
