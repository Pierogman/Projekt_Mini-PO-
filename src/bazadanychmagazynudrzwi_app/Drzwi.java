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

    public Drzwi(String dane)
    {
        ilosc_stworzonych_drzwi++;

        String[] osobne_dane = dane.split(";");
        
        super.typ = 1;
        super.numer_PZ = 0;
        super.generuj_ID(Integer.parseInt(osobne_dane[0]));
        
        super.dane_producenta = new Dane_Producenta(osobne_dane[1], osobne_dane[2]);
        
        this.wymiary = new Wymiary(Double.parseDouble(osobne_dane[3]),Double.parseDouble(osobne_dane[4]),Double.parseDouble(osobne_dane[5]));
        this.material = osobne_dane[6];
        this.ilosc_skrzydel = Integer.parseInt(osobne_dane[7]);
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
