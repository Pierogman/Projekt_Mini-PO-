package bazadanychmagazynudrzwi_app;

/**
 *
 * @author tymku
 */
public class Klamki extends Produkt
{

    private static int ilosc_stworzonych_klamek;

    private String material;

    public Klamki(int id, int numer_WZ, Dane_Producenta dane_producenta, String material)
    {
        ilosc_stworzonych_klamek++;

        super.typ = 2;
        super.numer_PZ = numer_WZ;
        super.generuj_ID(id);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.material = material;

    }

    public Klamki(int numer_WZ, Dane_Producenta dane_producenta, String material)
    {
        ilosc_stworzonych_klamek++;

        super.typ = 2;
        super.numer_PZ = numer_WZ;
        super.generuj_ID(typ, ilosc_stworzonych_klamek - 1);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.material = material;

    }

    //Metody 
    public String toFormatedString(String format)
    {
       return String.format(format, super.numer_ID, super.dane_producenta.toString()," ", material, " ");
    }

    @Override
    public String toString()
    {
        return super.numer_ID + ";" + super.dane_producenta.formatuj_do_zapisu() + ";"
                + " " + ";" + material + ";" + " ";

    }

}
