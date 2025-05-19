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

    public Drzwi(String dane, Boolean dodaj_ID)
    {
        String[] osobne_dane = dane.split(";");

        if (!dodaj_ID)
        {
            super.generuj_ID(Integer.parseInt(osobne_dane[0]));
        } else
        {
            ilosc_stworzonych_drzwi++;
            super.generuj_ID(typ, ilosc_stworzonych_drzwi - 1);
        }

        super.typ = 1;
        super.numer_PZ = Integer.parseInt(osobne_dane[1]);
        super.dane_producenta = new Dane_Producenta(osobne_dane[2], osobne_dane[3]);

        this.wymiary = new Wymiary(Double.parseDouble(osobne_dane[4]), Double.parseDouble(osobne_dane[5]), Double.parseDouble(osobne_dane[6]));
        this.material = osobne_dane[7];
        this.ilosc_skrzydel = Integer.parseInt(osobne_dane[8]);
    }

    //Metody:
    public boolean compareTo(Wymiary wymiary)
    {
        return this.wymiary.compareTo(wymiary);
    }

    public boolean compareTo(Drzwi drzwi)
    {
        return this.dane_producenta.compareTo(drzwi.dane_producenta)
                && this.wymiary.compareTo(drzwi.wymiary)
                && this.material.equals(drzwi.material) && ilosc_skrzydel == drzwi.ilosc_skrzydel;
    }

    public String toFormatedString(String format)
    {
        return String.format(format,
                super.numer_ID, super.numer_PZ, super.dane_producenta.formatuj_do_zapisu()
                , wymiary.formatuj_do_zapisu(), material, String.valueOf(ilosc_skrzydel));
    }

}
