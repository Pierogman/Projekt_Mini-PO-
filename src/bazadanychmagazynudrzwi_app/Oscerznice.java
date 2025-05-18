package bazadanychmagazynudrzwi_app;

/**
 *
 * @author tymku
 */
public class Oscerznice extends Produkt
{

    private static int ilosc_stworzonych_oscerznic;

    private Wymiary wymiary;
    private String material;

    public Oscerznice(int id, int numer_WZ, Dane_Producenta dane_producenta, Wymiary wymiary, String material)
    {
        ilosc_stworzonych_oscerznic++;

        super.typ = 3;
        super.numer_PZ = numer_WZ;
        super.generuj_ID(id);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.wymiary = new Wymiary(wymiary);
        this.material = material;
    }

    public Oscerznice(int numer_WZ, Dane_Producenta dane_producenta, Wymiary wymiary, String material)
    {
        ilosc_stworzonych_oscerznic++;

        super.typ = 3;
        super.numer_PZ = numer_WZ;
        super.generuj_ID(typ, ilosc_stworzonych_oscerznic - 1);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.wymiary = new Wymiary(wymiary);
        this.material = material;
    }

    // Metody: 
    public boolean compareTo(Wymiary wymiary)
    {
        return this.wymiary.compareTo(wymiary);
    }

    public String toFormatedString(String format)
    {
        return String.format(format, super.numer_ID, super.dane_producenta.toString(), wymiary.toString(), material, " ");
    }

    @Override
    public String toString()
    {
        return super.numer_ID + ";" + super.dane_producenta.formatuj_do_zapisu() + ";"
                + wymiary.formatuj_do_zapisu() + ";" + material + ";" + " ";

    }

}
