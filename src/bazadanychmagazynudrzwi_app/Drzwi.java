package bazadanychmagazynudrzwi_app;

/**
 * Ta klasa perzechowuje informacje o drzwiach
 *
 * @author tymku
 */
public class Drzwi extends Produkt
{

    private static int ilosc_stworzonych_drzwi;

    private Wymiary wymiary;
    private String material;
    private int ilosc_skrzydel;

    // Konstruktory:
    public Drzwi(int id, int nume_WZ, Dane_Producenta dane_producenta, Wymiary wymiary, String material, int ilosc_skrzydel)
    {
        ilosc_stworzonych_drzwi++;

        super.typ = 1;
        super.numer_PZ = numer_PZ;
        super.generuj_ID(id);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.wymiary = new Wymiary(wymiary);
        this.material = material;
        this.ilosc_skrzydel = ilosc_skrzydel;
    }

    public Drzwi(int numer_WZ, Dane_Producenta dane_producenta, Wymiary wymiary, String material, int ilosc_skrzydel)
    {
        ilosc_stworzonych_drzwi++;

        super.typ = 1;
        super.numer_PZ = numer_WZ;
        super.generuj_ID(typ, ilosc_stworzonych_drzwi - 1);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.wymiary = new Wymiary(wymiary);
        this.material = material;
        this.ilosc_skrzydel = ilosc_skrzydel;
    }

    //Metody:
    public boolean compareTo(Wymiary wymiary)
    {
        return this.wymiary.compareTo(wymiary);
    }

    public String toFormatedString(String format)

    {
        return String.format(format,
                super.numer_ID, super.dane_producenta.toString(), wymiary.toString(), material, ilosc_skrzydel);
    }

    @Override
    public String toString()
    {
        return super.numer_ID + ";" + super.dane_producenta.formatuj_do_zapisu() + ";"
                + wymiary.formatuj_do_zapisu() + ";" + material + ";" + ilosc_skrzydel;

    }

}
